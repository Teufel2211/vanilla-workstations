package de.teufel2211.workstations;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Minimale JSON-Config, pro Feature an/aus.
 * Datei: config/vanilla-workstations.json
 */
public final class ModConfig {
    public boolean enableFletching = true;
    public boolean enableHopperToggle = true;
    public boolean enableComposter = true;
    public double composterSpeedMultiplier = 1.0;

    private ModConfig() {}

    public static ModConfig load() {
        Path path = Paths.get("config", "vanilla-workstations.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (Files.exists(path)) {
            try {
                String json = Files.readString(path);
                return gson.fromJson(json, ModConfig.class);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        ModConfig fresh = new ModConfig();
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, gson.toJson(JsonObject.class.cast(gson.toJsonTree(fresh))));
        } catch (IOException ignored) {
            // silent-fail wie Vanilla: Config ist optional
        }
        return fresh;
    }
}
