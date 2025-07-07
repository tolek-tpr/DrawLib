To add this repository to your project use jitpack.

Gradle:
```groovy
repositorier {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation("com.github.tolek-tpr:drawlib:VERSION")
}
```

Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.tolek-tpr</groupId>
    <artifactId>drawlib</artifactId>
    <version>VERSION</version>
</dependency>
```