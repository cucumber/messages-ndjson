package io.cucumber.messages.ndjson;

import java.util.List;

public final class Jackson2JsonProvider extends AbstractJsonProvider {

    @Override
    protected Json createInstance() {
        return new Jackson2();
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

    @Override
    public String name() {
        return "Jackson 2";
    }
}
