package io.cucumber.messages.ndjson;

import java.util.List;
import java.util.Optional;

abstract class ConditionalJson implements Json  {

    @Override
    public <T> Optional<Deserializer<T>> deserializer(Class<T> type) {
        return available(dependencies()) ? Optional.of(createDeserializer(type)) : Optional.empty();
    }

    abstract protected   <T> Deserializer<T> createDeserializer(Class<T> type) ;

    @Override
    public <T> Optional<Serializer<T>> serializer(Class<T> type) {
        return available(dependencies()) ? Optional.of(createSerializer(type)) : Optional.empty();
    };

    abstract protected   <T> Serializer<T> createSerializer(Class<T> type) ;

    private static boolean available(List<Dependency> dependencies) {
        try {
            for (Dependency dependency : dependencies) {
                Class.forName(dependency.className(), false, Thread.currentThread().getContextClassLoader());
            }
        } catch (ClassNotFoundException ignored) {
            return false;
        }
        return true;
    }

}
