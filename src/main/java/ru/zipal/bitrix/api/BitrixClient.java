package ru.zipal.bitrix.api;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.apache.http.NameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BitrixClient {

    private final Logger logger = LoggerFactory.getLogger(BitrixClient.class);

    public static final String AUTH_QUERY_KEY = "auth";
    public static final String API_URL_FORMAT = "https://%s/rest/%s.json";
    public static final String ACCESS_TOKEN_ENDPOINT_FORMAT = "https://%s/oauth/token";
    public static final String AUTHORIZATION_BASE_URL_FORMAT = "https://%s/oauth/authorize";

    private OAuth20Service service;
    private OAuth2AccessToken accessToken;
    private String domain;
    private AccessTokenListener accessTokenListener;

    public BitrixClient(String domain, String apiKey, String apiSecret, String redirectUri) {
        this.domain = domain;
        String accessTokenEndpoint = String.format(ACCESS_TOKEN_ENDPOINT_FORMAT, domain);
        String authorizationBaseUrl = String.format(AUTHORIZATION_BASE_URL_FORMAT, domain);
        service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .callback(redirectUri)
                .build(new BitrixApi20(accessTokenEndpoint, authorizationBaseUrl));
    }

    public String getAuthorizationUrl() {
        return service.createAuthorizationUrlBuilder().build();
    }

    public void authorize(String code) throws InterruptedException, ExecutionException, IOException {
        accessToken = service.getAccessToken(code);
        if (accessToken != null) {
            fireAccessTokenReceived(accessToken);
        }
    }

    public void setAccessTokenListener(AccessTokenListener accessTokenListener) {
        this.accessTokenListener = accessTokenListener;
    }

    public void setAccessToken(String accessToken, String refreshToken) {
        this.accessToken = new OAuth2AccessToken(accessToken, null, null, refreshToken, null, null);
    }

    public String getAccessToken() throws UnauthorizedBitrixApiException {
        checkAuthorize();
        return accessToken.getAccessToken();
    }

    public String getRefreshToken() throws UnauthorizedBitrixApiException {
        checkAuthorize();

        return accessToken.getRefreshToken();
    }

    public JSONObject get(String method, List<NameValuePair> params) throws BitrixApiException {
        String apiUrl = String.format(API_URL_FORMAT, domain, method);
        return execute(Verb.GET, apiUrl, params);
    }

    public JSONObject post(String method, List<NameValuePair> params) throws BitrixApiException {
        String apiUrl = String.format(API_URL_FORMAT, domain, method);
        return execute(Verb.POST, apiUrl, params);
    }

    public void checkAuthorize() throws UnauthorizedBitrixApiException {
        if (accessToken == null) {
            throw new UnauthorizedBitrixApiException("Access Token is not exists, authorize first");
        }
    }

    private OAuthRequest createRequest(Verb verb, String url, List<NameValuePair> params) {
        OAuthRequest request = new OAuthRequest(verb, url);
        if (params != null) {
            params.forEach(param ->
                    request.addParameter(param.getName(), param.getValue()));
        }
        request.addQuerystringParameter(AUTH_QUERY_KEY, accessToken.getAccessToken());
        return request;
    }

    private JSONObject execute(Verb verb, String url, List<NameValuePair> params) throws BitrixApiException {

        logger.info("Request {} - {}", verb, url);

        checkAuthorize();

        Response response;
        try {
            OAuthRequest request = createRequest(verb, url, params);
            response = service.execute(request);

            if (response.getCode() == 401 && accessToken.getRefreshToken() != null) {

                logger.info("Access Token expired, try to retrieve new one");

                accessToken = service.refreshAccessToken(accessToken.getRefreshToken());
                if (accessToken != null) {
                    fireAccessTokenReceived(accessToken);

                    request = createRequest(verb, url, params);
                    response = service.execute(request);
                } else {
                    logger.info("Cannot retrieve new Access Token using Refresh Token");
                }
            }

        } catch (Exception e) {
            throw new BitrixApiException(String.format("An error occurred while execute request %s", url));
        }

        String responseBody;
        try {
            responseBody = response.getBody();
        } catch (IOException e) {
            throw new BitrixApiException(e);
        }

        if (!response.isSuccessful()) {
            throw new BitrixApiHttpException(responseBody, response.getCode());
        }

        return new JSONObject(responseBody);
    }

    private void fireAccessTokenReceived(OAuth2AccessToken accessToken) {
        if (accessTokenListener != null) {
            accessTokenListener.onReceived(accessToken);
        }
    }

    public interface AccessTokenListener {
        void onReceived(OAuth2AccessToken accessToken);
    }

}
