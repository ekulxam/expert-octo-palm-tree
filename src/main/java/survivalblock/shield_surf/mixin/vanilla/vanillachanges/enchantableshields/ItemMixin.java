package survivalblock.shield_surf.mixin.vanilla.vanillachanges.enchantableshields;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public class ItemMixin {

    @ModifyReturnValue(method = "getEnchantability", at = @At("RETURN"))
    protected int shieldsAreNowEnchantable(int original){
        return original;
    }
}