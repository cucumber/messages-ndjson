package io.cucumber.messages.ndjson;

public interface Json {

    <T> Deserializer<T> deserializer(Class<T> type);

    <T> Serializer<T> serializer(Class<T> type);

}
