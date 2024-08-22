package survivalblock.shield_surf.mixin.vanilla.rebound;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerTask;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MinecraftServer.class)
public interface MinecraftServerAccessor {

    @Invoker("createTask")
    ServerTask shield_surf$invokeCreateTask(Runnable runnable);
}
