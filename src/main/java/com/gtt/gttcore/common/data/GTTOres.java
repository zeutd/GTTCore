package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.worldgen.GTLayerPattern;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGeneratorUtils;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.api.data.worldgen.generator.veins.DikeVeinGenerator;
import com.gregtechceu.gtceu.api.data.worldgen.generator.veins.VeinedVeinGenerator;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gtt.gttcore.GTTCore;
import com.tterrag.registrate.AbstractRegistrate;
import earth.terrarium.adastra.common.tags.ModBiomeTags;
import earth.terrarium.adastra.common.tags.ModBlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTOres.blankOreDefinition;
import static com.gtt.gttcore.common.data.GTTMaterials.*;

public class GTTOres {
    private static final Map<ResourceLocation, GTOreDefinition> toReRegister = new HashMap<>();

    //////////////////////////////////////
    // ******** Moon Vein *********//
    //////////////////////////////////////
    public static RuleTest[] MOON_RULES = new RuleTest[]{new TagMatchTest(ModBlockTags.MOON_STONE_REPLACEABLES)};
    public static final GTOreDefinition DESH_VEIN = create(
            "desh_vein",
            vein -> vein.clusterSize(
                    UniformInt.of(40, 52))
                    .density(1.0F)
                    .weight(80)
                    .layer(AbstractRegistrate.isDevEnvironment() ? WorldGenLayers.STONE : WorldGenLayers.getByName("ad_astra_moon"))
                    .heightRangeUniform(-40, 40)
                    .veinedVeinGenerator(
                            generator -> generator
                                    .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Desh, 5))
                                    .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Iron, 2))
                                    .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(IceShard, 4))
                                    .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Gallium, 2))
                                    .veininessThreshold(0.01F)
                                    .maxRichnessThreshold(0.175F)
                                    .minRichness(0.7F)
                                    .maxRichness(1.0F)
                                    .edgeRoundoffBegin(3)
                                    .maxEdgeRoundoff(0.1F))
                    .surfaceIndicatorGenerator(indicator -> indicator.surfaceRock(Desh)));
    public static final GTOreDefinition ZIRCON_VEIN = create(
            "zircon_vein",
            vein -> vein.clusterSize(
                            UniformInt.of(40, 52))
                    .density(1.0F)
                    .weight(80)
                    .layer(AbstractRegistrate.isDevEnvironment() ? WorldGenLayers.STONE : WorldGenLayers.getByName("ad_astra_moon"))
            .heightRangeUniform(-40, 40)
                    .veinedVeinGenerator(
            generator -> generator
            .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Ilmenite, 5))
            .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Zircon, 3))
            .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Pyrochlore, 2))
            .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Monazite, 4))
            .veininessThreshold(0.01F)
                                    .maxRichnessThreshold(0.175F)
                                    .minRichness(0.7F)
                                    .maxRichness(1.0F)
                                    .edgeRoundoffBegin(3)
                                    .maxEdgeRoundoff(0.1F))
            .surfaceIndicatorGenerator(indicator -> indicator.surfaceRock(Zircon)));
    //////////////////////////////////////
    // ******** Mars Vein *********//
    //////////////////////////////////////
    public static RuleTest[] MARS_RULES = new RuleTest[]{new TagMatchTest(ModBlockTags.MARS_STONE_REPLACEABLES)};

    //////////////////////////////////////
    // ******** Venus Vein *********//
    //////////////////////////////////////
    public static RuleTest[] VENUS_RULES = new RuleTest[]{new TagMatchTest(ModBlockTags.VENUS_STONE_REPLACEABLES)};


    //////////////////////////////////////
    // ******** End Vein *********//
    //////////////////////////////////////
    public static RuleTest[] END_RULES = new RuleTest[]{WorldGeneratorUtils.END_ORE_REPLACEABLES};
    //////////////////////////////////////
    // ****** Nether Vein *******//
    //////////////////////////////////////
    public static RuleTest[] NETHER_RULES = new RuleTest[]{new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES)};
    //////////////////////////////////////
    // ***** Overworld Vein *****//
    //////////////////////////////////////
    //////////////////////////////////////
    // ***** Stone *****//
    //////////////////////////////////////
    public static RuleTest[] OVERWORLD_RULES = new RuleTest[]{new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)};
    //////////////////////////////////////
    // ***** Deepslate *****//
    //////////////////////////////////////
    public static RuleTest[] DEEPSLATE_RULES = new RuleTest[]{new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)};

    private static GTOreDefinition create(String name, Consumer<GTOreDefinition> config) {
        return create(GTTCore.id(name), config);
    }

    public static GTOreDefinition create(ResourceLocation name, Consumer<GTOreDefinition> config) {
        GTOreDefinition def = blankOreDefinition();
        config.accept(def);
        def.register(name);
        toReRegister.put(name, def);
        return def;
    }

    public static void init() {
        toReRegister.forEach(GTRegistries.ORE_VEINS::registerOrOverride);
    }
}
