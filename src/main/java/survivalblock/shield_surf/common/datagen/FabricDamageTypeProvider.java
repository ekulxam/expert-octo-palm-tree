package survivalblock.shield_surf.common.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput.OutputType;
import net.minecraft.data.DataOutput.PathResolver;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.util.Identifier;

/**
 * Extend this class and implement {@link #setup}.<p>
 * Register an instance of the class with {@link net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack#addProvider} in a {@link net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint}.
 */
public abstract class FabricDamageTypeProvider implements DataProvider {

    private final PathResolver path;
    private final String modId;
    private final FabricDamageTypesContainer damageTypesContainer = new FabricDamageTypesContainer();
    private final List<String> filenames = new ArrayList<>();

    protected abstract void setup(FabricDamageTypesContainer damageTypesContainer);

    public FabricDamageTypeProvider(FabricDataOutput generator) {
        this.path = generator.getResolver(OutputType.DATA_PACK, "damage_type/");
        this.modId = generator.getModId();
    }

    public CompletableFuture<?> run(DataWriter dataWriter) {
        setup(damageTypesContainer);

        // begin credit
        // modified from https://github.com/Lyinginbedmon/TricksyFoxes/blob/00cfc2bcf17993833db7f8c13f2bfb14913e2ece/src/main/java/com/lying/tricksy/data/TFDamageTypesProvider.java
        // As of writing this on the first of August 2024, Tricksy Foxes is licensed under Creative Commons Zero v1.0 Universal
        // which you can find a copy of at https://github.com/Lyinginbedmon/TricksyFoxes/blob/00cfc2bcf17993833db7f8c13f2bfb14913e2ece/LICENSE
        List<CompletableFuture<?>> futures = Lists.newArrayList();

        damageTypesContainer.forEach(pair -> {
            DamageType type = pair.getSecond();
            JsonObject obj = new JsonObject();
            obj.addProperty("message_id", type.msgId());
            obj.addProperty("exhaustion", type.exhaustion());
            obj.addProperty("scaling", type.scaling().asString());
            obj.addProperty("effects", type.effects().asString());
            obj.addProperty("death_message_type", type.deathMessageType().asString());
            Identifier id = new Identifier(this.modId, pair.getFirst());
            String string = id.toString();
            if (filenames.contains(string)) {
                throw new IllegalStateException("Duplicate damage type definition for " + id);
            } else {
                filenames.add(string);
            }
            futures.add(DataProvider.writeToPath(dataWriter, obj, this.path.resolveJson(id)));
        });
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        // end credit
    }

    public String getName() {
        return "Damage types";
    }
}