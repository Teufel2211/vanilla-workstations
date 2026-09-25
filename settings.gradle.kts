pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://maven.architectury.dev/") }
        maven { url = uri("https://maven.fabricmc.net/") }
        maven { url = uri("https://maven.neoforged.net/releases/") }
        maven { url = uri("https://maven.minecraftforge.net/") }
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://maven.fabricmc.net/") }
        maven { url = uri("https://maven.neoforged.net/releases/") }
        maven { url = uri("https://maven.minecraftforge.net/") }
        maven { url = uri("https://maven.quiltmc.org/repository/release/") }
    }
}

// Kein :common-Modul: geteilte Quellen liegen in /shared und werden
// pro Loader per srcDir eingebunden (kein Architectury noetig, alles Vanilla-API).
// :fabric26 = zweites Fabric-Modul fuer 26.x (No-Remap-Loom
// net.fabricmc.fabric-loom, keine Mappings); teilt sich Quellen mit :fabric.
rootProject.name = "vanilla-workstations"
include("fabric", "fabric26", "neoforge", "forge")
