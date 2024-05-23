package survivalblock.shield_surf.common.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import survivalblock.shield_surf.common.entity.ShieldboardEntity;
import survivalblock.shield_surf.common.init.ShieldSurfEnchantments;
import survivalblock.shield_surf.common.init.ShieldSurfEntityComponents;

public class ShieldboardSpeedComponent implements AutoSyncedComponent {
    private final ShieldboardEntity obj;
    private double currentBaseSpeed;
    private boolean wasFlying;

    public ShieldboardSpeedComponent(ShieldboardEntity obj) {
        this.obj = obj;
        currentBaseSpeed = 0;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.currentBaseSpeed = tag.getDouble("CurrentBaseSpeed");
        this.wasFlying = tag.getBoolean("WasFlyingWhenSummoned");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putDouble("CurrentBaseSpeed", this.currentBaseSpeed);
        tag.putBoolean("WasFlyingWhenSummoned", this.wasFlying);
    }

    public double getCurrentBaseSpeed() {
        return this.currentBaseSpeed;
    }

    public double getMaxBaseSpeed() {
        return ShieldboardEntity.MAX_SPEED + (EnchantmentHelper.getLevel(ShieldSurfEnchantments.SHIELD_SURF, this.obj.asItemStack()) * 0.1);
    }

    public void setCurrentBaseSpeed(double currentBaseSpeed) {
        double maxSpeed = this.getMaxBaseSpeed();
        this.currentBaseSpeed = MathHelper.clamp(currentBaseSpeed, -maxSpeed, maxSpeed);
        ShieldSurfEntityComponents.SHIELDBOARD_SPEED.sync(this.obj);
    }

    public void setWasFlying(boolean wasFlying) {
        this.wasFlying = wasFlying;
        ShieldSurfEntityComponents.SHIELDBOARD_SPEED.sync(this.obj);
    }

    public boolean getWasFlying() {
        return this.wasFlying;
    }
}
