package survivalblock.shield_surf.common.init;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import survivalblock.shield_surf.common.ShieldSurf;

public class ShieldSurfTags {

    public static class ShieldSurfEntityTypeTags {
        public static final TagKey<EntityType<?>> SHIELD_ENTITIES = TagKey.of(RegistryKeys.ENTITY_TYPE, ShieldSurf.id("shield_entities"));
    }
}
