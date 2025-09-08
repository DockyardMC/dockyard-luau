import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `maven-publish`
    kotlin("jvm") version "2.1.21"
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
}

group = "io.github.dockyardmc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://mvn.devos.one/releases")
    maven("https://mvn.devos.one/snapshots")
    maven("https://jitpack.io")
    maven("https://repo.spongepowered.org/repository/maven-public/")
    maven("https://repo.viaversion.com")
    maven("https://maven.noxcrew.com/public")
}

dependencies {
    implementation("io.github.dockyardmc:dockyard:0.10.7")
    api("com.github.MayaChen350:LuauLayer:fb35d797f3")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(23)
    explicitApi()

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