package survivalblock.shield_surf.common.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import survivalblock.shield_surf.common.init.ShieldSurfEntityComponents;

import java.util.ArrayList;
import java.util.List;

public class ShieldSatellitesComponent implements AutoSyncedComponent, CommonTickingComponent {
    private final PlayerEntity obj;
    private int satellites = 0;
    private int rotation = 0;
    private final List<ItemStack> itemStacks = new ArrayList<>();
    public static final int maxSatellites = 16;

    public ShieldSatellitesComponent(PlayerEntity obj) {
        this.obj = obj;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.satellites = tag.getInt("Satellites");
        this.rotation = tag.getInt("OrbitRotation");
        itemStacks.clear();
        NbtList nbtList = tag.getList("Items", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            itemStacks.add(ItemStack.fromNbt(nbtCompound));
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putInt("Satellites", this.satellites);
        tag.putInt("OrbitRotation", this.rotation);
        NbtList nbtList = new NbtList();
        for (ItemStack itemStack : itemStacks) {
            if (itemStack.isEmpty()) continue;
            NbtCompound nbtCompound = new NbtCompound();
            itemStack.writeNbt(nbtCompound);
            nbtList.add(nbtCompound);
        }
        tag.put("Items", nbtList);
    }

    public int getRotation() {
        return this.rotation;
    }

    public int getSatellites() {
        return this.satellites;
    }
    public void addSatellite(ItemStack stack) {
        ++this.satellites;
        itemStacks.add(stack);
        sync();
    }

    public void removeSatellite() {
        --this.satellites;
        itemStacks.remove(0);
        this.obj.getWorld().sendEntityStatus(this.obj, (byte) 29);
        this.obj.getWorld().sendEntityStatus(this.obj, (byte) 30);
        sync();
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
        sync();
    }

    @Override
    public void tick() {
        if (this.satellites > maxSatellites || this.getItemStacksSize() != this.satellites) {
            this.satellites = 0;
            itemStacks.clear();
            sync();
            return;
        }
        this.setRotation((this.getRotation() + 2) % 360);
    }

    private void sync(){
        ShieldSurfEntityComponents.SHIELD_SATELLITES.sync(this.obj);
    }

    public int getItemStacksSize() {
        return this.itemStacks.size();
    }

    public ItemStack getStack(int i) {
        return this.itemStacks.get(i);
    }
}
