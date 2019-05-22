package ru.zipal.bitrix.api.serialize;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.zipal.bitrix.api.BitrixApiException;
import ru.zipal.bitrix.api.model.BitrixEntity;

import java.util.List;

public interface Serializer {
    <T extends BitrixEntity> List<T> deserializeArray(Class<T> entityClass, JSONArray array) throws BitrixApiException;
    <T extends BitrixEntity> T deserialize(Class<T> entityClass, JSONObject json) throws BitrixApiException;
    List<NameValuePair> serialize(Object obj) throws BitrixApiException;
}
