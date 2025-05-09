package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.WorkableSteamMachineRenderer;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ParallelHatchPartMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.SteamHatchPartMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.SteamItemBusPartMachine;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gtt.gttcore.common.machine.multiblock.GTTPartAbility;
import com.gtt.gttcore.common.machine.multiblock.part.SteamFluidHatchPartMachine;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.network.chat.Component;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.capability.recipe.IO.IN;
import static com.gregtechceu.gtceu.api.capability.recipe.IO.OUT;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;

public class GTTMachines {
    public static final MachineDefinition[] PARALLEL_HATCH = registerTieredMachines("parallel_hatch",
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
                    .workableTieredHullRenderer(GTCEu.id("block/machines/parallel_hatch_mk" + (tier - 4)))
                    .tooltips(Component.translatable("gtceu.machine.parallel_hatch_mk" + tier + ".tooltip"))
                    .register(),
            UHV, UEV, UIV);
    public static final Pair<MachineDefinition, MachineDefinition> STEAM_CENTRIFUGE = registerSimpleSteamMachines(
            "centrifuge", GTRecipeTypes.CENTRIFUGE_RECIPES);

    public static final MachineDefinition WOOD_FLUID_IMPORT_HATCH = GTRegistration.REGISTRATE
            .machine("wood_input_hatch", holder -> new SteamFluidHatchPartMachine(holder, IN))
            .rotationState(RotationState.ALL)
            .abilities(GTTPartAbility.WOOD_IMPORT_FLUIDS)
            .sidedWorkableCasingRenderer("block/casings/wood_wall",
                    GTCEu.id("block/multiblock/tank_valve"))
            .tooltips(Component.translatable("gtceu.machine.fluid_hatch.export.tooltip"),
                    Component.translatable("gtceu.machine.steam_bus.tooltip"))
            .register();
    public static final MachineDefinition WOOD_FLUID_EXPORT_HATCH = GTRegistration.REGISTRATE
            .machine("wood_output_hatch", holder -> new SteamFluidHatchPartMachine(holder, OUT))
            .rotationState(RotationState.ALL)
            .abilities(GTTPartAbility.WOOD_EXPORT_FLUIDS)
            .sidedWorkableCasingRenderer("block/casings/wood_wall",
                    GTCEu.id("block/multiblock/tank_valve"))
            .tooltips(Component.translatable("gtceu.machine.fluid_hatch.import.tooltip"),
                    Component.translatable("gtceu.machine.steam_bus.tooltip"))
            .register();
    public static final MachineDefinition WOOD_ITEM_IMPORT_BUS = GTRegistration.REGISTRATE
            .machine("wood_input_bus", holder -> new SteamItemBusPartMachine(holder, IN))
            .rotationState(RotationState.ALL)
            .abilities(GTTPartAbility.WOOD_IMPORT_ITEMS)
            .sidedWorkableCasingRenderer("block/casings/wood_wall",
                    GTCEu.id("block/multiblock/tank_valve"))
            .tooltips(Component.translatable("gtceu.machine.fluid_hatch.export.tooltip"),
                    Component.translatable("gtceu.machine.steam_bus.tooltip"))
            .register();
    public static final MachineDefinition WOOD_ITEM_EXPORT_BUS = GTRegistration.REGISTRATE
            .machine("wood_output_bus", holder -> new SteamItemBusPartMachine(holder, OUT))
            .rotationState(RotationState.ALL)
            .abilities(GTTPartAbility.WOOD_EXPORT_ITEMS)
            .sidedWorkableCasingRenderer("block/casings/wood_wall",
                    GTCEu.id("block/multiblock/tank_valve"))
            .tooltips(Component.translatable("gtceu.machine.fluid_hatch.import.tooltip"),
                    Component.translatable("gtceu.machine.steam_bus.tooltip"))
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

    public static void init(){

    }

    public static Pair<MachineDefinition, MachineDefinition> registerSimpleSteamMachines(String name,
                                                                                         GTRecipeType recipeType) {
        return registerSteamMachines("steam_" + name, SimpleSteamMachine::new, (pressure, builder) -> builder
                .rotationState(RotationState.ALL)
                .recipeType(recipeType)
                .recipeModifier(SimpleSteamMachine::recipeModifier)
                .renderer(() -> new WorkableSteamMachineRenderer(pressure, GTCEu.id("block/machines/" + name)))
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
}
