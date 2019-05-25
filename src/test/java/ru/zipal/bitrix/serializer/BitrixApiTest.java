package ru.zipal.bitrix.serializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.zipal.bitrix.api.BitrixApi;
import ru.zipal.bitrix.api.BitrixApiException;
import ru.zipal.bitrix.api.BitrixClient;
import ru.zipal.bitrix.api.model.BitrixDeal;
import ru.zipal.bitrix.api.model.enums.EntityType;

public class BitrixApiTest {

    private final static String DOMAIN = "test121.bitrix24.ru";
    private final static String API_KEY = "local.5ce17f30218184.70754592";
    private final static String API_SECRET = "XXX";
    private final static String REDIRECT_URI = "http://localhost:8080";
    private final static String ACCESS_TOKEN = "9bd6e55c003c5234003c34c200000001100e03566d6a9ef83dce86d998f5e50280c577";

    private BitrixApi bitrixApi;

    @Before
    public void setup() {
        BitrixClient bitrixClient = new BitrixClient(DOMAIN, API_KEY, API_SECRET, REDIRECT_URI);
        bitrixApi = new BitrixApi(bitrixClient);
        bitrixApi.setAccessToken(ACCESS_TOKEN, null);
    }

    @Test
    public void retrieveDeal() throws BitrixApiException {
        BitrixDeal deal = bitrixApi.get(EntityType.DEAL, 1);
        Assert.assertNotNull(deal.getId());
        System.out.println(deal.getId());
    }

    @Test
    public void updateDeal() throws BitrixApiException {
        BitrixDeal deal = bitrixApi.get(EntityType.DEAL, 1);
        deal.setComments("test");
        bitrixApi.update(EntityType.DEAL, deal);
        deal = bitrixApi.get(EntityType.DEAL, 1);
        Assert.assertEquals("test", deal.getComments());
        System.out.println(deal.getId());
    }

    @Test
    public void createDeal() throws BitrixApiException {
        BitrixDeal deal = new BitrixDeal();
        deal.setTitle("test deal");
        Long id = bitrixApi.create(EntityType.DEAL, deal);
        Assert.assertNotNull(id);
        deal = bitrixApi.get(EntityType.DEAL, id);
        Assert.assertNotNull(deal.getId());
        System.out.println(deal.getId());
    }

    @Test
    public void removeDeal() throws BitrixApiException {
        bitrixApi.delete(EntityType.DEAL, 11);
    }

}
