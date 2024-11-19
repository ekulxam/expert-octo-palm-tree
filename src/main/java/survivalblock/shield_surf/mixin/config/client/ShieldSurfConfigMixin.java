package survivalblock.shield_surf.mixin.config.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.shield_surf.common.ShieldSurf;
import survivalblock.shield_surf.common.compat.config.ShieldSurfYACLCompat;
import survivalblock.shield_surf.common.compat.config.ShieldSurfConfig;

@Environment(EnvType.CLIENT)
@Mixin(value = ShieldSurfConfig.class, remap = false)
public class ShieldSurfConfigMixin {

    @ModifyReturnValue(method = "projectedShieldsRenderOutwards", at = @At("RETURN"))
    private static boolean projectedShieldsRenderOutwards(boolean original) {
        return ShieldSurfYACLCompat.HANDLER.instance().projectedShieldsRenderOutwards;
    }

    @ModifyReturnValue(method = "projectedShieldsRenderWithHandle", at = @At("RETURN"))
    private static boolean projectedShieldsRenderWithHandle(boolean original) {
        return ShieldSurfYACLCompat.HANDLER.instance().projectedShieldsRenderWithHandle;
    }

    @ModifyReturnValue(method = "orbitingShieldsFlattenWhileSwimming", at = @At("RETURN"))
    private static boolean orbitingShieldsFlattenWhileSwimming(boolean original) {
        return ShieldSurfYACLCompat.HANDLER.instance().orbitingShieldsFlattenWhileSwimming;
    }

    @ModifyReturnValue(method = "orbitingShieldsRotateClockwise", at = @At("RETURN"))
    private static boolean orbitingShieldsRotateClockwise(boolean original) {
        return ShieldSurfYACLCompat.HANDLER.instance().orbitingShieldsRotateClockwise;
    }

    @ModifyReturnValue(method = "create", at = @At("RETURN"))
    private static Screen create(Screen original, Screen parent) {
        return ShieldSurf.configLoaded ? ShieldSurfYACLCompat.create(parent) : original;
    }

    @ModifyReturnValue(method = "load", at = @At("RETURN"))
    private static boolean load(boolean original) {
        return ShieldSurf.shouldDoConfig && ShieldSurfYACLCompat.HANDLER.load();
    }
}
