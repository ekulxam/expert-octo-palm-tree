package survivalblock.shield_surf.common.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import survivalblock.shield_surf.common.ShieldSurf;
import survivalblock.shield_surf.common.enchantment.*;

public class ShieldSurfEnchantments {
    public static final Enchantment SHIELD_SURF = new ShieldSurfingEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.FISHING_ROD, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);
    public static final Enchantment AEGIS = new ShieldSurfingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.FISHING_ROD, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);
    public static final Enchantment RAPID = new ShieldSurfingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.FISHING_ROD, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);
    public static final Enchantment EXPULSION = new ShieldSurfingEnchantment(6, Enchantment.Rarity.UNCOMMON, EnchantmentTarget.FISHING_ROD, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);
    public static final Enchantment ORBIT = new ShieldSurfingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.FISHING_ROD, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);

    public static void init() {
        Registry.register(Registries.ENCHANTMENT, ShieldSurf.id("shield_surf"), SHIELD_SURF);
        Registry.register(Registries.ENCHANTMENT, ShieldSurf.id("aegis"), AEGIS);
        Registry.register(Registries.ENCHANTMENT, ShieldSurf.id("rapid"), RAPID);
        Registry.register(Registries.ENCHANTMENT, ShieldSurf.id("expulsion"), EXPULSION);
        Registry.register(Registries.ENCHANTMENT, ShieldSurf.id("orbit"), ORBIT);
    }
}
