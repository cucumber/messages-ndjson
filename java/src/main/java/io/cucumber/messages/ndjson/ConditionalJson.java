package io.cucumber.messages.ndjson;

import java.util.Optional;

abstract class ConditionalJson implements Json  {

    private final String expectedClassName;

    ConditionalJson(String expectedClassName) {
        this.expectedClassName = expectedClassName;
    }

    @Override
    public <T> Optional<Deserializer<T>> deserializer(Class<T> type) {
        return isSupported(expectedClassName) ? Optional.of(createDeserializer(type)) : Optional.empty();
    }

    abstract protected   <T> Deserializer<T> createDeserializer(Class<T> type) ;

    @Override
    public <T> Optional<Serializer<T>> serializer(Class<T> type) {
        return isSupported(expectedClassName) ? Optional.of(createSerializer(type)) : Optional.empty();
    };

    abstract protected   <T> Serializer<T> createSerializer(Class<T> type) ;

    private static boolean isSupported(String expectedClass) {
        try {
            Class.forName(expectedClass, false, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException ignored) {
            return false;
        }
        return true;
    }

}
