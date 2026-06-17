package io.cucumber.messages.ndjson.test;

public class ClassLoaderUtil {
    public static void executeWith(ClassLoader classLoader, Runnable runnable) {
        var original = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
        try {
            runnable.run();
        } finally {
            Thread.currentThread().setContextClassLoader(original);
        }
    }
}
