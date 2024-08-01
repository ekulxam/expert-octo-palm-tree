package survivalblock.shield_surf.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.DamageTypeTags;
import survivalblock.shield_surf.common.init.ShieldSurfDamageTypes;
import survivalblock.shield_surf.common.init.ShieldSurfEntityTypes;
import survivalblock.shield_surf.common.init.ShieldSurfTags;

import java.util.concurrent.CompletableFuture;

public class ShieldSurfTagGenerator {

    public static class ShieldSurfDamageTypeTagGenerator extends FabricTagProvider<DamageType> {
        public ShieldSurfDamageTypeTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.DAMAGE_TYPE, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup lookup) {

            getOrCreateTagBuilder(DamageTypeTags.BYPASSES_COOLDOWN).add(ShieldSurfDamageTypes.SHIELDBOARD_COLLISION);
            getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(ShieldSurfDamageTypes.SHIELDBOARD_COLLISION);

            getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(ShieldSurfDamageTypes.SHIELD_IMPACT);
            getOrCreateTagBuilder(DamageTypeTags.BYPASSES_COOLDOWN).add(ShieldSurfDamageTypes.SHIELD_IMPACT);
            getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR).add(ShieldSurfDamageTypes.SHIELD_IMPACT);
        }
    }

    public static class AmarongEntityTypeTagGenerator extends FabricTagProvider.EntityTypeTagProvider {
        public AmarongEntityTypeTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup lookup) {
            getOrCreateTagBuilder(ShieldSurfTags.ShieldSurfEntityTypeTags.SHIELD_ENTITIES).add(ShieldSurfEntityTypes.SHIELDBOARD);
            getOrCreateTagBuilder(ShieldSurfTags.ShieldSurfEntityTypeTags.SHIELD_ENTITIES).add(ShieldSurfEntityTypes.PROJECTED_SHIELD);
        }
    }
}
