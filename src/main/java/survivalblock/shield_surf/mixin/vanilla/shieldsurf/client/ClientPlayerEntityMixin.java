package survivalblock.shield_surf.mixin.vanilla.shieldsurf.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import survivalblock.shield_surf.common.entity.ShieldboardEntity;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Shadow public Input input;

    @WrapOperation(method = "tickRiding", constant = @Constant(classValue = BoatEntity.class, ordinal = 0))
    private boolean updateRidingShieldboard(Object obj, Operation<Boolean> original){
        if (obj instanceof ShieldboardEntity shieldboard) {
            shieldboard.setInputs(this.input.pressingLeft, this.input.pressingRight, this.input.pressingForward, this.input.pressingBack, this.input.jumping);
        }
        return original.call(obj);
    }
}
