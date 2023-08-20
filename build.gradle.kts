plugins {
    java
    id("org.springframework.boot") version ("3.1.2")
}

group = "com.github.mikybars.demo"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.shell:spring-shell-dependencies:3.1.3"))
    constraints {
        implementation("org.yaml:snakeyaml:]1.33,)") {
            because("CVE-2022-41854")
        }
    }
    implementation("org.springframework.shell:spring-shell-starter")
}

tasks.compileJava {
    options.compilerArgs.add("-parameters")
}