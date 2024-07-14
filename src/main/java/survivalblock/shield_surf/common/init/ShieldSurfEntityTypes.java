package survivalblock.shield_surf.common.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import survivalblock.shield_surf.common.ShieldSurf;
import survivalblock.shield_surf.common.entity.ProjectedShieldEntity;
import survivalblock.shield_surf.common.entity.ShieldboardEntity;

public class ShieldSurfEntityTypes {

    public static final EntityType<ShieldboardEntity> SHIELDBOARD = registerEntity("shieldboard", FabricEntityTypeBuilder.<ShieldboardEntity>create(SpawnGroup.MISC, ShieldboardEntity::new).dimensions(EntityDimensions.fixed(0.85f, 0.2125f)).fireImmune());
    public static final EntityType<ProjectedShieldEntity> PROJECTED_SHIELD = registerEntity("projected_shield", FabricEntityTypeBuilder.<ProjectedShieldEntity>create(SpawnGroup.MISC, ProjectedShieldEntity::new).dimensions(EntityDimensions.fixed(0.5f, 1.3f)));

    @SuppressWarnings("SameParameterValue")
    private static <T extends Entity> EntityType<T> registerEntity(String name, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, ShieldSurf.id(name), builder.build());
    }

    public static void init(){

    }
}
