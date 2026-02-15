package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.block.ActiveBlock;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.models.GTModels;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.util.LangUtil;
import com.gtt.gttcore.common.block.BlackholeBombBlock;
import com.gtt.gttcore.common.block.NuclearBombBlock;
import com.gtt.gttcore.data.lang.GTTChineseLanguageProvider;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

public class GTTBlocks {

    public static final BlockEntry<BlackholeBombBlock> BLACKHOLE_BOMB = REGISTRATE
            .block(LangUtil.createBlockZhTranslation("blackhole_bomb", "黑洞炸弹"), BlackholeBombBlock::new)
            .lang("Black Hole Bomb")
            .properties(p -> p.mapColor(MapColor.FIRE).instabreak().sound(SoundType.ANCIENT_DEBRIS).ignitedByLava())
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(), prov.models().cubeAll(ctx.getName(),
                    GTTCore.id("block/misc/blackhole_bomb")
            )))
            .simpleItem()
            .register();
    public static final BlockEntry<NuclearBombBlock> NUCLEAR_BOMB = REGISTRATE
            .block(LangUtil.createBlockZhTranslation("nuclear_bomb", "核弹"), NuclearBombBlock::new)
            .lang("Nuclear Bomb")
            .properties(p -> p.mapColor(MapColor.FIRE).instabreak().sound(SoundType.ANCIENT_DEBRIS).ignitedByLava())
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(), prov.models().cubeAll(ctx.getName(),
                    GTTCore.id("block/misc/nuclear_bomb")
            )))
            .simpleItem()
            .register();





    public static final BlockEntry<Block> CASING_IRIDIUM_REINFORCED = createCasingBlock("reinforced_machine_casing",
            GTTCore.id("block/casings/machine_casing_reinforced"), "铱强化机械方块");
    public static final BlockEntry<Block> CASING_LEAD_RADIATION_PROOF = createCasingBlock("radiation_proof_machine_casing",
            GTTCore.id("block/casings/machine_casing_radiation_proof"), "铅防辐射机械方块");
    public static final BlockEntry<Block> CASING_ZIRCONIUM_PIPE = createCasingBlock("zirconium_pipe_casing",
            GTTCore.id("block/casings/machine_casing_pipe_zirconium"), "锆管道方块");
    public static final BlockEntry<Block> CASING_LOW_NEUTRON_ABSORPTION = createCasingBlock("low_neutron_absorption_casing",
            GTTCore.id("block/casings/low_neutron_absorption_casing"), "锆低中子吸收机械方块");
    public static final BlockEntry<Block> CASING_INCONEL_718_TURBINE = createCasingBlock("inconel_718_turbine_casing",
            GTTCore.id("block/casings/machine_casing_turbine_inconel_718"), "镍基合金-718涡轮机械方块");
    public static final BlockEntry<Block> CASING_STEEL_SEALED = createCasingBlock("sealed_casing",
            GTTCore.id("block/casings/machine_casing_sealed_steel"), "密封钢机械方块");
    public static final BlockEntry<Block> CASING_LEAD_GLASS = createGlassCasingBlock("lead_glass",
            GTTCore.id("block/casings/lead_glass"), () -> RenderType::translucent, "铅玻璃");
    public static final BlockEntry<Block> CASING_PROCESSING = createCasingBlock("processing_casing",
            GTTCore.id("block/casings/processing_casing"), "加工机械方块");
    public static final BlockEntry<Block> CASING_REINFORCED_CONCRETE = createCasingBlock("reinforced_concrete",
            GTTCore.id("block/casings/reinforced_concrete"), "强化混凝土");
    public static final BlockEntry<Block> CASING_FIELD_PROTECTION = createCasingBlock("field_protection_casing",
            GTTCore.id("block/casings/field_protection_casing"), "力场防护机械方块");

    public static final BlockEntry<Block> CASING_STAINLESS_EVAPORATION = createCasingBlock(
            "stainless_evaporation_casing",
            GTCEu.id("block/casings/solid/machine_casing_stainless_evaporation"), "不锈钢蒸发机械方块");
    public static final BlockEntry<ActiveBlock> STEAM_CRUSHING_WHEELS = createActiveCasing("steam_crushing_wheels",
            "block/steam_crushing_wheels", "蒸汽粉碎轮");
    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture, String chineseName) {
        GTTChineseLanguageProvider.chineseNameMap.put("block.gttcore." + name, chineseName);
        return createCasingBlock(name, texture);
    }
    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }
    private static BlockEntry<Block> createGlassCasingBlock(String name, ResourceLocation texture,
                                                            Supplier<Supplier<RenderType>> type, String chineseName) {
        GTTChineseLanguageProvider.chineseNameMap.put("block.gttcore." + name, chineseName);
        return createGlassCasingBlock(name, texture, type);
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

    protected static BlockEntry<ActiveBlock> createActiveCasing(String name, String baseModelPath, String chineseName) {
        GTTChineseLanguageProvider.chineseNameMap.put("block.gttcore." + name, chineseName);
        return createActiveCasing(name, baseModelPath);
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
