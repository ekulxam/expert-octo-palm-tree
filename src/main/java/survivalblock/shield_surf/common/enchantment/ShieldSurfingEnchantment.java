package survivalblock.shield_surf.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import survivalblock.shield_surf.common.util.ShieldSurfUtil;

public class ShieldSurfingEnchantment extends Enchantment {
    private final int maxLevel;
    public ShieldSurfingEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
        this.maxLevel = 1;
    }
    public ShieldSurfingEnchantment(int maxLevel, Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
        this.maxLevel = maxLevel;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return ShieldSurfUtil.isAShield(stack);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && ShieldSurfUtil.cancelShieldEnchantments(this, other);
    }

    @SuppressWarnings("RedundantMethodOverride")
    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;
    }
}
