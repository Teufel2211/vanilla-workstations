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
    // FG7-Kotlin-DSL (vgl. MinecraftForge/MDKExamples traditional-mdk/fg7-kotlin):
    // mappings positional, Runs via register, kein mods{}-Block (Source-Sets werden gemergt).
    mappings("official", providers.gradleProperty("minecraft_version").get())
    runs {
        register("client") {
        }
        register("server") {
            args("--nogui")
        }
    }
}

dependencies {
    val mc = providers.gradleProperty("minecraft_version").get()
    val fg = providers.gradleProperty("forge_version").get()
    // FG7: Minecraft-Abhaengigkeit ueber minecraft.dependency(..) als implementation.
    implementation(minecraft.dependency("net.minecraftforge:forge:$mc-$fg"))
}
