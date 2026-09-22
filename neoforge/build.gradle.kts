plugins {
    `java-library`
    id("net.neoforged.moddev").version("2.0.147")
}

group = providers.gradleProperty("mod_group").get()
version = providers.gradleProperty("mod_version").get()

base {
    archivesName.set("vanilla-workstations-${providers.gradleProperty("minecraft_version").get()}-neoforge")
}

// Geteilte Quellen aus /shared (reines Vanilla-API, kein Loader-Code).
sourceSets {
    main {
        java.srcDirs("../shared/src/main/java", "src/main/java")
        resources.srcDirs("../shared/src/main/resources", "src/main/resources")
    }
}

neoForge {
    version = providers.gradleProperty("neoforge_version").get()
    mods {
        create("vanilla_workstations") {
            sourceSet(sourceSets.main.get())
        }
    }
}
