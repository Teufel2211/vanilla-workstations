package de.teufel2211.workstations.forge;

import de.teufel2211.workstations.VanillaWorkstations;
import net.minecraftforge.fml.common.Mod;

@Mod(VanillaWorkstations.MOD_ID)
public final class VanillaWorkstationsForge {
    public VanillaWorkstationsForge() {
        VanillaWorkstations.init();
    }
}
