plugins {
    java
    id("org.springframework.boot") version ("3.1.2")
    id("org.graalvm.buildtools.native") version("0.9.16")
}

group = "com.github.mikybars.demo"
version = "1.0.0"

repositories {
    mavenCentral()
}

springBoot {
    buildInfo()
}

dependencies {
    implementation(platform("org.springframework.shell:spring-shell-dependencies:3.1.3"))
    constraints {
        implementation("org.yaml:snakeyaml:]1.33,)") {
            because("CVE-2022-41854")
        }
    }
    implementation("org.springframework.shell:spring-shell-starter")

    testImplementation("org.springframework:spring-test:6.0.1")
    testImplementation("org.springframework.shell:spring-shell-test")
    testImplementation("org.springframework.shell:spring-shell-test-autoconfigure")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.awaitility:awaitility:4.2.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.compileJava {
    options.compilerArgs.add("-parameters")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

graalvmNative {
    metadataRepository {
        enabled.set(true)
    }
}