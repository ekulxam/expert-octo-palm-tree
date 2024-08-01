package survivalblock.shield_surf.common.datagen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.damage.DamageType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DamageTypeBuilder {

    private final List<Pair<String, DamageType>> backing = new ArrayList<>();

    public DamageTypeBuilder() {
    }

    public void forEach(Consumer<Pair<String, DamageType>> action) {
        backing.forEach(action);
    }

    @SuppressWarnings("unused")
    public boolean add(DamageType e) {
        return add(e.msgId(), e);
    }

    public boolean add(String filename, DamageType e) {
        Pair<String, DamageType> pair = new Pair<>(filename, e);
        return backing.add(pair);
    }
}
