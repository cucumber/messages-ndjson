package io.cucumber.messages.ndjson.test;

final class ClassLoaderUtil {
    static void executeWith(ClassLoader classLoader, Runnable runnable) {
        var original = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
        try {
            runnable.run();
        } finally {
            Thread.currentThread().setContextClassLoader(original);
        }
    }

    static final class FilteredClassLoader extends ClassLoader {
        private final String prefix;

        FilteredClassLoader(String prefix) {
            this.prefix = prefix;
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

            if (name.startsWith(prefix)) {
                throw new ClassNotFoundException();
            }
            return super.loadClass(name, resolve);
        }
    }
}
