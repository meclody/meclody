plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("org.junit:junit-bom:6.1.3"))

    constraints {
        api("io.dapr:dapr-sdk-actors:1.19.1")
        api("io.dapr:dapr-sdk-springboot:1.19.1")

        api("org.assertj:assertj-core:3.27.7")
        api("org.mockito:mockito-junit-jupiter:5.24.0")
    }
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["javaPlatform"])

            pom {
                name.set(project.name)
                description.set("Meclody Parent")
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