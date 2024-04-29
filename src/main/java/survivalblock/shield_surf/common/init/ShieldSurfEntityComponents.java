package survivalblock.shield_surf.common.init;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import survivalblock.shield_surf.common.ShieldSurf;
import survivalblock.shield_surf.common.component.ShieldStackComponent;
import survivalblock.shield_surf.common.component.ShieldboardSpeedComponent;
import survivalblock.shield_surf.common.entity.ProjectedShieldEntity;
import survivalblock.shield_surf.common.entity.ShieldboardEntity;

public class ShieldSurfEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<ShieldStackComponent> SHIELD_STACK = ComponentRegistry.getOrCreate(ShieldSurf.id("shield_stack"), ShieldStackComponent.class);
    public static final ComponentKey<ShieldboardSpeedComponent> SHIELDBOARD_SPEED = ComponentRegistry.getOrCreate(ShieldSurf.id("shieldboard_speed"), ShieldboardSpeedComponent.class);
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(ShieldboardEntity.class, SHIELD_STACK, ShieldStackComponent::new);
        registry.registerFor(ShieldboardEntity.class, SHIELDBOARD_SPEED, ShieldboardSpeedComponent::new);
        registry.registerFor(ProjectedShieldEntity.class, SHIELD_STACK, ShieldStackComponent::new);
    }
}
