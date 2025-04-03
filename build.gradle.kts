plugins {
    id("java")
    id("maven-publish")
}

group = "pl.epsi"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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