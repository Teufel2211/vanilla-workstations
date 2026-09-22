import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.language.jvm.tasks.ProcessResources

// Root: keine Plugins, nur Defaults. Loom/ModDev/ForgeGradle kommen pro Modul.
subprojects {
    apply(plugin = "java")

    extensions.configure<JavaPluginExtension> {
        withSourcesJar()
        val jv = providers.gradleProperty("java_version").get().toInt()
        sourceCompatibility = JavaVersion.toVersion(jv)
        targetCompatibility = JavaVersion.toVersion(jv)
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<ProcessResources> {
        // Loader-Plugins (Loom/ModDev) legen zusaetzlich generierte Ressourcen
        // (z.B. quilt.mod.json, neoforge.mods.toml) neben die gecheckten Dateien;
        // Gradle 9 bricht bei Duplikaten ab -> erste Datei gewinnt.
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        val ver = providers.gradleProperty("mod_version").get()
        inputs.property("version", ver)
        filesMatching(listOf("fabric.mod.json", "quilt.mod.json", "*.toml")) {
            expand(mapOf("version" to ver))
        }
    }

    tasks.withType<Jar> {
        // sourcesJar vereinigt gecheckte + generierte Ressourcen
        // (z.B. fabric.mod.json doppelt) -> erste Datei gewinnt.
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}
