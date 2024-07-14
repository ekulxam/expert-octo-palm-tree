package survivalblock.shield_surf.common.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ShieldSurfGameRules {
    public static final GameRules.Key<GameRules.IntRule> EXPULSION_MULTIPLIER =
            GameRuleRegistry.register("shieldSurfExpulsionMultiplier", GameRules.Category.MISC, GameRuleFactory.createIntRule(3, 1, 1000));

    public static void init() {

    }
}
