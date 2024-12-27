package survivalblock.shield_surf.common.compat;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import survivalblock.shield_surf.common.ShieldSurf;

public class SurfingFabricShieldLib {

    public static boolean isAFabricShield(ItemStack stack) {
        if (!ShieldSurf.hasFabricShieldLib) {
            return false;
        }
        return stack.getItem() instanceof FabricShieldItem || stack.getItem() instanceof FabricBannerShieldItem;
    }

    public static void invokeOrbitBlock(LivingEntity defender, DamageSource source, float amount, ItemStack stack) {
        if (!ShieldSurf.hasFabricShieldLib) {
            return;
        }
        Hand hand = defender.getActiveHand();
        if (hand == null) {
            hand = Hand.OFF_HAND;
        }
        ShieldBlockCallback.EVENT.invoker().block(defender, source, amount, hand, stack);
    }
}
