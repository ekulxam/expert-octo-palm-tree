package survivalblock.shield_surf.client;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import survivalblock.shield_surf.client.render.ProjectedShieldEntityRenderer;
import survivalblock.shield_surf.client.render.ShieldboardEntityRenderer;
import survivalblock.shield_surf.common.init.UnboundEntityTypes;

public class ShieldSurfClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(UnboundEntityTypes.SHIELDBOARD, ShieldboardEntityRenderer::new);
		EntityRendererRegistry.register(UnboundEntityTypes.PROJECTED_SHIELD, ProjectedShieldEntityRenderer::new);
	}
}
