package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.worldgen.GTLayerPattern;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGeneratorUtils;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.api.data.worldgen.generator.veins.DikeVeinGenerator;
import com.gregtechceu.gtceu.api.data.worldgen.generator.veins.VeinedVeinGenerator;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTOres;
import com.gtt.gttcore.GTTCore;
import com.tterrag.registrate.AbstractRegistrate;
import earth.terrarium.adastra.common.registry.ModBlocks;
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

@SuppressWarnings("unused")
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
    public static final GTOreDefinition BAUXITE_VEIN_MOON = create("bauxite_vein_moon", vein -> vein
            .clusterSize(UniformInt.of(32, 40)).density(0.3f).weight(40)
            .layer(AbstractRegistrate.isDevEnvironment() ? WorldGenLayers.STONE : WorldGenLayers.getByName("ad_astra_moon"))
            .heightRangeUniform(10, 80)
            .layeredVeinGenerator(generator -> generator
                    .withLayerPattern(() -> GTLayerPattern.builder(MOON_RULES)
                            .layer(l -> l.weight(2).state(ModBlocks.MOON_STONE.get()::defaultBlockState).size(1, 6))
                            .layer(l -> l.weight(2).mat(Bauxite).size(1, 4))
                            .layer(l -> l.weight(1).mat(Ilmenite).size(1, 2))
                            .layer(l -> l.weight(1).mat(Aluminium).size(1, 1))
                            .build()))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(Bauxite)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));
    //////////////////////////////////////
    // ******** Mars Vein *********//
    //////////////////////////////////////
    public static RuleTest[] MARS_RULES = new RuleTest[]{new TagMatchTest(ModBlockTags.MARS_STONE_REPLACEABLES)};
    public static final GTOreDefinition PITCHBLENDE_VEIN_MARS = create("pitchblende_vein_mars", vein -> vein
            .clusterSize(UniformInt.of(32, 64)).density(0.25f).weight(30)
            .layer(AbstractRegistrate.isDevEnvironment() ? WorldGenLayers.STONE : WorldGenLayers.getByName("ad_astra_mars"))
            .heightRangeUniform(30, 60)
            .cuboidVeinGenerator(generator -> generator
                    .top(b -> b.mat(Pitchblende).size(2))
                    .middle(b -> b.mat(Pitchblende).size(3))
                    .bottom(b -> b.mat(Pitchblende).size(2))
                    .spread(b -> b.mat(Uraninite)))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(Pitchblende)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));
    public static final GTOreDefinition THORIUM_VEIN = create("thorium_vein", vein -> vein
            .clusterSize(UniformInt.of(50, 64)).density(0.75f).weight(30)
            .layer(WorldGenLayers.NETHERRACK)
            .heightRangeUniform(5, 30)
            .biomes(BiomeTags.IS_NETHER)
            .dikeVeinGenerator(generator -> generator
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Glowstone, 3, 5, 30))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Monazite, 2, 5, 19))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Thorium, 1, 16, 30)))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(Thorium)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));

    public static final GTOreDefinition SCHEELITE_VEIN_MARS = create("scheelite_vein_mars", vein -> vein
            .clusterSize(UniformInt.of(50, 64)).density(0.7f).weight(20)
            .layer(AbstractRegistrate.isDevEnvironment() ? WorldGenLayers.STONE : WorldGenLayers.getByName("ad_astra_mars"))
            .heightRangeUniform(20, 60)
            .dikeVeinGenerator(generator -> generator
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Scheelite, 3, 20, 60))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Tungstate, 2, 35, 55))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Lithium, 1, 20, 40)))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(Scheelite)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));
    public static final GTOreDefinition ALUMINIUM_VEIN_MARS = create("aluminium_vein_mars", vein -> vein
            .clusterSize(UniformInt.of(32, 40)).density(0.3f).weight(40)
            .layer(AbstractRegistrate.isDevEnvironment() ? WorldGenLayers.STONE : WorldGenLayers.getByName("ad_astra_mars"))
            .heightRangeUniform(10, 80)
            .layeredVeinGenerator(generator -> generator
                    .withLayerPattern(() -> GTLayerPattern.builder(MARS_RULES)
                            .layer(l -> l.weight(2).state(ModBlocks.MOON_STONE.get()::defaultBlockState).size(1, 6))
                            .layer(l -> l.weight(2).mat(Aluminium).size(1, 4))
                            .layer(l -> l.weight(1).mat(Ilmenite).size(1, 2))
                            .layer(l -> l.weight(1).mat(Aluminium).size(1, 1))
                            .layer(l -> l.weight(1).mat(Aluminium).size(1, 3))
                            .build()))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(Aluminium)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));
    //////////////////////////////////////
    // ******** Venus Vein *********//
    //////////////////////////////////////
    public static RuleTest[] VENUS_RULES = new RuleTest[]{new TagMatchTest(ModBlockTags.VENUS_STONE_REPLACEABLES)};

    public static final GTOreDefinition NAQUADAH_VEIN_VENUS = create("naquadah_vein_venus", vein -> vein
            .clusterSize(UniformInt.of(48, 80)).density(0.25f).weight(30)
            .layer(AbstractRegistrate.isDevEnvironment() ? WorldGenLayers.STONE : WorldGenLayers.getByName("ad_astra_venus"))
            .heightRangeUniform(10, 90)
            .cuboidVeinGenerator(generator -> generator
                    .top(b -> b.mat(Naquadah).size(2))
                    .middle(b -> b.mat(Naquadah).size(3))
                    .bottom(b -> b.mat(Naquadah).size(2))
                    .spread(b -> b.mat(Plutonium239)))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(Naquadah)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));
    public static final GTOreDefinition SHELDONITE_VEIN_VENUS = create("sheldonite_vein_venus", vein -> vein
            .clusterSize(UniformInt.of(25, 29)).density(0.2f).weight(10)
            .layer(AbstractRegistrate.isDevEnvironment() ? WorldGenLayers.STONE : WorldGenLayers.getByName("ad_astra_venus"))
            .heightRangeUniform(5, 50)
            .layeredVeinGenerator(generator -> generator
                    .withLayerPattern(() -> GTLayerPattern.builder(VENUS_RULES)
                            .layer(l -> l.weight(3).mat(Bornite).size(2, 4))
                            .layer(l -> l.weight(2).mat(Cooperite).size(1, 1))
                            .layer(l -> l.weight(2).mat(Platinum).size(1, 1))
                            .layer(l -> l.weight(1).mat(Palladium).size(1, 1))
                            .build()))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(Platinum)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));
    //////////////////////////////////////
    // ******** End Vein *********//
    //////////////////////////////////////
    public static RuleTest[] END_RULES = new RuleTest[]{WorldGeneratorUtils.END_ORE_REPLACEABLES};
    //////////////////////////////////////
    // ****** Nether Vein *******//
    //////////////////////////////////////
    public static RuleTest[] NETHER_RULES = new RuleTest[]{new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES)};
    public static final GTOreDefinition GLOWSTONE_VEIN = create("glowstone_vein", vein -> vein
            .clusterSize(UniformInt.of(50, 64)).density(0.75f).weight(30)
            .layer(WorldGenLayers.NETHERRACK)
            .heightRangeUniform(5, 30)
            .biomes(BiomeTags.IS_NETHER)
            .dikeVeinGenerator(generator -> generator
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Glowstone, 3, 5, 30))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Scheelite, 2, 5, 19))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Apatite, 1, 16, 30)))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(Glowstone)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));
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
        GTOres.MONAZITE_VEIN.layeredVeinGenerator(generator -> generator
                .withLayerPattern(() -> GTLayerPattern.builder(NETHER_RULES)
                        .layer(l -> l.weight(3).mat(Bastnasite).size(2, 4))
                        .layer(l -> l.weight(1).mat(Monazite).size(1, 1))
                        .layer(l -> l.weight(1).mat(Xenotime).size(1, 1))
                        .build()));
    }
}
