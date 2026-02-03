package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.client.util.TooltipHelper;
import com.gregtechceu.gtceu.common.data.GTMedicalConditions;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ParallelHatchPartMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.RotorHolderPartMachine;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.api.LangUtil;
import com.gtt.gttcore.client.renderers.LargeRotorHolderRenderer;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.gtt.gttcore.common.machine.CreativeHighEnergyLaserProviderMachine;
import com.gtt.gttcore.common.machine.multiblock.GTTPartAbility;
import com.gtt.gttcore.common.machine.multiblock.part.HighEnergyLaserHatchPartMachine;
import com.gtt.gttcore.data.lang.GTTChineseLanguageProvider;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createOverlayTieredHullMachineModel;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;
import static net.minecraft.ChatFormatting.RESET;

public class GTTMachines {
    public static void init() {

    }
    public static final MachineDefinition[] LARGE_ROTOR_HOLDER = registerTieredMachines("large_rotor_holder",
            RotorHolderPartMachine::new,
            (tier, builder) -> builder
                    .langValue("%s Large Rotor Holder".formatted(VNF[tier]))
                    .rotationState(RotationState.ALL)
                    .abilities(GTTPartAbility.LARGE_ROTOR_HOLDER)
                    .modelProperty(GTMachineModelProperties.IS_FORMED, false)
                    .model(
                            createOverlayTieredHullMachineModel(GTCEu.id("block/machine/template/rotor_holder/block"))
                                    .andThen(b -> b.addDynamicRenderer(LargeRotorHolderRenderer::new))
                    )
                    .hasBER(true)
                    .tooltips(LangHandler.getMultiLang("gtceu.machine.rotor_holder.tooltip"))
                    .tooltips(Component.translatable("gtceu.part_sharing.disabled"))
                    .register(),
            GTValues.tiersBetween(HV, OpV));

    public static final MachineDefinition HIGH_ENERGY_LASER_EXPORT_HATCH = REGISTRATE
            .machine("high_energy_laser_export_hatch", holder -> new HighEnergyLaserHatchPartMachine(holder, true))
            .rotationState(RotationState.ALL)
            .tier(UHV)
            .abilities(GTTPartAbility.EXPORT_HIGH_ENERGY_LASER)
            .workableTieredHullModel(GTCEu.id("laser_source_hatch"))
            .tooltips(Component.translatable(LangUtil.createEnZhTranslation(
                    "gttcore.machine.high_energy_hatch.export.tooltip",
                    "High Energy Laser Output for Multiblocks",
                    "为多方块结构输出高能激光")))
            .register();
    public static final MachineDefinition HIGH_ENERGY_LASER_IMPORT_HATCH = REGISTRATE
            .machine("high_energy_laser_import_hatch", holder -> new HighEnergyLaserHatchPartMachine(holder, false))
            .rotationState(RotationState.ALL)
            .tier(UHV)
            .abilities(GTTPartAbility.IMPORT_HIGH_ENERGY_LASER)
            .workableTieredHullModel(GTCEu.id("laser_target_hatch"))
            .tooltips(Component.translatable(LangUtil.createEnZhTranslation(
                    "gttcore.machine.high_energy_hatch.export.tooltip",
                    "High Energy Laser Input for Multiblocks",
                    "为多方块结构输入高能激光")))
            .register();


