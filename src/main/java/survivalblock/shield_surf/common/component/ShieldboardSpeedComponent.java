package survivalblock.shield_surf.common.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.math.MathHelper;
import survivalblock.shield_surf.common.entity.ShieldboardEntity;
import survivalblock.shield_surf.common.init.ShieldSurfEntityComponents;

public class ShieldboardSpeedComponent implements AutoSyncedComponent {
    private final ShieldboardEntity obj;
    private double currentBaseSpeed;

    public ShieldboardSpeedComponent(ShieldboardEntity obj) {
        this.obj = obj;
        currentBaseSpeed = 0;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.currentBaseSpeed = tag.getDouble("CurrentBaseSpeed");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putDouble("CurrentBaseSpeed", this.currentBaseSpeed);
    }

    public double getCurrentBaseSpeed() {
        return this.currentBaseSpeed;
    }

    public void setCurrentBaseSpeed(double currentBaseSpeed) {
        this.currentBaseSpeed = MathHelper.clamp(currentBaseSpeed, -ShieldboardEntity.maxSpeed, ShieldboardEntity.maxSpeed);
        ShieldSurfEntityComponents.SHIELDBOARD_SPEED.sync(this.obj);
    }
}
