plugins {
    id("fabric-loom").version("1.7.4")
}

group = providers.gradleProperty("mod_group").get()
version = providers.gradleProperty("mod_version").get()

base {
    archivesName.set("vanilla-workstations-${providers.gradleProperty("minecraft_version").get()}-fabric")
}

// Geteilte Quellen aus /shared (reines Vanilla-API, kein Loader-Code).
sourceSets {
    main {
        java.srcDirs("../shared/src/main/java", "src/main/java")
        resources.srcDirs("../shared/src/main/resources", "src/main/resources")
    }
}

dependencies {
    val mc = providers.gradleProperty("minecraft_version").get()
    val yarn = providers.gradleProperty("yarn_mappings").get()
    minecraft("com.mojang:minecraft:$mc")
    mappings("net.fabricmc:yarn:$yarn:v2")
    modImplementation("net.fabricmc:fabric-loader:${providers.gradleProperty("fabric_loader_version").get()}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${providers.gradleProperty("fabric_api_version").get()}")
}

tasks.withType<ProcessResources> {
    val modMap = mapOf(
        "version" to providers.gradleProperty("mod_version").get(),
        "minecraft" to providers.gradleProperty("minecraft_version").get()
    )
    inputs.properties(modMap)
    filesMatching(listOf("fabric.mod.json", "quilt.mod.json")) {
        expand(modMap)
    }
}
