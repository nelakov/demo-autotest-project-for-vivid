import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    `java-library`
    id("io.qameta.allure") version "4.1.0"
}

repositories {
    mavenCentral()
}

val allureVersion = "2.35.2"
val selenideVersion = "7.16.2"
val junitVersion = "5.14.4"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

allure {
    report {
        version.set(allureVersion)
    }
    adapter {
        autoconfigure.set(true)
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set(allureVersion)
            }
        }
    }
}

dependencies {
    testImplementation("org.aspectj:aspectjweaver:1.9.25.1")
    testImplementation("com.codeborne:selenide:$selenideVersion")
    testImplementation("io.qameta.allure:allure-selenide:$allureVersion")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("net.datafaker:datafaker:2.5.4")

    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.18")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.withType<Test>().configureEach {
    systemProperties(System.getProperties().entries.associate { it.key.toString() to it.value })
    useJUnitPlatform()

    if (System.getProperty("threads") != null) {
        systemProperties(
            mapOf(
                "junit.jupiter.execution.parallel.enabled" to true,
                "junit.jupiter.execution.parallel.mode.default" to "concurrent",
                "junit.jupiter.execution.parallel.mode.classes.default" to "concurrent",
                "junit.jupiter.execution.parallel.config.strategy" to "fixed",
                "junit.jupiter.execution.parallel.config.fixed.parallelism" to System.getProperty("threads").toInt()
            )
        )
    }

    testLogging {
        events("started", "skipped", "failed", "standard_error", "standard_out")
        exceptionFormat = TestExceptionFormat.SHORT
    }
}
