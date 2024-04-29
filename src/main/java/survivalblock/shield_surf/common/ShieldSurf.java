package survivalblock.shield_surf.common;


import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import survivalblock.shield_surf.common.init.ShieldSurfEnchantments;
import survivalblock.shield_surf.common.init.ShieldSurfEntityTypes;
import survivalblock.shield_surf.common.init.ShieldSurfSoundEvents;

public class ShieldSurf implements ModInitializer {
	public static final String MOD_ID = "shield_surf";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ShieldSurfEnchantments.init();
		ShieldSurfEntityTypes.init();
		ShieldSurfSoundEvents.init();
		MidnightConfig.init(MOD_ID, ShieldSurfConfig.class);
	}

	public static Identifier id(String value) {
		return new Identifier(MOD_ID, value);
	}
}
