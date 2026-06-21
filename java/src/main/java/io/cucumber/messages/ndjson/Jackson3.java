package io.cucumber.messages.ndjson;

import java.util.List;

public final class Jackson3 extends AbstractJsonFactory {

    @Override
    protected <T> Deserializer<T> createDeserializer(Class<T> type) {
        return new Jackson3Json<>(type);
    }

    @Override
    protected <T> Serializer<T> createSerializer(Class<T> type) {
        return new Jackson3Json<>(type);
    }

    @Override
    List<Dependency> dependencies() {
        return List.of(
                new Dependency(
                        "tools.jackson.databind.json.JsonMapper",
                        "tools.jackson.core",
                        "jackson-databind"
                )
        );
    }
}
