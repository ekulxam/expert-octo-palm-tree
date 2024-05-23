package survivalblock.shield_surf.mixin.vanilla.vanillachanges.enchantableshields;

import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;

import static net.minecraft.item.ToolMaterials.WOOD;
import static net.minecraft.item.ToolMaterials.IRON;

@Mixin(ShieldItem.class)
public class ShieldItemMixin extends ItemMixin {

    @Override
    protected int shieldsAreNowEnchantable(int original){
        return (int) Math.ceil((WOOD.getEnchantability() + IRON.getEnchantability()) / 2d);
    }
}
