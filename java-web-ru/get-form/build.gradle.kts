plugins {
    application
    id("com.adarshr.test-logger") version "3.2.0"
    id("io.freefair.lombok") version "8.1.0"
}

application {
    mainClass.set("exercise.App")
}

group = "exercise"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:5.6.1")

    implementation("gg.jte:jte:3.0.1")
    implementation("io.javalin:javalin-rendering:5.6.2")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation ("com.github.javafaker:javafaker:1.0.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.konghq:unirest-java:3.13.0")
    testImplementation("org.hamcrest:hamcrest-core:2.2")
}

tasks.test {
    useJUnitPlatform()
}

testlogger {
  showStandardStreams = true
}