    public static final MachineDefinition ULV_BENDER = registerULVMachines("bender", GTRecipeTypes.BENDER_RECIPES, "卷板机");
    public static final MachineDefinition ULV_COMPRESSOR = registerULVMachines("compressor", GTRecipeTypes.COMPRESSOR_RECIPES, "压缩机");
    public static final MachineDefinition ULV_ELECTRIC_FURNACE = registerULVMachines("electric_furnace", GTRecipeTypes.FURNACE_RECIPES, "电炉");
    public static final MachineDefinition ULV_ALLOY_SMELTER = registerULVMachines("alloy_smelter", GTRecipeTypes.ALLOY_SMELTER_RECIPES, "合金炉");
    public static final MachineDefinition ULV_FLUID_HEATER = registerULVMachines("fluid_heater", GTRecipeTypes.FLUID_HEATER_RECIPES, hvCappedTankSizeFunction, "流体加热器");
    public static final MachineDefinition ULV_FORGE_HAMMER = registerULVMachines("forge_hammer", GTRecipeTypes.FORGE_HAMMER_RECIPES, "锻造锤");
    public static final MachineDefinition ULV_ORE_WASHER = registerULVMachines("ore_washer", GTRecipeTypes.ORE_WASHER_RECIPES, "洗矿厂");
    public static final MachineDefinition ULV_CHEMICAL_REACTOR = registerULVMachines("chemical_reactor", GTRecipeTypes.CHEMICAL_RECIPES, "化学反应釜");
    public static final MachineDefinition ULV_CHEMICAL_BATH = registerULVMachines("chemical_bath", GTRecipeTypes.CHEMICAL_BATH_RECIPES, "化学浸洗机");
    public static final MachineDefinition ULV_CENTRIFUGE = registerULVMachines("centrifuge", GTRecipeTypes.CENTRIFUGE_RECIPES, "离心机");
    public static final MachineDefinition ULV_CUTTER = registerULVMachines("cutter", GTRecipeTypes.CUTTER_RECIPES, "切割机");
    public static final MachineDefinition ULV_MIXER = registerULVMachines("mixer", GTRecipeTypes.MIXER_RECIPES, hvCappedTankSizeFunction, "搅拌机");
    public static final MachineDefinition ULV_WIREMILL = registerULVMachines("wiremill", GTRecipeTypes.WIREMILL_RECIPES, "线材轧机");
    public static final MachineDefinition ULV_MACERATOR = registerULVMachines("macerator", GTRecipeTypes.MACERATOR_RECIPES, "研磨机");
    public static final MachineDefinition ULV_LATHE = registerULVMachines("lathe", GTRecipeTypes.LATHE_RECIPES, "车床");
    public static final MachineDefinition ULV_EXTRACTOR = registerULVMachines("extractor", GTRecipeTypes.EXTRACTOR_RECIPES, "提取机");
    public static final MachineDefinition ULV_FLUID_SOLIDIFIER = registerULVMachines("fluid_solidifier", GTRecipeTypes.FLUID_SOLIDFICATION_RECIPES, "流体固化机");
    public static final MachineDefinition ULV_BREWERY = registerULVMachines("brewery", GTRecipeTypes.BREWING_RECIPES, "酿造室");
    public static final MachineDefinition ULV_FERMENTER = registerULVMachines("fermenter", GTRecipeTypes.FERMENTING_RECIPES, "发酵槽");
    public static final MachineDefinition ULV_FORMING_PRESS = registerULVMachines("forming_press", GTRecipeTypes.FORMING_PRESS_RECIPES, "冲压机床");
    public static final MachineDefinition ULV_POLARIZER = registerULVMachines("polarizer", GTRecipeTypes.POLARIZER_RECIPES, "两极磁化机");
    public static final MachineDefinition ULV_SIFTER = registerULVMachines("sifter", GTRecipeTypes.SIFTER_RECIPES, "筛选机");
    public static final MachineDefinition ULV_DISTILLERY = registerULVMachines("distillery", GTRecipeTypes.DISTILLERY_RECIPES, hvCappedTankSizeFunction, "蒸馏室");
    public static final MachineDefinition ULV_ELECTROLYZER = registerULVMachines("electrolyzer", GTRecipeTypes.ELECTROLYZER_RECIPES, largeTankSizeFunction, "电解机");
    public static final MachineDefinition ULV_ELECTROMAGNETIC_SEPARATOR = registerULVMachines("electromagnetic_separator", GTRecipeTypes.ELECTROMAGNETIC_SEPARATOR_RECIPES, "电磁选矿机");
    public static final MachineDefinition ULV_AUTOCLAVE = registerULVMachines("autoclave", GTRecipeTypes.AUTOCLAVE_RECIPES, "高压釜");
    public static final MachineDefinition ULV_ASSEMBLER = registerULVMachines("assembler", GTRecipeTypes.ASSEMBLER_RECIPES, hvCappedTankSizeFunction, true, "组装机");
    public static final MachineDefinition ULV_GAS_COLLECTOR = registerULVMachines("gas_collector",
            GTRecipeTypes.GAS_COLLECTOR_RECIPES, largeTankSizeFunction, true);



    public static final MachineDefinition[] CULTIVATOR = registerSimpleMachines("cultivator", GTTRecipeTypes.CULTIVATOR_RECIPES, GTCEu.id("block/machines/fermenter" ));


