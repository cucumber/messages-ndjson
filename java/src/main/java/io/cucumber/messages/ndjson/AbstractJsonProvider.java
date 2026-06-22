package io.cucumber.messages.ndjson;

import java.util.List;
import java.util.Optional;

abstract class AbstractJsonProvider implements JsonProvider {

    private volatile boolean doneOnce;
    private boolean available;

    @Override
    public final Optional<Json> instance() {
        return enabled() ? Optional.of(createInstance()) :  Optional.empty();
    }

    protected abstract Json createInstance();

    public final boolean enabled() {
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
}
