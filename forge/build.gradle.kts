// Forge nur fuer 1.21.x. Kein 26.x CalVer-Support durch Forge.
// Fuer 26.x bitte den NeoForge-Build nutzen.
plugins {
    `java-library`
    id("net.minecraftforge.gradle").version("7.0.40")
}

group = providers.gradleProperty("mod_group").get()
version = providers.gradleProperty("mod_version").get()

base {
    archivesName.set("vanilla-workstations-${providers.gradleProperty("minecraft_version").get()}-forge")
}

// Geteilte Quellen aus /shared (reines Vanilla-API, kein Loader-Code).
sourceSets {
    main {
        java.srcDirs("../shared/src/main/java", "src/main/java")
        resources.srcDirs("../shared/src/main/resources", "src/main/resources")
    }
}

minecraft {
    mappings(channel = "official", version = providers.gradleProperty("minecraft_version").get())
    runs {
        create("client") {
            workingDirectory(project.file("run"))
            mods {
                create("vanilla_workstations") {
                    source(sourceSets.main.get())
                }
            }
        }
        create("server") {
            workingDirectory(project.file("run"))
            mods {
                create("vanilla_workstations") {
                    source(sourceSets.main.get())
                }
            }
        }
    }
}

dependencies {
    val mc = providers.gradleProperty("minecraft_version").get()
    val fg = providers.gradleProperty("forge_version").get()
    minecraft("net.minecraftforge:forge:$mc-$fg")
}
