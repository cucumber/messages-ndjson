package io.cucumber.messages.ndjson;

import java.util.List;
import java.util.Optional;

/**
 * A dependency-aware {@link Json} provider.
 *
 * <p>Cucumber creates reports using JSON but Java doesn't have a standard JSON
 * implementation and the JSR 353 jsonb-api doesn't seem to be going anywhere
 * yet. As a testing library Cucumber also can't just depend on Jackson, Gson or
 * any other library. It might influence the system under test. As such Cucumber
 * has  to use what is available.
 *
 * <p>A {@code JsonProvider} can be used through SPI and may optionally provide a
 * {@code Json} implementation if the dependencies for that implementation are
 * available on the class or module path.
 */
public interface JsonProvider {

    /**
     * Provides a {@link Json} instance if the {@link #dependencies()} are available
     * on the class or module path. Otherwise, empty.
     */
    Optional<Json> instance();

    /**
     * The dependencies that should be available on the class or module path
     * required to provide a working {@code Json} instance.
     *
     * @return dependencies that should be available on the class or module path.
     */
    List<Dependency> dependencies();

    record Dependency(String className, String groupId, String artifactId) {

    }

}
