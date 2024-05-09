package survivalblock.shield_surf.mixin.orbit;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.shield_surf.common.component.ShieldSatellitesComponent;
import survivalblock.shield_surf.common.init.ShieldSurfEnchantments;
import survivalblock.shield_surf.common.init.ShieldSurfEntityComponents;

@Mixin(ShieldItem.class)
public class ShieldItemMixin {

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setCurrentHand(Lnet/minecraft/util/Hand;)V"))
    private void solarSystem(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir, @Local ItemStack stack) {
        if (world.isClient() || EnchantmentHelper.getLevel(ShieldSurfEnchantments.ORBIT, stack) <= 0) {
            return;
        }
        ShieldSatellitesComponent satellitesComponent = ShieldSurfEntityComponents.SHIELD_SATELLITES.get(user);
        if (satellitesComponent.getSatellites() + 1 > ShieldSatellitesComponent.maxSatellites) {
            return;
        }
        satellitesComponent.addSatellite(stack);
        if (!user.isCreative()) {
            stack.damage(3, user, p -> p.sendToolBreakStatus(hand));
            user.getItemCooldownManager().set((ShieldItem) (Object) this, 80);
            user.stopUsingItem();
        }
        user.incrementStat(Stats.USED.getOrCreateStat((ShieldItem) (Object) this));
    }
}
