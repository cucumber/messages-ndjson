package io.cucumber.messages.ndjson;

public interface JsonFactory {

    <T> Deserializer<T> deserializer(Class<T> type);

    <T> Serializer<T> serializer(Class<T> type);

    boolean enabled();

    static JsonFactory instance() {
        return JsonFactoryInstance.instance();
    }

}
