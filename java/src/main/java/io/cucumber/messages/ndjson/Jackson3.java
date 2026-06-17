package io.cucumber.messages.ndjson;

public final class Jackson3 extends ConditionalJson {

    private static final String EXPECTED_CLASS = "tools.jackson.databind.json.JsonMapper";

    public Jackson3() {
        super(EXPECTED_CLASS);
    }

    @Override
    protected <T> Deserializer<T> createDeserializer(Class<T> type) {
        return new Jackson3JsonMapper<>(type);
    }

    @Override
    protected <T> Serializer<T> createSerializer(Class<T> type) {
        return new Jackson3JsonMapper<>(type);
    }

    @Override
    public String name() {
        return EXPECTED_CLASS;
    }
}
