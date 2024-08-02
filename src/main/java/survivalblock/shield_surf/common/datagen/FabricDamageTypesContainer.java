package survivalblock.shield_surf.common.datagen;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import io.netty.util.internal.UnstableApi;
import net.minecraft.entity.damage.DamageType;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * A container for damage types, backed by a list {@link #backing}
 * @author Survivalblock
 */
@SuppressWarnings("unused")
public class FabricDamageTypesContainer {

    private final List<Pair<String, DamageType>> backing;
    private final Logger LOGGER = LogUtils.getLogger();

    /**
     * Creates a new instance of {@link FabricDamageTypesContainer} with an ArrayList as backing
     */
    public FabricDamageTypesContainer() {
        this(new ArrayList<>());
    }

    /**
     * Creates a new instance of {@link FabricDamageTypesContainer} with an existing list as backing
     * Allows the user to determine the type of the backing
     * @param list the list that the backing will be assigned to
     * @throws IllegalStateException if the provided list is immutable
     */
    @UnstableApi
    public FabricDamageTypesContainer(List<Pair<String, DamageType>> list) {
        if (list instanceof ImmutableCollection<?>) {
            throw new IllegalStateException("The backing of FabricDamageTypesContainer cannot be immutable!");
        }
        List<Pair<String, DamageType>> temporaryList;
        try {
            temporaryList = (List<Pair<String, DamageType>>) list.getClass().newInstance();
        } catch (Throwable t) {
            LOGGER.error("Error when attempting to make a new instance of a list in the constructor of FabricDamageTypesContainer", t);
            temporaryList = new ArrayList<>();
        }
        if (!temporaryList.isEmpty()) temporaryList.clear();
        this.backing = temporaryList;
    }

    /**
     * Invokes {@link Collection#forEach(Consumer)} on the backing
     * @param action The action to be performed for each element
     */
    public void forEach(Consumer<Pair<String, DamageType>> action) {
        this.backing.forEach(action);
    }

    /**
     * Adds a damage type to the backing with the filename as {@link DamageType#msgId()}
     * @param damageType the damage type to be added
     * @return true if the backing was modified
     */
    public boolean add(DamageType damageType) {
        return this.add(damageType.msgId(), damageType);
    }

    /**
     * Adds a damage type to the backing
     * @param filename the filename to write the damage type to
     * @param damageType the damage type to be added
     * @return true if the backing was modified
     */
    public boolean add(String filename, DamageType damageType) {
        Pair<String, DamageType> pair = new Pair<>(filename, damageType);
        return this.backing.add(pair);
    }

    /**
     * Adds a collection of damage types to the backing
     * @param collection the collection of damage types to add
     * @see #add(DamageType)
     * @return if the backing was modified
     */
    public boolean addDamageTypes(Collection<DamageType> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        AtomicBoolean changed = new AtomicBoolean(false);
        collection.forEach(damageType -> changed.set(changed.get() || this.add(damageType)));
        return changed.get();
    }

    /**
     * Adds a collection of damage types to the backing
     * @param collectionPair a collection of pairs, where each pair is a {@link String} filename and a {@link DamageType} damageType
     * @see #add(String, DamageType)
     * @see #addDamageTypes(Collection)
     * @return if the backing was modified
     */
    public boolean addFilenamesAndDamageTypes(Collection<Pair<String, DamageType>> collectionPair) {
        if (collectionPair.isEmpty()) {
            return false;
        }
        AtomicBoolean changed = new AtomicBoolean(false);
        collectionPair.forEach(pair -> changed.set(changed.get() || this.add(pair.getFirst(), pair.getSecond())));
        return changed.get();
    }

    /**
     * Converts the backing into an {@link ImmutableList}
     * @see ImmutableList#copyOf(Collection)
     * @return an ImmutableList that is a copy of {@link #backing}
     */
    public ImmutableList<Pair<String, DamageType>> asImmutableCopy() {
        return ImmutableList.copyOf(this.backing);
    }
}
