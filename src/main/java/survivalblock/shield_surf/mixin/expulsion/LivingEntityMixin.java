package survivalblock.shield_surf.mixin.expulsion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.shield_surf.access.ExpulsionDamageAccess;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ExpulsionDamageAccess {

    @Shadow protected float lastDamageTaken;
    @Unique
    private float explusionAttackDamage = 0;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At("RETURN"))
    private void weWantTheMaximum(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        this.explusionAttackDamage = Math.max(this.explusionAttackDamage, this.lastDamageTaken);
    }

    @Override
    public float enchancement_unbound$getExpulsionAttackDamage() {
        float resetAfterMe = this.explusionAttackDamage;
        this.explusionAttackDamage = 0;
        return resetAfterMe;
    }
}
