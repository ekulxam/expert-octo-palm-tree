package survivalblock.shield_surf.mixin.expulsion;

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
import survivalblock.shield_surf.access.ExpulsionDamageAccess;
import survivalblock.shield_surf.common.ShieldSurfConfig;
import survivalblock.shield_surf.common.entity.ProjectedShieldEntity;
import survivalblock.shield_surf.common.init.ShieldSurfEnchantments;

@Mixin(ShieldItem.class)
public class ShieldItemMixin {

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setCurrentHand(Lnet/minecraft/util/Hand;)V"))
    private void shieldcast(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir, @Local ItemStack stack){
        int expulsionLevel = EnchantmentHelper.getLevel(ShieldSurfEnchantments.EXPULSION, stack);
        if (world.isClient() || expulsionLevel <= 0 || !user.isSneaking()) {
            return;
        }
        float damage = ((ExpulsionDamageAccess) user).enchancement_unbound$getExpulsionAttackDamage();
        if (damage * 2 <= 100){
            damage *= 2;
        } else if (damage < 100) {
            damage = 100;
        }
        ProjectedShieldEntity projectedShield;
        for (float i = 0; i < 360; i += 360 / (float) (expulsionLevel * ShieldSurfConfig.expulsionMultiplier)) {
            projectedShield = new ProjectedShieldEntity(world, user, stack);
            projectedShield.setYaw(i + user.getYaw());
            world.spawnEntity(projectedShield);
            projectedShield.setVelocity(user, projectedShield.getPitch(), projectedShield.getYaw(), 0.0f, 0.3f, 0.0f);
            projectedShield.setDamage(Math.max(damage, 4));
        }
        stack.damage(2, user, (p) -> p.sendToolBreakStatus(user.getActiveHand()));
        user.incrementStat(Stats.USED.getOrCreateStat((ShieldItem) (Object) this));
        user.getItemCooldownManager().set((ShieldItem) (Object) this, 200);
    }
}
