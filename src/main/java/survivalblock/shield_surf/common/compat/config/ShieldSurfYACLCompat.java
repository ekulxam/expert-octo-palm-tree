package survivalblock.shield_surf.common.compat.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import survivalblock.shield_surf.common.ShieldSurf;

public class ShieldSurfYACLCompat {

    public static Screen create(Screen parent){
        if (!ShieldSurf.shouldDoConfig) {
            throw new UnsupportedOperationException();
        }
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("shield_surf.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("shield_surf.config.title"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("shield_surf.config.client"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("shield_surf.config.option.boolean.projectedShieldsRenderOutwards"))
                                        .description(OptionDescription.of(Text.translatable("shield_surf.config.option.boolean.projectedShieldsRenderOutwards.desc")))
                                        .binding(ShieldSurfYACLCompat.HANDLER.defaults().projectedShieldsRenderOutwards, () -> ShieldSurfYACLCompat.HANDLER.instance().projectedShieldsRenderOutwards, newVal -> ShieldSurfYACLCompat.HANDLER.instance().projectedShieldsRenderOutwards = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("shield_surf.config.option.boolean.projectedShieldsRenderWithHandle"))
                                        .description(OptionDescription.of(Text.translatable("shield_surf.config.option.boolean.projectedShieldsRenderWithHandle.desc")))
                                        .binding(ShieldSurfYACLCompat.HANDLER.defaults().projectedShieldsRenderWithHandle, () -> ShieldSurfYACLCompat.HANDLER.instance().projectedShieldsRenderWithHandle, newVal -> ShieldSurfYACLCompat.HANDLER.instance().projectedShieldsRenderWithHandle = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("shield_surf.config.option.boolean.orbitingShieldsFlattenWhileSwimming"))
                                        .description(OptionDescription.of(Text.translatable("shield_surf.config.option.boolean.orbitingShieldsFlattenWhileSwimming.desc")))
                                        .binding(ShieldSurfYACLCompat.HANDLER.defaults().orbitingShieldsFlattenWhileSwimming, () -> ShieldSurfYACLCompat.HANDLER.instance().orbitingShieldsFlattenWhileSwimming, newVal -> ShieldSurfYACLCompat.HANDLER.instance().orbitingShieldsFlattenWhileSwimming = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("shield_surf.config.option.boolean.orbitingShieldsRotateClockwise"))
                                        .description(OptionDescription.of(Text.translatable("shield_surf.config.option.boolean.orbitingShieldsRotateClockwise.desc")))
                                        .binding(ShieldSurfYACLCompat.HANDLER.defaults().orbitingShieldsRotateClockwise, () -> ShieldSurfYACLCompat.HANDLER.instance().orbitingShieldsRotateClockwise, newVal -> ShieldSurfYACLCompat.HANDLER.instance().orbitingShieldsRotateClockwise = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .save(() -> ShieldSurfYACLCompat.HANDLER.save())
                .build()
                .generateScreen(parent);
    }

    public static ConfigClassHandler<ShieldSurfYACLCompat> HANDLER = ConfigClassHandler.createBuilder(ShieldSurfYACLCompat.class)
            .id(ShieldSurf.id("shield_surf"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("shield_surf.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry
    public boolean projectedShieldsRenderOutwards = true;
    @SerialEntry
    public boolean projectedShieldsRenderWithHandle = true;
    @SerialEntry
    public boolean orbitingShieldsFlattenWhileSwimming = true;
    @SerialEntry
    public boolean orbitingShieldsRotateClockwise = true;
}
