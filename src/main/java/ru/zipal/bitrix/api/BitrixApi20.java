package ru.zipal.bitrix.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.Verb;

public class BitrixApi20 extends DefaultApi20 {

    private String accessTokenEndpoint;
    private String authorizationBaseUrl;

    public BitrixApi20(String accessTokenEndpoint, String authorizationBaseUrl) {
        this.accessTokenEndpoint = accessTokenEndpoint;
        this.authorizationBaseUrl = authorizationBaseUrl;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return accessTokenEndpoint;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return authorizationBaseUrl;
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }

    @Override
    public BitrixAuthService createService(String apiKey, String apiSecret, String callback, String defaultScope, String responseType, String userAgent, HttpClientConfig httpClientConfig, HttpClient httpClient) {
        return new BitrixAuthService(this, apiKey, apiSecret, callback, defaultScope, responseType, userAgent,
                httpClientConfig, httpClient);
    }
}
