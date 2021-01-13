# quarkus-ddd project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-ddd-1.0.0-SNAPSHOT-runner.jar` file in the `/build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar build/quarkus-ddd-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-ddd-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

# Config example

<p>This example displays mach speed in your favourite unit, depending on the specified Quarkus configuration.</p>
<p>The Quarkus configuration is located in: <code>src/main/resources/application.yml</code></p>
<p><b>Supersonic!</b></p>
Guide: https://quarkus.io/guides/config#yaml

# Logging JSON

<p>This example let you go faster with your jet aircraft, your speed is logged when you send a new request.</p>
<p>When you reach the speed of sound, a "Sonic Boom" error is going to be thrown and logged.</p>
<p><b>Boom!</b></p>

Guide: https://quarkus.io/guides/logging#json-logging

# RESTEasy JSON serialisation using Jackson

<p>This example demonstrate RESTEasy JSON serialisation by letting you list, add and remove quark types from a list.</p>
<p><b>Quarked!</b></p>

Guide: https://quarkus.io/guides/rest-json
