package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IRotorHolderMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeTurbineMachine;
import com.gtt.gttcore.GTTCore;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.registerLargeTurbine;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;
import static com.gtt.gttcore.common.data.GTTBlocks.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;

public class GTTMultiblockMachines {
    public static void init(){}

    public final static MultiblockMachineDefinition FISSION_REACTOR = REGISTRATE
            .multiblock("fission_reactor", WorkableElectricMultiblockMachine::new)
            .langValue("Fission Reactor")
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.fission_reactor")))
            .rotationState(RotationState.ALL)
            .recipeType(FISSION_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH)
            .appearanceBlock(CASING_LEAD_RADIATION_PROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXXXXXXXXX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "GWWWWWWWWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "GW##CC##WG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "GW#CPPC#WG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "GWCPPPPCWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "G#CPPPPC#G", "GWCPPPPCWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "G##CPPC##G", "GW#CPPC#WG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "G###CC###G", "GW##CC##WG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "G########G", "GWWWWWWWWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "XXXXGGGGGX", "XXSXGGGGGX", "XXXXGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XXXXXXXXXX")
                    .where('S', controller(blocks(definition.get())))
                    .where('X', blocks(CASING_LEAD_RADIATION_PROOF.get()).setMinGlobalLimited(25)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('C', blocks(CASING_LOW_NEUTRON_ABSORPTION.get()))
                    .where('P', blocks(CASING_ZIRCONIUM_PIPE.get()))
                    .where('W', blocks(Blocks.WATER))
                    .where('G', blocks(CASING_LEAD_GLASS.get()))
                    .where('#', any())
                    .build())
            .workableCasingRenderer(GTTCore.id("block/machine_casing_radiation_proof"),
                    GTCEu.id("block/multiblock/fusion_reactor"))
            .register();
    public final static MultiblockMachineDefinition GREENHOUSE = REGISTRATE
            .multiblock("greenhouse", WorkableElectricMultiblockMachine::new)
            .langValue("greenhouse")
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.greenhouse")))
            .rotationState(RotationState.ALL)
            .recipeType(GREENHOUSE_RECIPES)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAA", "AAAAAAA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "B     B", "AAAAAAA")
                    .aisle("AAAAAAA", "AAA~AAA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "AAAAAAA")
                    .where("~", controller(blocks(definition.get())))
                    .where("B", blocks(Blocks.GLASS))
                    .where("C", blocks(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK))
                    .where("A", blocks(CASING_STEEL_SOLID.get()).setMinGlobalLimited(100))
                    .where(" ", any())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id(("block/multiblock/gcym/large_assembler")))
            .register();
    public final static MultiblockMachineDefinition SUPERCRITICAL_STEAM_TURBINE = registerLargeTurbine("steam_large_turbine",
            LuV,
            SUPERCRITICAL_STEAM_TURBINE_FUELS,
            CASING_NAQUADAH_ALLOY_TURBINE, CASING_TUNGSTENSTEEL_GEARBOX,
            GTTCore.id("block/machine_casing_turbine_naquadah_alloy"),
            GTCEu.id("block/multiblock/generator/large_steam_turbine"),
            false);
    public final static MultiblockMachineDefinition PLASMA_HEAT_EXCHANGER = REGISTRATE
            .multiblock("plasma_heat_exchanger", WorkableElectricMultiblockMachine::new)
            .langValue("plasma_heat_exchanger")
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.plasma_heat_exchanger")))
            .rotationState(RotationState.ALL)
            .recipeType(PLASMA_HEAT_EXCHANGER_RECIPES)
            .recipeModifiers(GTRecipeModifiers.OC_PERFECT_SUBTICK)
            .appearanceBlock(CASING_TUNGSTENSTEEL_ROBUST)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAABAAA","AAAAAAA","AAABAAA")
                    .aisle("AAAAAAA","ACCCCCA","AAAAAAA")
                    .aisle("AAABAAA","AAA~AAA","AAABAAA")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:robust_machine_casing"))).or(autoAbilities(definition.getRecipeTypes())))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:tungsten_steel_frame"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:tungstensteel_pipe_casing"))))
                    .where(" ", any())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_tungstensteel_robust"),
                    GTCEu.id(("block/multiblock/generator/large_steam_turbine")))
            .register();
    public static MultiblockMachineDefinition registerLargeTurbine(String name, int tier, GTRecipeType recipeType,
                                                                   Supplier<? extends Block> casing,
                                                                   Supplier<? extends Block> gear,
                                                                   ResourceLocation casingTexture,
                                                                   ResourceLocation overlayModel,
                                                                   boolean needsMuffler) {
        return REGISTRATE.multiblock(name, holder -> new LargeTurbineMachine(holder, tier))
                .rotationState(RotationState.ALL)
                .recipeType(recipeType)
                .generator(true)
                .recipeModifier(LargeTurbineMachine::recipeModifier, true)
                .appearanceBlock(casing)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("CCCC", "CHHC", "CCCC")
                        .aisle("CHHC", "RGGR", "CHHC")
                        .aisle("CCCC", "CSHC", "CCCC")
                        .where('S', controller(blocks(definition.getBlock())))
                        .where('G', blocks(gear.get()))
                        .where('C', blocks(casing.get()))
                        .where('R',
                                new TraceabilityPredicate(
                                        new SimplePredicate(
                                                state -> MetaMachine.getMachine(state.getWorld(),
                                                        state.getPos()) instanceof IRotorHolderMachine rotorHolder &&
                                                        state.getWorld()
                                                                .getBlockState(state.getPos()
                                                                        .relative(rotorHolder.self().getFrontFacing()))
                                                                .isAir(),
                                                () -> PartAbility.ROTOR_HOLDER.getAllBlocks().stream()
                                                        .map(BlockInfo::fromBlock).toArray(BlockInfo[]::new)))
                                        .addTooltips(Component.translatable("gtceu.multiblock.pattern.clear_amount_3"))
                                        .addTooltips(Component.translatable("gtceu.multiblock.pattern.error.limited.1",
                                                VN[tier]))
                                        .setExactLimit(1)
                                        .or(abilities(PartAbility.OUTPUT_ENERGY)).setExactLimit(1))
                        .where('H', blocks(casing.get())
                                .or(autoAbilities(definition.getRecipeTypes(), false, false, true, true, true, true))
                                .or(autoAbilities(true, needsMuffler, false)))
                        .build())
                .recoveryItems(
                        () -> new ItemLike[] {
                                GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get() })
                .workableCasingRenderer(casingTexture, overlayModel)
                .tooltips(
                        Component.translatable("gtceu.universal.tooltip.base_production_eut", V[tier] * 2),
                        Component.translatable("gtceu.multiblock.turbine.efficiency_tooltip", VNF[tier]))
                .register();
    }
}
