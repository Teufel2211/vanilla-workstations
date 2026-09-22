// Root: keine Plugins, nur Defaults. Loom/ModDev/ForgeGradle kommen pro Modul.
subprojects {
    apply(plugin = "java")

    java {
        withSourcesJar()
        val jv = providers.gradleProperty("java_version").get().toInt()
        sourceCompatibility = JavaVersion.toVersion(jv)
        targetCompatibility = JavaVersion.toVersion(jv)
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<ProcessResources> {
        val ver = providers.gradleProperty("mod_version").get()
        inputs.property("version", ver)
        filesMatching(listOf("fabric.mod.json", "quilt.mod.json", "*.toml")) {
            expand(mapOf("version" to ver))
        }
    }
}
