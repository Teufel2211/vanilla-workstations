plugins {
    java
    // No-Remap-Loom fuer NICHT-obfuszierte Versionen (26.1+). Mojang liefert
    // lesbaren Code, darum keine Mappings (Yarn/Intermediary sind tot, enden
    // bei 1.21.11; officialMojangMappings existieren fuer 26.x nicht).
    // 1.21.x bleibt in :fabric mit Remap-Loom + Yarn (unangetastet).
    id("net.fabricmc.fabric-loom").version("1.18.2")
}

group = providers.gradleProperty("mod_group").get()
version = providers.gradleProperty("mod_version").get()

base {
    archivesName.set("vanilla-workstations-${providers.gradleProperty("minecraft_version").get()}-fabric")
}

// Geteilte Quellen: /shared (Vanilla-API) + Fabric-Initializer aus :fabric.
// Eigene resources (fabric.mod.json/quilt.mod.json mit 26.x-Constraints).
sourceSets {
    main {
        java.srcDirs("../shared/src/main/java", "../fabric/src/main/java")
        resources.srcDirs("src/main/resources")
    }
}

dependencies {
    val mc = providers.gradleProperty("minecraft_version").get()
    minecraft("com.mojang:minecraft:$mc")
    // No-Remap-Loom: Standard-Konfigurationen statt modImplementation,
    // Artefakt landet im jar-Task (remapJar existiert hier nicht).
    // Loader 0.19.5 = kanonisch fuer 26.x (fabric-example-mod/26.1);
    // Matrix ueberschreibt per -Pfabric_loader_version, leer = Default.
    val loaderVer = providers.gradleProperty("fabric_loader_version").getOrElse("0.19.5").let {
        if (it.isBlank()) "0.19.5" else it
    }
    implementation("net.fabricmc:fabric-loader:$loaderVer")
    implementation("net.fabricmc.fabric-api:fabric-api:${providers.gradleProperty("fabric_api_version").get()}")
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
