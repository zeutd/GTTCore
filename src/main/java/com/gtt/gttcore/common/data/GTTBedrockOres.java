package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockore.BedrockOreDefinition;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import earth.terrarium.adastra.api.planets.Planet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class GTTBedrockOres {
    public static void init() {

    }

    static {
        GTRegistries.ORE_VEINS.forEach(oreDefinition -> {
            create(GTRegistries.ORE_VEINS.getKey(oreDefinition), bedrockOreDefinition -> {
                bedrockOreDefinition
                        .dimensions(oreDefinition.dimensionFilter())
                        .size(5)
                        .yield(5, 10)
                        .depletionAmount(4)
                        .depletionChance(100)
                        .depletedYield(1)
                        .weight(oreDefinition.weight());
                oreDefinition.veinGenerator().getAllEntries().forEach(entry -> {
                    Material material = entry.mapToMaterial();
                    if (material != null && material != GTMaterials.NULL) {
                        bedrockOreDefinition.material(material, entry.chance());
                    }
                });
            });
        });
    }

    public static BedrockOreDefinition create(ResourceLocation id,
                                                Consumer<BedrockOreDefinition.Builder> consumer) {
        BedrockOreDefinition.Builder builder = BedrockOreDefinition.builder(id);
        consumer.accept(builder);

        return builder.register();
    }
}
