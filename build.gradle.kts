import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `maven-publish`
    kotlin("jvm") version "2.1.21"
}

group = "io.github.dockyardmc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://mvn.devos.one/releases")
    maven("https://jitpack.io")
}

dependencies {
    implementation("io.github.dockyardmc:dockyard:0.9.6")
    api("dev.hollowcube:luau:0.2.2")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(23)

    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_23)
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

//publishing {
//    repositories {
//        maven {
//            uri("https://mvn.devos.one/releases")
//            credentials {
//                username = System.getenv()["MAVEN_USER"]
//                password = System.getenv()["MAVEN_PASS"]
//            }
//        }
//    }
//    publications {
//        register<MavenPublication>("maven") {
//            groupId = "io.github.dockyardmc"
//            artifactId = "luau"
//            version = version
//            from(components["java"])
//
//        }
//    }
//}