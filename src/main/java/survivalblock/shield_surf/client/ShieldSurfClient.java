package survivalblock.shield_surf.client;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import survivalblock.shield_surf.client.render.ProjectedShieldEntityRenderer;
import survivalblock.shield_surf.client.render.ShieldboardEntityRenderer;
import survivalblock.shield_surf.common.ShieldSurf;
import survivalblock.shield_surf.common.compat.config.ShieldSurfConfig;
import survivalblock.shield_surf.common.init.ShieldSurfEntityTypes;

public class ShieldSurfClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ShieldSurfEntityTypes.SHIELDBOARD, ShieldboardEntityRenderer::new);
		EntityRendererRegistry.register(ShieldSurfEntityTypes.PROJECTED_SHIELD, ProjectedShieldEntityRenderer::new);
		//noinspection CodeBlock2Expr
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("shieldsurfconfig").executes(context -> {
				if (!ShieldSurf.shouldDoConfig) {
					context.getSource().sendFeedback(Text.translatable("commands.shieldsurfconfig.noyacl"));
					return 0;
				}
				if (!ShieldSurf.configLoaded) {
					context.getSource().sendFeedback(Text.translatable("commands.shieldsurfconfig.fail"));
					return 0;
				}
				MinecraftClient client = context.getSource().getClient();
				Screen configScreen = ShieldSurfConfig.create(null);
				if (configScreen == null) {
					context.getSource().sendFeedback(Text.translatable("commands.shieldsurfconfig.fail"));
					return 0;
				}
				client.send(() -> client.setScreen(configScreen));
				return 1;
			}));
		});
	}
}
