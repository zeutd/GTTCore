package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gtt.gttcore.GTTCore;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.any;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
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
                    Component.translatable("gttcore.fission_reactor")))
            .rotationState(RotationState.ALL)
            .recipeType(GREENHOUSE_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                    GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(CASING_STRESS_PROOF)
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
                    .where("A", blocks(CASING_STEEL_SOLID.get()))
                    .where(" ", any())
                    .build())
            .workableCasingRenderer(GTTCore.id("block/machine_casing_radiation_proof"),
                    GTCEu.id(("block/multiblock/gcym/large_assembler")))
            .register();
}
