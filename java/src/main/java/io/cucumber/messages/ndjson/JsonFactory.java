package io.cucumber.messages.ndjson;

import java.util.List;

public interface JsonFactory {

    <T> Deserializer<T> deserializer(Class<T> type);

    <T> Serializer<T> serializer(Class<T> type);

    String name();

    List<Dependency> dependencies();

    boolean enabled();

    record Dependency(String className, String groupId, String artifactId) {

    }

}
