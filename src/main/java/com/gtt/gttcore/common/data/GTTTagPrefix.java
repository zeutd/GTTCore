package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class GTTTagPrefix {
    public static void init(){

    }
    public static final TagPrefix oreMoon = TagPrefix.oreTagPrefix("moon", BlockTags.MINEABLE_WITH_PICKAXE)
            .langValue("Moon %s Ore")
                .registerOre(() -> BuiltInRegistries.BLOCK.get(new ResourceLocation("ad_astra", "moon_stone")).defaultBlockState(),
                        () -> GTTMaterials.MoonStone, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        new ResourceLocation("ad_astra", "block/moon_stone"), true, false, true);
    public static final TagPrefix oreMars = TagPrefix.oreTagPrefix("mars", BlockTags.MINEABLE_WITH_PICKAXE)
            .langValue("Mars %s Ore")
                .registerOre(() -> BuiltInRegistries.BLOCK.get(new ResourceLocation("ad_astra", "mars_stone")).defaultBlockState(),
                        () -> GTTMaterials.MarsStone, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        new ResourceLocation("ad_astra", "block/mars_stone"), true, false, true);
    public static final TagPrefix oreMercury = TagPrefix.oreTagPrefix("mercury", BlockTags.MINEABLE_WITH_PICKAXE)
            .langValue("Mercury %s Ore")
                .registerOre(() -> BuiltInRegistries.BLOCK.get(new ResourceLocation("ad_astra", "mercury_stone")).defaultBlockState(),
                        () -> GTTMaterials.MercuryStone, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        new ResourceLocation("ad_astra", "block/mercury_stone"), true, false, true);
    public static final TagPrefix oreVenus = TagPrefix.oreTagPrefix("venus", BlockTags.MINEABLE_WITH_PICKAXE)
            .langValue("Venus %s Ore")
                .registerOre(() -> BuiltInRegistries.BLOCK.get(new ResourceLocation("ad_astra", "venus_stone")).defaultBlockState(),
                        () -> GTTMaterials.VenusStone, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        new ResourceLocation("ad_astra", "block/venus_stone"), true, false, true);
    public static final TagPrefix oreGlacio = TagPrefix.oreTagPrefix("glacio", BlockTags.MINEABLE_WITH_PICKAXE)
            .langValue("Glacio %s Ore")
                .registerOre(() -> BuiltInRegistries.BLOCK.get(new ResourceLocation("ad_astra", "glacio_stone")).defaultBlockState(),
                        () -> GTTMaterials.GlacioStone, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                        new ResourceLocation("ad_astra", "block/glacio_stone"), true, false, true);
}
