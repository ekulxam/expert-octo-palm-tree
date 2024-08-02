package survivalblock.shield_surf.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import survivalblock.shield_surf.common.init.ShieldSurfDamageTypes;

import java.util.Map;

public class ShieldSurfDamageTypeGenerator extends FabricDamageTypeProvider {

    public ShieldSurfDamageTypeGenerator(FabricDataOutput generator) {
        super(generator);
    }

    @Override
    protected void setup(FabricDamageTypesContainer damageTypesContainer) {
        for (Map.Entry<RegistryKey<DamageType>, DamageType> entry : ShieldSurfDamageTypes.asDamageTypes().entrySet()) {
            damageTypesContainer.add(entry.getKey().getValue().getPath(), entry.getValue());
        }
    }
}
