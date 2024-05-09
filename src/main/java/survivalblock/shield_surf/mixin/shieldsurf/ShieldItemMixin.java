package survivalblock.shield_surf.mixin.shieldsurf;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.shield_surf.common.entity.ShieldboardEntity;
import survivalblock.shield_surf.common.init.ShieldSurfEnchantments;
import survivalblock.shield_surf.common.init.ShieldSurfEntityComponents;

@Mixin(ShieldItem.class)
public class ShieldItemMixin {
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setCurrentHand(Lnet/minecraft/util/Hand;)V"))
    private void rideTheLightning(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir, @Local ItemStack stack){
        if(!world.isClient() && EnchantmentHelper.getLevel(ShieldSurfEnchantments.SHIELD_SURF, stack) > 0 && !user.isSneaking()){
            user.incrementStat(Stats.USED.getOrCreateStat((ShieldItem) (Object) this));
            ShieldboardEntity shieldboard = new ShieldboardEntity(world, user, stack);
            if (user instanceof ServerPlayerEntity player) ShieldSurfEntityComponents.SHIELDBOARD_SPEED.get(shieldboard).setWasFlying(player.getAbilities().flying);
            world.spawnEntity(shieldboard);
            user.getInventory().removeOne(stack);
            user.startRiding(shieldboard, true);
        }
    }
}
