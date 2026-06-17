package io.cucumber.messages.ndjson.test;

class FilteredClassLoader extends ClassLoader {
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
