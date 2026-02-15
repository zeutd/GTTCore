package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.worldgen.IWorldGenLayer;
import com.gregtechceu.gtceu.api.data.worldgen.SimpleWorldGenLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.fml.ModList;

import java.util.Set;

@SuppressWarnings("removal")
public class GTTWorldGenLayers {
    private static TagKey<Block> moonstone;
    private static TagKey<Block> marsstone;
    private static TagKey<Block> mercurystone;
    private static TagKey<Block> venusstone;
    private static TagKey<Block> glaciostone;

    public static IWorldGenLayer MOON;
    public static IWorldGenLayer MARS;
    public static IWorldGenLayer MERCURY;
    public static IWorldGenLayer VENUS;
    public static IWorldGenLayer GLACIO;


    public static void init() {
        if (ModList.get().isLoaded("ad_astra")) {

            moonstone = BlockTags.create(new ResourceLocation("ad_astra", "moon_stone_replaceables"));
            marsstone = BlockTags.create(new ResourceLocation("ad_astra", "mars_stone_replaceables"));
            mercurystone = BlockTags.create(new ResourceLocation("ad_astra", "mercury_stone_replaceables"));
            venusstone = BlockTags.create(new ResourceLocation("ad_astra", "venus_stone_replaceables"));
            glaciostone = BlockTags.create(new ResourceLocation("ad_astra", "glacio_stone_replaceables"));

            MOON = new SimpleWorldGenLayer("ad_astra_moon", () -> new TagMatchTest(moonstone), Set.of(new ResourceLocation("ad_astra:moon")));
            MARS = new SimpleWorldGenLayer("ad_astra_mars", () -> new TagMatchTest(marsstone), Set.of(new ResourceLocation("ad_astra:mars")));
            MERCURY = new SimpleWorldGenLayer("ad_astra_mercury", () -> new TagMatchTest(mercurystone), Set.of(new ResourceLocation("ad_astra:mercury")));
            VENUS = new SimpleWorldGenLayer("ad_astra_venus", () -> new TagMatchTest(venusstone), Set.of(new ResourceLocation("ad_astra:venus")));
            GLACIO = new SimpleWorldGenLayer("ad_astra_glacio", () -> new TagMatchTest(glaciostone), Set.of(new ResourceLocation("ad_astra:glacio")));

        }
    }
}