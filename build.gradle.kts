plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.spring.io/snapshot")
        mavenContent {
            snapshotsOnly()
        }
    }
}

dependencyManagement {
    imports {
        mavenBom("io.micrometer:micrometer-tracing-bom:1.2.3-SNAPSHOT")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

    implementation("org.springframework.cloud:spring-cloud-gateway:4.1.1")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.1.1")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.security:spring-security-config")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-tracing")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
}


