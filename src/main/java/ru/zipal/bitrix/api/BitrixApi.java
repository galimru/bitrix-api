package ru.zipal.bitrix.api;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.zipal.bitrix.api.model.*;
import ru.zipal.bitrix.api.model.enums.EntityType;
import ru.zipal.bitrix.api.serialize.DefaultSerializer;
import ru.zipal.bitrix.api.serialize.Serializer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class BitrixApi {

    private final Logger logger = LoggerFactory.getLogger(BitrixApi.class);

    private final BitrixClient client;
    private final Serializer serializer;

    private Map<EntityType, Class<? extends BitrixEntity>> registeredEntities = new HashMap<>();

    public BitrixApi(BitrixClient client) {
        this(client, new DefaultSerializer());
    }

    public BitrixApi(BitrixClient client, Serializer serializer) {
        this.client = client;
        this.serializer = serializer;

        registerDefaultEntities();
    }

    public BitrixClient getClient() {
        return client;
    }

    public void registerEntity(EntityType entityType, Class<? extends BitrixEntity> entityClass) {
        registeredEntities.put(entityType, entityClass);
    }

    private void registerDefaultEntities() {
        registerEntity(EntityType.DEAL, BitrixDeal.class);
        // TODO add default entities
    }

    public String getAuthorizationUrl() {
        return client.getAuthorizationUrl();
    }

    public void setAccessToken(String accessToken, String refreshToken) {
        client.setAccessToken(accessToken, refreshToken);
    }

    public String getAccessToken() throws BitrixApiException {
        return client.getAccessToken();
    }

    public String getRefreshToken() throws BitrixApiException {
        return client.getRefreshToken();
    }

    public void authorize(String code) {
        try {
            client.authorize(code);
        } catch (InterruptedException | ExecutionException | IOException e) {
            logger.error("An error occurred while authorize request");
        }
    }

    public <T extends BitrixEntity> Map<Long, T> get(EntityType entityType, Collection<Long> ids) throws BitrixApiException {
        Class<T> entityClass = getEntityClass(entityType);
        return getBatch(entityClass, ids, getMethodName(entityType, BitrixOperation.GET));
    }

    private  <T extends BitrixEntity> Map<Long, T> getBatch(Class<T> entityClass, Collection<Long> ids, String method) throws BitrixApiException {
        final JSONObject json = client.post("batch", ids.stream()
                .map(id -> new BasicNameValuePair("cmd[e_" + id + "]", method + "?ID=" + id))
                .collect(Collectors.toList()));

        final JSONObject result = json.getJSONObject("result").getJSONObject("result");

        final HashMap<Long, T> map = new HashMap<>();
        for (Long id : ids) {
            if (result.has("e_" + id)) {
                map.put(id, serializer.deserialize(entityClass, result.getJSONObject("e_" + id)));
            }
        }

        return map;
    }

    private Map<Pair<EntityType, Long>, Object> getBatch(Collection<Pair<EntityType, Long>> ids) throws BitrixApiException {
        final JSONObject json = client.post("batch", ids.stream()
                .map(pair -> new BasicNameValuePair("cmd[e_" + pair.getValue() + "]", getMethodName(pair.getKey(), BitrixOperation.GET) + "?ID=" + pair.getValue()))
                .collect(Collectors.toList()));
        final JSONObject result = json.getJSONObject("result").getJSONObject("result");
        final HashMap<Pair<EntityType, Long>, Object> map = new HashMap<>();
        for (Pair<EntityType, Long> pair : ids) {
            if (result.has("e_" + pair.getValue())) {
                map.put(pair, serializer.deserialize(getEntityClass(pair.getKey()), result.getJSONObject("e_" + pair.getValue())));
            }
        }
        return map;
    }

    public <T extends BitrixEntity> T get(EntityType entityType, long id) throws BitrixApiException {
        Class<T> entityClass = getEntityClass(entityType);
        return serializer.deserialize(entityClass, client.post(
                getMethodName(entityType, BitrixOperation.GET),
                Collections.singletonList(new BasicNameValuePair("id", Long.toString(id))))
                .getJSONObject("result"));
    }

    public <T extends BitrixEntity> Long create(EntityType entityType, T entity) throws BitrixApiException {
        return client.post(
                getMethodName(entityType, BitrixOperation.ADD),
                serializer.serialize(entity)
        ).getLong("result");
    }

    public <T extends BitrixEntity> void update(EntityType entityType, T entity) throws BitrixApiException {
        List<NameValuePair> params = serializer.serialize(entity);
        params.add(new BasicNameValuePair("id", Long.toString(entity.getId())));

        client.post(
                getMethodName(entityType, BitrixOperation.UPDATE),
                params
        );
    }

    public void delete(EntityType entityType, long id) throws BitrixApiException {
        client.post(
                getMethodName(entityType, BitrixOperation.DELETE),
                Collections.singletonList(new BasicNameValuePair("id", Long.toString(id)))
        );
    }

    private <T extends BitrixEntity> BitrixPage<T> list(EntityType entityType, Integer start, NameValuePair... additional) throws BitrixApiException {
        final List<NameValuePair> params = new ArrayList<>();
        if (start != null) {
            params.add(new BasicNameValuePair("start", start.toString()));
        }
        if (additional != null) {
            params.addAll(Arrays.asList(additional));
        }
        Class<T> entityClass = getEntityClass(entityType);
        return getPage(client.post(getMethodName(entityType, BitrixOperation.LIST), params), entityClass);
    }

    private <T extends BitrixEntity> BitrixPage<T> getPage(JSONObject json, Class<T> entityClass) throws BitrixApiException {
        final JSONArray array = json.getJSONArray("result");
        Integer next = json.has("next") ? json.getInt("next") : null;
        return new BitrixPage<>(next, serializer.deserializeArray(entityClass, array));
    }

    @SuppressWarnings("unchecked")
    private <T extends BitrixEntity> Class<T> getEntityClass(EntityType entityType) {
        return (Class<T>) registeredEntities.get(entityType);
    }

    private String getMethodName(EntityType entityType, BitrixOperation operation) {
        return entityType.getName() + "." + operation.getName();
    }

}
