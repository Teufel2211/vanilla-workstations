package de.teufel2211.workstations;

public final class VanillaWorkstations {
    public static final String MOD_ID = "vanilla_workstations";

    private VanillaWorkstations() {}

    public static void init() {
        // Registrierung kommt hier hin (MVP Schritt 1):
        // - FletchingTableMenu + Rezepte (common, JSON-datapack-faehig)
        // - HopperSlotToggle (Server-Logik, NBT pro BlockEntity)
        // - ComposterTweaks (Fuellstand-Partikel, Hopper-Regeln)
        ModConfig.load();
    }
}
