package survivalblock.shield_surf.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import survivalblock.shield_surf.common.init.ShieldSurfDamageTypes;

public class ShieldSurfDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ShieldSurfEnUsLangGenerator::new);
		pack.addProvider(ShieldSurfTagGenerator.ShieldSurfDamageTypeTagGenerator::new);
		pack.addProvider(ShieldSurfTagGenerator.ShieldSurfEntityTypeTagGenerator::new);
		pack.addProvider(ShieldSurfDamageTypeGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, ShieldSurfDamageTypes::bootstrap);
	}
}
