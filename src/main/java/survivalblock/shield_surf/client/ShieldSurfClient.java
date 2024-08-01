package survivalblock.shield_surf.client;


import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.text.Text;
import survivalblock.shield_surf.client.render.ProjectedShieldEntityRenderer;
import survivalblock.shield_surf.client.render.ShieldboardEntityRenderer;
import survivalblock.shield_surf.common.ShieldSurf;
import survivalblock.shield_surf.common.init.ShieldSurfEntityTypes;

public class ShieldSurfClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ShieldSurfEntityTypes.SHIELDBOARD, ShieldboardEntityRenderer::new);
		EntityRendererRegistry.register(ShieldSurfEntityTypes.PROJECTED_SHIELD, ProjectedShieldEntityRenderer::new);
		//noinspection CodeBlock2Expr
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("shieldsurfconfig").executes(context -> {
				MinecraftClient client = context.getSource().getClient();
				Screen configScreen = MidnightConfig.getScreen(null, ShieldSurf.MOD_ID);
				client.send(() -> client.setScreen(configScreen));
				return 1;
			}));
		});
	}
}
