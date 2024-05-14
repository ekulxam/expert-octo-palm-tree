package survivalblock.shield_surf.mixin.shieldsurf;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.shield_surf.common.entity.ShieldboardEntity;

@Debug(export = true)
@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @ModifyExpressionValue(method = "onVehicleMove", at = @At(value = "CONSTANT", args = "doubleValue=0.0625", ordinal = 1))
    private double neverMoveWrongly(double original, @Local(ordinal = 0) Entity entity){
        return entity instanceof ShieldboardEntity ? original * 10 : original;
    }
}
