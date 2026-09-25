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
// Nur einbinden, wenn es gebaut wird: No-Remap-Loom wuerde sonst bei
// Baseline-Defaults (MC 1.21.1 + FAPI-1.21.x mit Intermediary-Access-Widenern)
// die Konfiguration von :fabric:build & Release sprengen. Die Matrix ruft
// :fabric26:build gezielt auf (--configure-on-demand).
rootProject.name = "vanilla-workstations"
include("fabric", "neoforge", "forge")
if (gradle.startParameter.taskNames.any { it.contains("fabric26") }) {
    include("fabric26")
}
