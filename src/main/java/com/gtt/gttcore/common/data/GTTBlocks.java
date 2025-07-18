package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.block.ActiveBlock;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.models.GTModels;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
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
    public static final BlockEntry<Block> CASING_STEEL_SEALED = createCasingBlock("sealed_casing",
            GTTCore.id("block/casings/machine_casing_sealed_steel"));
    public static final BlockEntry<Block> CASING_LEAD_GLASS = createGlassCasingBlock("lead_glass",
            GTTCore.id("block/casings/lead_glass"), () -> RenderType::translucent);


    public static final BlockEntry<ActiveBlock> STEAM_CRUSHING_WHEELS = createActiveCasing("steam_crushing_wheels",
            "block/steam_crushing_wheels");
    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }
    private static BlockEntry<Block> createGlassCasingBlock(String name, ResourceLocation texture,
                                                            Supplier<Supplier<RenderType>> type) {
        return createCasingBlock(name, GlassBlock::new, texture, () -> Blocks.GLASS, type);
    }
    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .exBlockstate(GTModels.cubeAllModel(texture))
                .tag(TagUtil.createBlockTag("mineable/wrench"), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    protected static BlockEntry<ActiveBlock> createActiveCasing(String name, String baseModelPath) {
        return REGISTRATE.block(name, ActiveBlock::new)
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate(GTModels.createActiveModel(GTTCore.id(baseModelPath)))
                .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
                .item(BlockItem::new)
                .model((ctx, prov) -> prov.withExistingParent(prov.name(ctx), GTTCore.id(baseModelPath)))
                .build()
                .register();
    }

    public static void init(){

    }
}
