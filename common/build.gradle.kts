plugins {
    `java-library`
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation(platform(project(":parent")))

    testImplementation(platform("org.junit:junit-bom"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
}

tasks.test {
    useJUnitPlatform()

    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.config.executor-service", "WORKER_THREAD_POOL")
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])

            pom {
                name.set(project.name)
                description.set("Meclody Kernel")
                url.set("https://github.com/meclody/meclody")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("sly")
                        name.set("Sly")
                        email.set("slymitec@live.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/meclody/melcody.git")
                    developerConnection.set("scm:git:ssh://git://github.com/meclody/melcody.git")
                    url.set("https://github.com/meclody/meclody")
                }
            }
        }
    }
    repositories {
        maven {
            name = "localBuild"
            url = uri(rootProject.layout.buildDirectory.dir("repo"))
        }
    }
}