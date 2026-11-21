plugins {
    base
}

val projectVersion = "1.0.0.RELEASE"
val projectNameSpace = "com.meclody"

allprojects {
    group = projectNameSpace
    version = projectVersion
}

subprojects {
    pluginManager.apply("maven-publish")

    repositories {
        mavenCentral()
    }

    tasks.withType<Javadoc>().configureEach {
        (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:-missing", "-quiet")
    }

    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.MINUTES)
    }

    plugins.withType(JavaPlugin::class).configureEach {
        extensions.configure<JavaPluginExtension>("java") {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(25))
            }
        }
    }
}

defaultTasks("build")