package survivalblock.shield_surf.mixin.vanilla.orbit;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
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

    @Inject(method = "damage", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;despawnCounter:I", opcode = Opcodes.PUTFIELD, shift = At.Shift.AFTER), cancellable = true)
    private void blockEntityDamageSources(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!this.getWorld().isClient()) {
            if (source.getSource() == null || source.getAttacker() == null) {
                return;
            }
            ShieldSatellitesComponent satellitesComponent = ShieldSurfEntityComponents.SHIELD_SATELLITES.get((PlayerEntity) (Object) this);
            if (satellitesComponent.getSatellites() > 0) {
                cir.setReturnValue(false);
                SurfingFabricShieldLib.invokeOrbitBlock(this, source, amount, satellitesComponent.getStack(0));
                satellitesComponent.removeSatellite();
            }
        }
    }
}
