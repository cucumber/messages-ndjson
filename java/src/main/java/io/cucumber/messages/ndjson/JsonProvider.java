package io.cucumber.messages.ndjson;

import java.util.List;
import java.util.Optional;

public interface JsonProvider {

    Optional<Json> instance();

    List<Dependency> dependencies();

    record Dependency(String className, String groupId, String artifactId) {

    }

}
