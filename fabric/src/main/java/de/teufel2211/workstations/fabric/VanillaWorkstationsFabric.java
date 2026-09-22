package de.teufel2211.workstations.fabric;

import de.teufel2211.workstations.VanillaWorkstations;
import net.fabricmc.api.ModInitializer;

public final class VanillaWorkstationsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        VanillaWorkstations.init();
    }
}
