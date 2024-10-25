plugins {
    kotlin("jvm") version "2.0.20"
}

group = "org.hvi2ist"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.addAll("-Xwhen-guards","-Xmulti-dollar-interpolation", "-Xnon-local-break-continue", "-Xcontext-receivers")
    }
}