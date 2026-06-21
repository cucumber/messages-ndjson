package io.cucumber.messages.ndjson;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

final class JsonFactoryInstance {

    private JsonFactoryInstance() {
        /* no-op */
    }

    static JsonFactory instance() {
        var factories = ServiceLoader.load(JsonFactory.class).stream()
                .map(ServiceLoader.Provider::get)
                .toList();

        return factories.stream()
                .filter(JsonFactory::enabled)
                .findFirst()
                .orElseThrow(() -> createNoEnabledFactories(factories));
    }

    private static RuntimeException createNoEnabledFactories(List<AbstractJsonFactory> candidates) {
        var details = candidates.stream().map(factory -> {
                    var name = factory.getClass().getSimpleName();
                    var dependencies = factory.dependencies().stream()
                            .map(dependency -> "%s from %s:%s".formatted(dependency.className(), dependency.groupId(), dependency.artifactId()))
                            .collect(Collectors.joining("\n", "* ", ""));

                    return """
                            %s which requires
                            %s
                            """.formatted(name, dependencies);
                })
                .collect(Collectors.joining("\n", "", ""));

        return new RuntimeException("""
                Could not create a JsonFactory, there were not suitable JSON libraries available.
                
                Currently recognized libraries are:
                %s
                
                Please ensure one of these is available on the class or module path.
                """.formatted(details));
    }
}
