// Import necessary Kotlin compilation task from the Kotlin Gradle plugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Apply necessary plugins for Kotlin and Spring Boot
plugins {
    // Apply the Kotlin JVM plugin to support Kotlin in the JVM environment
    kotlin("jvm") version "1.5.31" apply true
    kotlin("plugin.spring") version "1.5.31"
    // Apply the Spring Boot plugin to manage bootstrapping and runtime configurations
    id("org.springframework.boot") version "2.6.15"
    // Apply the Dependency Management plugin for managing dependencies versions more effectively
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    // for war
    id("war")
}

// Set the group ID and version for the project artifacts
group = "org.prowikiq"
version = "0.0.1-SNAPSHOT"

// Define Java compatibility to ensure your codebase aligns with a specific Java version
java.sourceCompatibility = JavaVersion.VERSION_11

// Specify repositories for fetching dependencies
repositories {
    // Use Maven Central as a primary source for library artifacts
    mavenCentral()
    // Additional Spring repository for accessing Spring-specific releases
    maven {
        url = uri("https://repo.spring.io/release")
    }
}

dependencies {
    // Framework
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // Database
    implementation("org.postgresql:postgresql")
    // JSON Handling
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // Kotlin Libraries
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    // lombok
    compileOnly("org.projectlombok:lombok:1.18.24") // 최신 버전으로 업데이트
    annotationProcessor("org.projectlombok:lombok:1.18.24") // 최신 버전으로 업데이트
    //api Doc
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    //jwt
    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation ("io.jsonwebtoken:jjwt:0.9.1")
    // test for Mockito
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.h2database:h2:1.4.200") // Use runtimeOnly if needed at runtime
    //testImplementation("org.mockito:mockito-core:4.5.1") // Use the latest version available
}
tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:unchecked")
    options.encoding = "UTF-8"
}

// Configure Kotlin compilation to specify target JVM and additional compiler arguments
tasks.withType<KotlinCompile> {
    kotlinOptions {
        // Target JVM version for compilation to ensure compatibility
        jvmTarget = "11"
        // Compiler arguments for enhanced interoperability and safety
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

// Configure testing tasks to use JUnit Platform for modern testing features
tasks.withType<Test> {
    useJUnitPlatform()
}

// Spring Boot specific configurations for managing application properties and additional tasks
springBoot {
    // Generate build information for diagnostics and reporting
    buildInfo()
}

// for war
//// Configure the build to produce a WAR file instead of a JAR file
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootWar>("bootWar") {
    enabled = true
}