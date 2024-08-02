package survivalblock.shield_surf.common.datagen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.damage.DamageType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class FabricDamageTypesContainer {

    private final List<Pair<String, DamageType>> backing;

    public FabricDamageTypesContainer() {
        this.backing = new ArrayList<>();
    }

    public void forEach(Consumer<Pair<String, DamageType>> action) {
        this.backing.forEach(action);
    }

    public boolean add(DamageType damageType) {
        return this.add(damageType.msgId(), damageType);
    }

    public boolean add(String filename, DamageType damageType) {
        Pair<String, DamageType> pair = new Pair<>(filename, damageType);
        return this.backing.add(pair);
    }

    public boolean addDamageTypes(Collection<DamageType> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        AtomicBoolean changed = new AtomicBoolean(false);
        collection.forEach(damageType -> changed.set(changed.get() || this.add(damageType)));
        return changed.get();
    }

    public boolean addFilenamesAndDamageTypes(Collection<Pair<String, DamageType>> collectionPair) {
        if (collectionPair.isEmpty()) {
            return false;
        }
        AtomicBoolean changed = new AtomicBoolean(false);
        collectionPair.forEach(pair -> changed.set(changed.get() || this.add(pair.getFirst(), pair.getSecond())));
        return changed.get();
    }

    public ImmutableList<Pair<String, DamageType>> asImmutableCopy() {
        return ImmutableList.copyOf(this.backing);
    }
}
