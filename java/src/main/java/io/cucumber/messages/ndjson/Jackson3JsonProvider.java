package io.cucumber.messages.ndjson;

import java.util.List;

public final class Jackson3JsonProvider extends AbstractJsonProvider{

    @Override
    protected Json createInstance() {
        return new Jackson3();
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

    @Override
    public String name() {
        return "Jackson 3";
    }
}
