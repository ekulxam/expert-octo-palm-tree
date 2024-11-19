package survivalblock.shield_surf.common.compat.config;

import net.minecraft.client.gui.screen.Screen;

public class ShieldSurfConfig {

    public static boolean projectedShieldsRenderOutwards() {
        return true;
    }

    public static boolean projectedShieldsRenderWithHandle() {
        return true;
    }

    public static boolean orbitingShieldsFlattenWhileSwimming() {
        return true;
    }

    public static boolean orbitingShieldsRotateClockwise() {
        return true;
    }

    @SuppressWarnings("unused")
    public static Screen create(Screen parent) {
        return null;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean load() {
        return false;
    }
}
