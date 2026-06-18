package io.cucumber.messages.ndjson;

import java.util.List;

abstract class DependencyAwareJsonFactory implements JsonFactory {

    private volatile boolean doneOnce;
    private boolean available;

    @Override
    public <T> Deserializer<T> deserializer(Class<T> type) {
        if (dependenciesAvailable()) {
            return createDeserializer(type);
        }
        throw createMissingDependencies("deserializer");
    }

    abstract protected <T> Deserializer<T> createDeserializer(Class<T> type);

    @Override
    public <T> Serializer<T> serializer(Class<T> type) {
        if (dependenciesAvailable()) {
            return createSerializer(type);
        }
        throw createMissingDependencies("serializer");
    }

    abstract protected <T> Serializer<T> createSerializer(Class<T> type);

    @Override
    public boolean dependenciesAvailable() {
        if (!doneOnce) {
            synchronized (this) {
                if (!doneOnce) {
                    available = check(dependencies());
                    doneOnce = true;
                }
            }
        }
        return available;
    }

    private static boolean check(List<Dependency> dependencies) {
        try {
            for (Dependency dependency : dependencies) {
                Class.forName(dependency.className(), false, Thread.currentThread().getContextClassLoader());
            }
        } catch (ClassNotFoundException ignored) {
            return false;
        }
        return true;
    }

    private RuntimeException createMissingDependencies(String what) {
        return new RuntimeException("Could not create %s. Not all required dependencies are available.".formatted(what));
    }

}
