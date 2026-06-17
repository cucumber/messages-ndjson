package io.cucumber.messages.ndjson;

import java.util.List;

public final class Jackson2 extends ConditionalJson {

    private static final List<Dependency> DEPENDENCIES = List.of(
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

    public Jackson2(){
        /* no-op */
    }

    @Override
    protected <T> Deserializer<T> createDeserializer(Class<T> type) {
        return new Jackson2JsonMapper<>(type);
    }

    @Override
    protected <T> Serializer<T> createSerializer(Class<T> type) {
        return new Jackson2JsonMapper<>(type);
    }

    @Override
    public String name() {
        return "Jackson2";
    }

    @Override
    public List<Dependency> dependencies() {
        return DEPENDENCIES;
    }
}
