package io.cucumber.messages.ndjson;

import java.util.List;
import java.util.Optional;

public interface Json {

    default <T> Optional<Deserializer<T>> deserializer(Class<T> type){
        return Optional.empty();
    }

    default <T> Optional<Serializer<T>> serializer(Class<T> type) {
        return Optional.empty();
    }

    String name();

    List<Dependency> dependencies();

    record Dependency(String className, String groupId, String artifactId) {

    }
}
