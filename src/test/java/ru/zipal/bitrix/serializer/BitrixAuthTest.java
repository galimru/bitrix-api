package ru.zipal.bitrix.serializer;

import org.junit.Assert;
import org.junit.Test;
import ru.zipal.bitrix.api.BitrixApi;
import ru.zipal.bitrix.api.BitrixApiException;
import ru.zipal.bitrix.api.BitrixClient;

public class BitrixAuthTest {

    private final static String DOMAIN = "test121.bitrix24.ru";
    private final static String API_KEY = "local.5ce17f30218184.70754592";
    private final static String API_SECRET = "XXX";
    private final static String REDIRECT_URI = "http://localhost:8080";

    @Test
    public void setup() throws BitrixApiException {
        BitrixClient bitrixClient = new BitrixClient(DOMAIN, API_KEY, API_SECRET, REDIRECT_URI);
        BitrixApi bitrixApi = new BitrixApi(bitrixClient);
        String authorizationUrl = bitrixApi.getAuthorizationUrl();

        System.out.println("OAuth Flow");
        System.out.println("Authorization Url: " + authorizationUrl);

        //Scanner in = new Scanner(System.in, "UTF-8");
        //String code = in.nextLine();

        String code = "9cc8e55c003c5234003c34c200000001100e0323274fba75700edcc075b25b215f9fd8";

        bitrixApi.authorize(code);

        String accessToken = bitrixApi.getAccessToken();

        Assert.assertNotNull(accessToken);

        System.out.println("Access Token: " + accessToken);
    }

}
