package io.cucumber.messages.ndjson;

import java.util.List;

public final class Jackson2 extends DependencyAwareJsonFactory {

    @Override
    protected <T> Deserializer<T> createDeserializer(Class<T> type) {
        return new Jackson2Json<>(type);
    }

    @Override
    protected <T> Serializer<T> createSerializer(Class<T> type) {
        return new Jackson2Json<>(type);
    }

    @Override
    public String name() {
        return "Jackson2";
    }

    @Override
    public List<Dependency> dependencies() {
        return List.of(
                new Dependency(
                        "com.fasterxml.jackson.databind.json.JsonMapper",
                        "com.fasterxml.jackson.core",
                        "jackson-databind"
                ),
                new Dependency(
                        "com.fasterxml.jackson.datatype.jdk8.Jdk8Module",
                        "com.fasterxml.jackson.datatype",
                        "jackson-datatype-jdk8"
                )
        );
    }
}