    public static final MachineDefinition[] HIGHER_PARALLEL_HATCH = registerTieredMachines("parallel_hatch",
            ParallelHatchPartMachine::new,
            (tier, builder) -> builder
                    .langValue(switch (tier) {
                        case 9 -> "UHV";
                        case 10 -> "UEV";
                        case 11 -> "UIV";
                        case 12 -> "UXV";
                        default -> "Simple"; // Should never be hit.
                    } + " Parallel Control Hatch")
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.PARALLEL_HATCH)
                    .workableTieredHullModel(GTCEu.id("block/machines/parallel_hatch_mk" + (tier - 4) % 4))
                    .tooltips(Component.translatable(LangUtil.createEnZhTranslation("gttcore.machine.parallel_hatch_mk" + tier + ".tooltip", "Allows to run up to %s recipes in parallel.".formatted((int) Math.pow(4, tier - GTValues.EV) * 4), "允许同时处理至多%s个配方。".formatted(Math.pow(4, tier - GTValues.EV) * 4))))
                    .register(),
            UHV, UEV, UIV);
    public static final Pair<MachineDefinition, MachineDefinition> STEAM_CENTRIFUGE = registerSimpleSteamMachines(
            "centrifuge", GTRecipeTypes.CENTRIFUGE_RECIPES);
    public static final Pair<MachineDefinition, MachineDefinition> STEAM_MIXER = registerSimpleSteamMachines(
            "mixer", GTRecipeTypes.MIXER_RECIPES);


    public static final MachineDefinition ULV_STEAM_TURBINE = registerSimpleGenerator("steam_engine",
            GTRecipeTypes.STEAM_TURBINE_FUELS, steamGeneratorTankSizeFunction, 0.0f, GTValues.ULV)[0];

    public static final BiConsumer<ItemStack, List<Component>> CREATIVE_TOOLTIPS = (stack, list) -> list.add(
            Component.translatable("gtceu.creative_tooltip.1")
                    .append(Component.translatable("gtceu.creative_tooltip.2")
                            .withStyle(TooltipHelper.RAINBOW_HSL_SLOW))
                    .append(Component.translatable("gtceu.creative_tooltip.3")));
    public static final MachineDefinition CREATIVE_HIGH_ENERGY_LASER_PROVIDER = REGISTRATE
            .machine("creative_high_energy_laser_provider", CreativeHighEnergyLaserProviderMachine::new)
            .rotationState(RotationState.ALL)
            .tooltipBuilder(CREATIVE_TOOLTIPS)
            .tier(UHV)
            .workableTieredHullModel(
                    GTCEu.id("laser_source_hatch")
            )
            .register();




















    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static Pair<MachineDefinition, MachineDefinition> registerSimpleSteamMachines(String name,
                                                                                         GTRecipeType recipeType) {
        return registerSteamMachines("steam_" + name, SimpleSteamMachine::new, (pressure, builder) -> builder
                .rotationState(RotationState.ALL)
                .recipeType(recipeType)
                .recipeModifier(SimpleSteamMachine::recipeModifier)
                .workableSteamHullModel(pressure, GTCEu.id("block/machines/" + name))
                .register());
    }

    public static Pair<MachineDefinition, MachineDefinition> registerSteamMachines(String name,
                                                                                   BiFunction<IMachineBlockEntity, Boolean, MetaMachine> factory,
                                                                                   BiFunction<Boolean, MachineBuilder<MachineDefinition>, MachineDefinition> builder) {
        MachineDefinition lowTier = builder.apply(false,
                REGISTRATE.machine("lp_%s".formatted(name), holder -> factory.apply(holder, false))
                        .langValue("Low Pressure " + FormattingUtil.toEnglishName(name))
                        .tier(0));
        MachineDefinition highTier = builder.apply(true,
                REGISTRATE.machine("hp_%s".formatted(name), holder -> factory.apply(holder, true))
                        .langValue("High Pressure " + FormattingUtil.toEnglishName(name))
                        .tier(1));
        return Pair.of(lowTier, highTier);
    }

    public static MachineDefinition[] registerSimpleGenerator(String name,
                                                              GTRecipeType recipeType,
                                                              Int2IntFunction tankScalingFunction,
                                                              float hazardStrengthPerOperation,
                                                              int... tiers) {
        return registerTieredMachines(name,
                (holder, tier) -> new SimpleGeneratorMachine(holder, tier, hazardStrengthPerOperation * tier,
                        tankScalingFunction),
                (tier, builder) -> builder
                        .langValue("%s %s Generator %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                        .editableUI(SimpleGeneratorMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                        .rotationState(RotationState.ALL)
                        .recipeType(recipeType)
                        .recipeModifier(SimpleGeneratorMachine::recipeModifier, true)
                        .addOutputLimit(ItemRecipeCapability.CAP, 0)
                        .addOutputLimit(FluidRecipeCapability.CAP, 0)
                        .simpleGeneratorModel(GTTCore.id("block/generators/" + name))
                        .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType,
                                tankScalingFunction.applyAsInt(tier), false))
                        .register(),
                tiers);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             boolean hasPollutionDebuff,
                                                             ResourceLocation texture) {
        return registerSimpleMachines(name, recipeType, tankScalingFunction, hasPollutionDebuff, texture, ELECTRIC_TIERS);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction, ResourceLocation texture) {
        return registerSimpleMachines(name, recipeType, tankScalingFunction, false, texture);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType, ResourceLocation texture) {
        return registerSimpleMachines(name, recipeType, defaultTankSizeFunction, texture);
    }

    public static MachineDefinition[] registerSimpleMachines(String name,
                                                             GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             boolean hasPollutionDebuff,
                                                             ResourceLocation texture,
                                                             int... tiers) {
        return registerTieredMachines(name,
                (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> {
                    if (hasPollutionDebuff) {
                        builder.recipeModifiers(GTRecipeModifiers.ENVIRONMENT_REQUIREMENT
                                                .apply(GTMedicalConditions.CARBON_MONOXIDE_POISONING, 100 * tier),
                                        GTRecipeModifiers.OC_NON_PERFECT)
                                .conditionalTooltip(defaultEnvironmentRequirement(),
                                        ConfigHolder.INSTANCE.gameplay.environmentalHazards);
                    } else {
                        builder.recipeModifier(GTRecipeModifiers.OC_NON_PERFECT);
                    }
                    return builder
                            .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                            .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                            .rotationState(RotationState.NON_Y_AXIS)
                            .recipeType(recipeType)
                            .workableTieredHullModel(texture)
                            .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType,
                                    tankScalingFunction.applyAsInt(tier), true))
                            .register();
                },
                tiers);
    }

    public static MachineDefinition registerULVMachines(String name, GTRecipeType recipeType) {
        return registerULVMachines(name, recipeType, defaultTankSizeFunction);
    }
    public static MachineDefinition registerULVMachines(String name, GTRecipeType recipeType, String chineseName) {
        GTTChineseLanguageProvider.chineseNameMap.put("block.gttcore.ulv_" + name, VCF[ULV] + "原始" + chineseName);
        return registerULVMachines(name, recipeType, defaultTankSizeFunction);
    }

    public static MachineDefinition registerULVMachines(String name, GTRecipeType recipeType, Int2IntFunction tankScalingFunction) {
        return registerULVMachines(name, recipeType, tankScalingFunction, false);
    }
    public static MachineDefinition registerULVMachines(String name, GTRecipeType recipeType, Int2IntFunction tankScalingFunction, String chineseName) {
        GTTChineseLanguageProvider.chineseNameMap.put("block.gttcore.ulv_" + name, VCF[ULV] + "原始" + chineseName + RESET);
        return registerULVMachines(name, recipeType, tankScalingFunction, false);
    }

    public static MachineDefinition registerULVMachines(String name,
                                                          GTRecipeType recipeType,
                                                          Int2IntFunction tankScalingFunction,
                                                          boolean hasPollutionDebuff, String chineseName) {
        GTTChineseLanguageProvider.chineseNameMap.put("block.gttcore.ulv_" + name, VCF[ULV] + "原始" + chineseName + RESET);
        return registerULVMachines(name, recipeType, tankScalingFunction, hasPollutionDebuff);
    }
    public static MachineDefinition registerULVMachines(String name,
                                                          GTRecipeType recipeType,
                                                          Int2IntFunction tankScalingFunction,
                                                          boolean hasPollutionDebuff) {
        return registerTieredMachines(name,
                (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> {
                    if (hasPollutionDebuff) {
                        builder.recipeModifiers(GTRecipeModifiers.ENVIRONMENT_REQUIREMENT
                                                .apply(GTMedicalConditions.CARBON_MONOXIDE_POISONING, 100 * tier),
                                        GTRecipeModifiers.OC_NON_PERFECT)
                                .conditionalTooltip(defaultEnvironmentRequirement(),
                                        ConfigHolder.INSTANCE.gameplay.environmentalHazards);
                    } else {
                        builder.recipeModifier(GTRecipeModifiers.OC_NON_PERFECT);
                    }
                    return builder
                            .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                            .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                            .rotationState(RotationState.NON_Y_AXIS)
                            .recipeType(recipeType)
                            .workableTieredHullModel(GTCEu.id("block/machines/" + name))
                            .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType,
                                    tankScalingFunction.applyAsInt(tier), true))
                            .register();
                },
                ULV)[0];
    }
}
