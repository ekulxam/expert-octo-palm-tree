package survivalblock.shield_surf.common.util;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import survivalblock.shield_surf.common.enchantment.ShieldSurfingEnchantment;
import survivalblock.shield_surf.common.init.ShieldSurfEnchantments;

public class ShieldSurfUtil {

    public static ItemStack getFirstAegisStack(LivingEntity living, boolean shouldCooldown){
        for (ItemStack handStack : living.getHandItems()){
            if (!isAShield(handStack)) continue;
            if (!(EnchantmentHelper.getLevel(ShieldSurfEnchantments.AEGIS, handStack) > 0)) continue;
            if (living instanceof PlayerEntity player && player.getItemCooldownManager().isCoolingDown(handStack.getItem())) continue;
            return handStack;
        }
        if (living instanceof PlayerEntity player){
            int size = player.getInventory().size();
            for (short slot = 0; slot < size; slot++){
                ItemStack stackInSlot = player.getInventory().getStack(slot);
                if (!isAShield(stackInSlot)) continue;
                if (!(EnchantmentHelper.getLevel(ShieldSurfEnchantments.AEGIS, stackInSlot) > 0)) continue;
                Item item = stackInSlot.getItem();
                if (player.getItemCooldownManager().isCoolingDown(item)) continue;
                if (shouldCooldown) {
                    player.getItemCooldownManager().set(item, 100);
                }
                return stackInSlot;
            }
        }
        return ItemStack.EMPTY;
    }
    public static boolean hasAegis(LivingEntity living){
        return getFirstAegisStack(living, false) != ItemStack.EMPTY;
    }

    public static boolean isAShield(ItemStack stack){
        return stack.isIn(ConventionalItemTags.SHIELDS) || stack.isOf(Items.SHIELD);
    }

    public static boolean cancelShieldEnchantments(Enchantment original, Enchantment other){
        return !(other instanceof ShieldSurfingEnchantment) || other == original;
    }

    public static RenderLayer getOrbitingShieldsRenderLayer(boolean showBody, boolean translucent, boolean showOutline, Identifier texture, Model model) {
        if (showOutline) {
            return RenderLayer.getOutline(texture);
        }
        if (translucent) {
            return null;
        }
        if (showBody) {
            return model.getLayer(texture);
        }
        return null;
    }
}
