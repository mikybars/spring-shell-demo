## What is Spring Shell?

> The Spring Shell project provides an interactive shell for processing commands and building a full-featured CLI using
the Spring programming model.

https://spring.io/projects/spring-shell

## How to use this demo

### With Java

```shell
./gradlew build
```

```shell
java -jar build/libs/spring-shell-demo-1.0.0.jar
my-shell:>
```

### Run native with GraalVM

```shell
sdk install java 17.0.8-graalce
sdk use java 17.0.8-graalce
./gradlew nativeCompile
...
========================================================================================================================
GraalVM Native Image: Generating 'spring-shell-demo' (executable)...
========================================================================================================================
...
[native-image-plugin] Native Image written to: [...]/build/native/nativeCompile
```

```shell
build/native/nativeCompile/spring-shell-demo
my-shell:>
```