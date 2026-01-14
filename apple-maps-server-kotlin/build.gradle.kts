import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "2.3.0"
    id("com.vanniktech.maven.publish") version "0.26.0"
    id("org.jetbrains.dokka") version "2.1.0"

    `java-library`
}

group = "com.doorbit"
version = "0.3.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(platform("tools.jackson:jackson-bom:3.0.3"))
    implementation("tools.jackson.module:jackson-module-kotlin")
    implementation("tools.jackson.core:jackson-databind")
}

mavenPublishing {
    configure(KotlinJvm(
        javadocJar = JavadocJar.Dokka("dokkaHtml"),
        // whether to publish a sources jar
        sourcesJar = true,
    ))
    publishToMavenCentral(SonatypeHost.S01, true)
    if (System.getenv("JITPACK") != "true") {
        signAllPublications()
    }
}