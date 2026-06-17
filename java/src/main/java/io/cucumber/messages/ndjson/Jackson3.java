package io.cucumber.messages.ndjson;

import java.util.List;

public final class Jackson3 extends DependencyAwareJsonMapperFactory {

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
        return "Jackson3";
    }

    @Override
    public List<Dependency> dependencies() {
        return List.of(
                new Dependency(
                        "tools.jackson.databind.json.JsonMapper",
                        "tools.jackson.core",
                        "jackson-databind"
                )
        );
    }
}
