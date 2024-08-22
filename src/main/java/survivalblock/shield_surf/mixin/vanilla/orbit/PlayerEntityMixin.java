package survivalblock.shield_surf.mixin.vanilla.orbit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.shield_surf.common.compat.SurfingFabricShieldLib;
import survivalblock.shield_surf.common.component.ShieldSatellitesComponent;
import survivalblock.shield_surf.common.init.ShieldSurfEntityComponents;

@SuppressWarnings({"UnreachableCode", "RedundantCast"})
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isDead()Z", ordinal = 0, shift = At.Shift.BEFORE), cancellable = true)
    private void blockEntityDamageSources(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!this.getWorld().isClient()) {
            Entity entity = source.getSource();
            if (entity == null && source.getAttacker() == null) {
                return;
            }
            boolean piercesThrough = false;
            if (entity instanceof PersistentProjectileEntity persistentProjectileEntity) {
                if (persistentProjectileEntity.getPierceLevel() > 0) {
                    piercesThrough = true;
                }
            }
            if (!source.isIn(DamageTypeTags.BYPASSES_SHIELD) && !piercesThrough) {
                ShieldSatellitesComponent satellitesComponent = ShieldSurfEntityComponents.SHIELD_SATELLITES.get((PlayerEntity) (Object) this);
                if (satellitesComponent.getSatellites() > 0) {
                    cir.setReturnValue(false);
                    SurfingFabricShieldLib.invokeOrbitBlock(this, source, amount, satellitesComponent.getStack(0));
                    satellitesComponent.removeSatellite();
                }
            }
        }
    }
}
