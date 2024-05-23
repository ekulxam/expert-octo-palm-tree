package survivalblock.shield_surf.common;


import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import survivalblock.shield_surf.common.init.ShieldSurfEnchantments;
import survivalblock.shield_surf.common.init.ShieldSurfEntityTypes;
import survivalblock.shield_surf.common.init.ShieldSurfSoundEvents;

public class ShieldSurf implements ModInitializer {
	public static final String MOD_ID = "shield_surf";
	public static final String FABRIC_SHIELD_LIB_ID = "fabricshieldlib";
	public static final Logger LOGGER = LoggerFactory.getLogger("Shield_Surf");
	public static boolean hasFabricShieldLib = false;

	@Override
	public void onInitialize() {
		hasFabricShieldLib = FabricLoader.getInstance().isModLoaded(FABRIC_SHIELD_LIB_ID);
		MidnightConfig.init(MOD_ID, ShieldSurfConfig.class);
		ShieldSurfEnchantments.init();
		ShieldSurfEntityTypes.init();
		ShieldSurfSoundEvents.init();
	}

	public static Identifier id(String value) {
		return new Identifier(MOD_ID, value);
	}
}
