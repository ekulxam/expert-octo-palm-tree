package survivalblock.shield_surf.common.compat;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.minecraft.item.ItemStack;
import survivalblock.shield_surf.common.ShieldSurf;

public class SurfingFabricShieldLib {

    public static boolean isAFabricShield(ItemStack stack) {
        if (!ShieldSurf.hasFabricShieldLib) {
            return false;
        }
        return stack.getItem() instanceof FabricShieldItem;
    }
}
