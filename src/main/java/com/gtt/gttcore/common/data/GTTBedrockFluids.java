package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.bedrockfluid.BedrockFluidDefinition;
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

@SuppressWarnings("unused")
public class GTTBedrockFluids {
    public static final Map<ResourceLocation, BedrockFluidDefinition> toReRegister = new HashMap<>();

    public static BedrockFluidDefinition HELIUM_3 = create(GTCEu.id("helium_3_deposit"), builder -> builder
            .fluid(GTMaterials.Helium3::getFluid)
            .weight(30)
            .yield(100, 200)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(30)
            .dimensions(moon()));
    public static BedrockFluidDefinition RADON = create(GTCEu.id("radon_deposit"), builder -> builder
            .fluid(GTMaterials.Radon::getFluid)
            .weight(40)
            .yield(100, 200)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(30)
            .dimensions(moon()));
    public static BedrockFluidDefinition DEUTERIUM = create(GTCEu.id("deuterium_deposit"), builder -> builder
            .fluid(GTMaterials.Deuterium::getFluid)
            .weight(60)
            .yield(125, 250)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(30)
            .dimensions(moon()));







    public static BedrockFluidDefinition HYDROCHLORIC_ACID = create(GTCEu.id("hydrochloric_acid_deposit"), builder -> builder
            .fluid(GTMaterials.HydrochloricAcid::getFluid)
            .weight(30)
            .yield(125, 250)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(30)
            .dimensions(mars()));
    public static BedrockFluidDefinition NITROGEN = create(GTCEu.id("nitrogen_deposit"), builder -> builder
            .fluid(GTMaterials.Nitrogen::getFluid)
            .weight(60)
            .yield(2000, 2750)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(1750)
            .dimensions(mars()));
    public static BedrockFluidDefinition HYDROGEN = create(GTCEu.id("hydrogen_deposit"), builder -> builder
            .fluid(GTMaterials.Hydrogen::getFluid)
            .weight(60)
            .yield(2000, 2750)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(1750)
            .dimensions(mars()));
    public static BedrockFluidDefinition OXYGEN = create(GTCEu.id("oxygen_deposit"), builder -> builder
            .fluid(GTMaterials.Oxygen::getFluid)
            .weight(60)
            .yield(2000, 2750)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(1750)
            .dimensions(mars()));









    public static BedrockFluidDefinition SULFURIC_ACID = create(GTCEu.id("sulfuric_acid_deposit"), builder -> builder
            .fluid(GTMaterials.SulfuricAcid::getFluid)
            .weight(40)
            .yield(125, 250)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(30)
            .dimensions(venus()));
    public static BedrockFluidDefinition SULFUR_TRIOXIDE = create(GTCEu.id("sulfur_trioxide_deposit"), builder -> builder
            .fluid(GTMaterials.SulfurTrioxide::getFluid)
            .weight(30)
            .yield(250, 750)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(50)
            .dimensions(venus()));
    public static BedrockFluidDefinition FLUORINE = create(GTCEu.id("fluorine_deposit"), builder -> builder
            .fluid(GTMaterials.Fluorine::getFluid)
            .weight(60)
            .yield(2000, 2750)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(1750)
            .dimensions(venus()));
    public static BedrockFluidDefinition CHLORINE = create(GTCEu.id("chlorine_deposit"), builder -> builder
            .fluid(GTMaterials.Chlorine::getFluid)
            .weight(60)
            .yield(2000, 2750)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(1750)
            .dimensions(venus()));
    public static BedrockFluidDefinition SodiumPersulfate = create(GTCEu.id("sodium_persulfate_deposit"), builder -> builder
            .fluid(GTMaterials.SodiumPersulfate::getFluid)
            .weight(20)
            .yield(500, 750)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(250)
            .dimensions(venus()));





    public static BedrockFluidDefinition Toluene = create(GTCEu.id("toluene_deposit"), builder -> builder
            .fluid(GTMaterials.Toluene::getFluid)
            .weight(50)
            .yield(750, 1000)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(250)
            .dimensions(mercury()));
    public static BedrockFluidDefinition TETRAFLUOROETHYLENE = create(GTCEu.id("tetrafluoroethylene_deposit"), builder -> builder
            .fluid(GTMaterials.Tetrafluoroethylene::getFluid)
            .weight(40)
            .yield(500, 750)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(250)
            .dimensions(mercury()));
    public static BedrockFluidDefinition NITROBENZENE = create(GTCEu.id("nitrobenzene_deposit"), builder -> builder
            .fluid(GTMaterials.Nitrobenzene::getFluid)
            .weight(30)
            .yield(750, 1000)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(250)
            .dimensions(mercury()));
    public static BedrockFluidDefinition WOOD_VINEGAR = create(GTCEu.id("wood_vinegar_deposit"), builder -> builder
            .fluid(GTMaterials.WoodVinegar::getFluid)
            .weight(40)
            .yield(750, 1000)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(250)
            .dimensions(mercury()));








    public static BedrockFluidDefinition METHANE = create(GTCEu.id("methane_deposit"), builder -> builder
            .fluid(GTMaterials.Methane::getFluid)
            .weight(50)
            .yield(500, 750)
            .depletionAmount(1)
            .depletionChance(100)
            .depletedYield(250)
            .dimensions(Set.of(Planet.VENUS, Planet.MARS, Planet.MOON)));
    public static void init() {
        toReRegister.forEach(GTRegistries.BEDROCK_FLUID_DEFINITIONS::registerOrOverride);
    }

    public static BedrockFluidDefinition create(ResourceLocation id,
                                                Consumer<BedrockFluidDefinition.Builder> consumer) {
        BedrockFluidDefinition.Builder builder = BedrockFluidDefinition.builder(id);
        consumer.accept(builder);

        BedrockFluidDefinition definition = builder.build();
        toReRegister.put(id, definition);
        return definition;
    }

    public static Set<ResourceKey<Level>> moon() {
        return Set.of(Planet.MOON);
    }

    public static Set<ResourceKey<Level>> mars() {
        return Set.of(Planet.MARS);
    }

    public static Set<ResourceKey<Level>> venus() {
        return Set.of(Planet.VENUS);
    }

    public static Set<ResourceKey<Level>> mercury() {
        return Set.of(Planet.MERCURY);
    }

}
