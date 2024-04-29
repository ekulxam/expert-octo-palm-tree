package survivalblock.shield_surf.mixin.vanillachanges.enchantableshields;

import net.minecraft.item.ShieldItem;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ShieldItem.class)
public class ShieldItemMixin extends ItemMixin {
    @Override
    protected int shieldsAreNowEnchantable(int original){
        return ceil(ToolMaterials.WOOD.getEnchantability(), ToolMaterials.IRON.getEnchantability());
    }

    @Unique
    private static int ceil(int a, int b){
        int avg = (a + b) / 2; // int division
        return avg < a || avg < b ? avg + 1 : avg;
    }
}
