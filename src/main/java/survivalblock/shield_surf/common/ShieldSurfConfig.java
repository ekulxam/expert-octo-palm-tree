package survivalblock.shield_surf.common;

import eu.midnightdust.lib.config.MidnightConfig;

public class ShieldSurfConfig extends MidnightConfig {
    @Entry
    public static boolean projectedShieldsRenderOutwards = true;
    @Entry
    public static boolean projectedShieldsRenderWithHandle = true;
    @Entry
    public static boolean orbitingShieldsFlattenWhileSwimming = true;
    @Entry
    public static boolean orbitingShieldsRotateClockwise = true;
}
