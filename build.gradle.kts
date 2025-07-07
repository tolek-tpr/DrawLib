plugins {
    id("java")
    id("maven-publish")
}

group = "pl.epsi"
version = "1.0.2"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.21.0") // Log4J2

    compileOnly("org.jetbrains:annotations:26.0.2")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "pl.epsi"
            artifactId = "drawlib"
            version = project.version.toString()
        }
    }
    repositories {
        mavenLocal()
    }
}