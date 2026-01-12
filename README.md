⚠️ This is an internal package; you don't need to install it in order to use the JSON Formatter.

[![Maven Central](https://img.shields.io/maven-central/v/io.cucumber/messages-json.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:io.cucumber%20AND%20a:messages-json)

# Messages JSON

Serialize and deserialize [Cucumber Messages](https://github.com/cucumber/messages/)
to and from JSON.

This functionality is intentionally kept separate from Cucumber messages. The
backing (de-)serialisation is provided by [Jackson](https://github.com/FasterXML/jackson)
which gets frequent updates. As messages is used by every other project in the
Cucumber ecosystem including  could result a cascade of releases. 

This limits the release cascade to only those projects that use Jackson in
production code.