package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.GTModels;
import com.gtt.gttcore.GTTCore;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

public class GTTBlocks {
    public static final BlockEntry<Block> CASING_IRIDIUM_REINFORCED = createCasingBlock("reinforced_machine_casing",
            GTTCore.id("block/casings/machine_casing_reinforced"));
    public static final BlockEntry<Block> CASING_LEAD_RADIATION_PROOF = createCasingBlock("radiation_proof_machine_casing",
            GTTCore.id("block/casings/machine_casing_radiation_proof"));
    public static final BlockEntry<Block> CASING_ZIRCONIUM_PIPE = createCasingBlock("zirconium_pipe_casing",
            GTTCore.id("block/casings/machine_casing_pipe_zirconium"));
    public static final BlockEntry<Block> CASING_LOW_NEUTRON_ABSORPTION = createCasingBlock("low_neutron_absorption_casing",
            GTTCore.id("block/casings/low_neutron_absorption_casing"));
    public static final BlockEntry<Block> CASING_NAQUADAH_ALLOY_TURBINE = createCasingBlock("naquadah_alloy_turbine_casing",
            GTTCore.id("block/casings/machine_casing_turbine_naquadah_alloy"));
    public static final BlockEntry<Block> CASING_LEAD_GLASS = createGlassCasingBlock("lead_glass",
            GTTCore.id("block/casings/lead_glass"), () -> RenderType::translucent);

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }
    private static BlockEntry<Block> createGlassCasingBlock(String name, ResourceLocation texture,
                                                            Supplier<Supplier<RenderType>> type) {
        return createCasingBlock(name, GlassBlock::new, texture, () -> Blocks.GLASS, type);
    }
    @SuppressWarnings("removal")
    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .blockstate(GTModels.cubeAllModel(name, texture))
                .tag(TagUtil.createBlockTag("mineable/wrench"), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static void init(){

    }
}
