package survivalblock.shield_surf.client;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import survivalblock.shield_surf.client.render.ProjectedShieldEntityRenderer;
import survivalblock.shield_surf.client.render.ShieldboardEntityRenderer;
import survivalblock.shield_surf.common.init.ShieldSurfEntityTypes;

public class ShieldSurfClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ShieldSurfEntityTypes.SHIELDBOARD, ShieldboardEntityRenderer::new);
		EntityRendererRegistry.register(ShieldSurfEntityTypes.PROJECTED_SHIELD, ProjectedShieldEntityRenderer::new);
	}
}
