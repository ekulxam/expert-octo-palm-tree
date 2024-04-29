package survivalblock.shield_surf.mixin.aegis;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import survivalblock.shield_surf.common.util.ShieldSurfUtil;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "isBlocking", at = @At("HEAD"), cancellable = true)
    private void returnTrueIfHasAegis(CallbackInfoReturnable<Boolean> cir){
        if (ShieldSurfUtil.hasAegis((LivingEntity) (Object) this)) {
            cir.setReturnValue(true);
        }
    }
}
