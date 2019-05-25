package ru.zipal.bitrix.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;
import com.github.scribejava.core.oauth.OAuth20Service;

public class BitrixAuthService extends OAuth20Service {

    private final static String CLIENT_ID_KEY = "client_id";
    private final static String CLIENT_SECRET_KEY = "client_secret";

    public BitrixAuthService(DefaultApi20 api, String apiKey, String apiSecret, String callback, String defaultScope, String responseType, String userAgent, HttpClientConfig httpClientConfig, HttpClient httpClient) {
        super(api, apiKey, apiSecret, callback, defaultScope, responseType, userAgent, httpClientConfig, httpClient);
    }

    @Override
    protected OAuthRequest createAccessTokenRequest(AccessTokenRequestParams params) {
        OAuthRequest request = super.createAccessTokenRequest(params);

        request.addParameter(CLIENT_ID_KEY, getApiKey());
        request.addParameter(CLIENT_SECRET_KEY, getApiSecret());

        return request;
    }

    @Override
    protected OAuthRequest createRefreshTokenRequest(String refreshToken, String scope) {
        OAuthRequest request = super.createRefreshTokenRequest(refreshToken, scope);

        request.addParameter(CLIENT_ID_KEY, getApiKey());
        request.addParameter(CLIENT_SECRET_KEY, getApiSecret());

        return request;
    }
}
