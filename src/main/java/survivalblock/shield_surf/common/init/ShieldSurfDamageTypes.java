package survivalblock.shield_surf.common.init;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import survivalblock.shield_surf.common.ShieldSurf;

public class ShieldSurfDamageTypes {
    public static final RegistryKey<DamageType> SHIELDBOARD_COLLISION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, ShieldSurf.id("shieldboard_collision"));
    public static final RegistryKey<DamageType> SHIELD_IMPACT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, ShieldSurf.id("shield_impact"));

    public static RegistryEntry.Reference<DamageType> get(RegistryKey<DamageType> key, World world) {
        return world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key);
    }
}
