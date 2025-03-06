package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IRotorHolderMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.FusionReactorRenderer;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeTurbineMachine;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.machine.multiblock.HugeTurbineMachine;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.machine.multiblock.PartAbility.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.api.pattern.util.RelativeDirection.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.FLUID_EXPORT_HATCH;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.defaultEnvironmentRequirement;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.registerTieredMultis;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toRomanNumeral;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;
import static com.gtt.gttcore.common.data.GTTBlocks.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;


public class GTTMultiblockMachines {
    public static void init(){}

    public final static MultiblockMachineDefinition FISSION_REACTOR = REGISTRATE
            .multiblock("fission_reactor", WorkableElectricMultiblockMachine::new)
            .langValue("Fission Reactor")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.fission_reactor")))
            .rotationState(RotationState.ALL)
            .recipeType(FISSION_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH)
            .appearanceBlock(CASING_LEAD_RADIATION_PROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXXXXXXXXX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "GWWWWWWWWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "GW  CC  WG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "GW CPPC WG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "GWCPPPPCWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "GWCPPPPCWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "GW CPPC WG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "GW  CC  WG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "G        G", "GWWWWWWWWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "XXXXGGGGGX", "XX~XGGGGGX", "XXXXGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XGGGGGGGGX", "XXXXXXXXXX")
                    .where('~', controller(blocks(definition.get())))
                    .where('X', blocks(CASING_LEAD_RADIATION_PROOF.get()).setMinGlobalLimited(25)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('C', blocks(CASING_LOW_NEUTRON_ABSORPTION.get()))
                    .where('P', blocks(CASING_ZIRCONIUM_PIPE.get()))
                    .where('W', blocks(Blocks.WATER))
                    .where('G', blocks(CASING_LEAD_GLASS.get()))
                    .where(' ', any())
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
    public final static MultiblockMachineDefinition LASER_FREEZER = REGISTRATE
            .multiblock("laser_freezer", WorkableElectricMultiblockMachine::new)
            .langValue("laser_freezer")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.laser_freezer")))
            .rotationState(RotationState.ALL)
            .recipeType(VACUUM_RECIPES)
            .recipeModifiers((m, r) -> ModifierFunction.builder().modifyAllContents(ContentModifier.multiplier(256)).eutMultiplier(256).parallels(256).build(), GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(CASING_ALUMINIUM_FROSTPROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("                                             ","                                             ","                                             ","                                             ","                                             ","                     AAA                     ","                    AAAAA                    ","                    AAAAA                    ","                    AAAAA                    ","                     AAA                     ","                                             ","                                             ","                                             ","                                             ","                                             ")
                    .aisle("                                             ","                                             ","                                             ","                    AAAAA                    ","                   AAAAAAA                   ","                  AAA###AAA                  ","                  AA#####AA                  ","                  AA#####AA                  ","                  AA#####AA                  ","                  AAA###AAA                  ","                   AAAAAAA                   ","                    AAAAA                    ","                                             ","                                             ","                                             ")
                    .aisle("                                             ","                                             ","                    AAAAA                    ","                   A#####A                   ","                  A#######A                  ","                 A#########A                 ","                 A#########A                 ","                 A#########A                 ","                 A#########A                 ","                 A#########A                 ","                  A#######A                  ","                   A#####A                   ","                    AAAAA                    ","                                             ","                                             ")
                    .aisle("                                             ","                    AAAAA                    ","                   A#####A                   ","                  A#######A                  ","                 A#########A                 ","                A###########A                ","        C       A###########A       C        ","        C       A###########A       C        ","        C       A###########A       C        ","                A###########A                ","                 A#########A                 ","                  A#######A                  ","                   A#####A                   ","                    AAAAA                    ","                                             ")
                    .aisle("                                             ","                   AAAAAAA                   ","                  A#######A                  ","                 A#########A                 ","                A###########A                ","        C       A###########A       C        "," DC     C       A###########A       C     CD "," DC     C       A###########A       C     CD "," DC     C       A###########A       C     CD ","        C       A###########A       C        ","                A###########A                ","                 A#########A                 ","                  A#######A                  ","                   AAAAAAA                   ","                                             ")
                    .aisle("                     AAA                     ","                  AAA###AAA                  ","                 A#########A                 ","                A###########A                ","        C       A###########A       C        "," DC     C  C    A###########A    C  C     CD ","EDFCF   C  C  CA#############AC  C  C   FCFDE","EDFCF  FCF C  CA#############AC  C FCF  FCFDE","EDFCF   C  C  CA#############AC  C  C   FCFDE"," DC     C  C    A###########A    C  C     CD ","        C       A###########A       C        ","                A###########A                ","                 A#########A                 ","                  AAA###AAA                  ","                     AAA                     ")
                    .aisle("                    AAAAA                    ","                  AA#####AA                  ","                 A#########A                 ","        C       A###########A       C        "," DC     C       A###########A       C     CD ","EDFCF   C  C  CA#############AC  C  C   FCFDE","EDGFFC FCF G CCA#############ACC G FCF CFFGDE","EDGFFC FCF G CGA#############AGC G FCF CFFGDE","EDGFFC FCF G CCA#############ACC G FCF CFFGDE","EDFCF   C  C  CA#############AC  C  C   FCFDE"," DC     C       A###########A       C     CD ","        C       A###########A       C        ","                 A#########A                 ","                  AA#####AA                  ","                    AAAAA                    ")
                    .aisle("                    AAAAA                    ","                  AA#####AA                  ","                 A#########A                 ","        C       A###########A       C        "," DC     C       A###########A       C     CD ","EDFCF  FCF C  CA#############AC  C FCF  FCFDE","EDGFFC FCF G CGA#############AGC G FCF CFFGDE","EDGGG  GGG G GGG#############GGG G GGG  GGGDE","EDGFFC FCF G CGA#############AGC G FCF CFFGDE","EDFCF  FCF C  CA#############AC  C FCF  FCFDE"," DC     C       A###########A       C     CD ","        C       A###########A       C        ","                 A#########A                 ","                  AA#####AA                  ","                    AAAAA                    ")
                    .aisle("                    AAAAA                    ","                  AA#####AA                  ","                 A#########A                 ","        C       A###########A       C        "," DC     C       A###########A       C     CD ","EDFCF   C  C  CA#############AC  C  C   FCFDE","EDGFFC FCF G CCA#############ACC G FCF CFFGDE","EDGFFC FCF G CGA#############AGC G FCF CFFGDE","EDGFFC FCF G CCA#############ACC G FCF CFFGDE","EDFCF   C  C  CA#############AC  C  C   FCFDE"," DC     C       A###########A       C     CD ","        C       A###########A       C        ","                 A#########A                 ","                  AA#####AA                  ","                    AAAAA                    ")
                    .aisle("                     AAA                     ","                  AAA###AAA                  ","                 A#########A                 ","                A###########A                ","        C       A###########A       C        "," DC     C  C    A###########A    C  C     CD ","EDFCF   C  C  CA#############AC  C  C   FCFDE","EDFCF  FCF C  CA#############AC  C FCF  FCFDE","EDFCF   C  C  CA#############AC  C  C   FCFDE"," DC     C  C    A###########A    C  C     CD ","        C       A###########A       C        ","                A###########A                ","                 A#########A                 ","                  AAA###AAA                  ","                     AAA                     ")
                    .aisle("                                             ","                   AAAAAAA                   ","                  A#######A                  ","                 A#########A                 ","                A###########A                ","        C       A###########A       C        "," DC     C       A###########A       C     CD "," DC     C       A###########A       C     CD "," DC     C       A###########A       C     CD ","        C       A###########A       C        ","                A###########A                ","                 A#########A                 ","                  A#######A                  ","                   AAAAAAA                   ","                                             ")
                    .aisle("                                             ","                    AAAAA                    ","                   A#####A                   ","                  A#######A                  ","                 A#########A                 ","                A###########A                ","        C       A###########A       C        ","        C       A###########A       C        ","        C       A###########A       C        ","                A###########A                ","                 A#########A                 ","                  A#######A                  ","                   A#####A                   ","                    AAAAA                    ","                                             ")
                    .aisle("                                             ","                                             ","                    AAAAA                    ","                   A#####A                   ","                  A#######A                  ","                 A#########A                 ","                 A#########A                 ","                 A#########A                 ","                 A#########A                 ","                 A#########A                 ","                  A#######A                  ","                   A#####A                   ","                    AAAAA                    ","                                             ","                                             ")
                    .aisle("                                             ","                                             ","                                             ","                    AAAAA                    ","                   AAAAAAA                   ","                  AAA###AAA                  ","                  AA#####AA                  ","                  AA#####AA                  ","                  AA#####AA                  ","                  AAA###AAA                  ","                   AAAAAAA                   ","                    AAAAA                    ","                                             ","                                             ","                                             ")
                    .aisle("                                             ","                                             ","                                             ","                                             ","                                             ","                     AAA                     ","                    AAAAA                    ","                    AA~AA                    ","                    AAAAA                    ","                     AAA                     ","                                             ","                                             ","                                             ","                                             ","                                             ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:frostproof_machine_casing"))).setMinGlobalLimited(490)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true))
                            .or(abilities(INPUT_LASER)))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:laser_safe_engraving_casing"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:heat_vent"))))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:electrolytic_cell"))))
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:molybdenum_disilicide_coil_block"))))
                    .where("G", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:fusion_glass"))))
                    .where(" ", any())
                    .where("#", air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_frost_proof"),
                    GTCEu.id("block/multiblock/gcym/mega_vacuum_freezer"))
            .register();

    public final static MultiblockMachineDefinition ENGRAVING_PLANT = REGISTRATE
            .multiblock("engraving_plant", WorkableElectricMultiblockMachine::new)
            .langValue("engraving_plant")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.engraving_plant")))
            .rotationState(RotationState.ALL)
            .recipeType(LASER_ENGRAVER_RECIPES)
            .recipeModifiers((m, r) -> ModifierFunction.builder().modifyAllContents(ContentModifier.multiplier(256)).eutMultiplier(256).parallels(256).build(), GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(CASING_LASER_SAFE_ENGRAVING)
            .pattern(definition -> FactoryBlockPattern.start(LEFT, UP, BACK)
                    .aisle("                 ","                 ","     AAAAAAA     ","    AAAAAAAAA    ","   AAAAAAAAAAA   ","  AAAABBBBBAAAA  ","  AAABBBBBBBAAA  ","  AAABBBBBBBAAA  ","  AAABBB~BBBAAA  ","  AAABBBBBBBAAA  ","  AAABBBBBBBAAA  ","  AAAABBBBBAAAA  ","   AAAAAAAAAAA   ","    AAAAAAAAA    ","     AAAAAAA     ","                 ","                 ")
                    .aisle("                 ","     BBBBBBB     ","   BBBBBBBBBBB   ","  BBBB#####BBBB  ","  BBB       BBB  "," BBB#########BBB "," BB###########BB "," BB           BB "," BB###########BB "," BB###########BB "," BB###########BB "," BBB#########BBB ","  BBB#######BBB  ","  BBBB#####BBBB  ","   BBBBBBBBBBB   ","     BBBBBBB     ","                 ")
                    .aisle("                 ","                 ","     BBBBBBB     ","    BBBBBBBBB    ","   BBBBBBBBBBB   ","  BBBBBBBBBBBBB  ","  BBBBBBBBBBBBB  ","  BBBBBBBBBBBBB  ","  BBBBBBCBBBBBB  ","  BBBBBBBBBBBBB  ","  BBBBBBBBBBBBB  ","  BBBBBBBBBBBBB  ","   BBBBBBBBBBB   ","    BBBBBBBBB    ","     BBBBBBB     ","                 ","                 ")
                    .aisle("BB             BB","BB             BB","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","BB             BB","BB             BB")
                    .aisle("BB             BB","BDB           BDB"," BB           BB ","                 ","      BBBBB      ","     B     B     ","    B       B    ","    B       B    ","    B       B    ","    B       B    ","    B       B    ","     B     B     ","      BBBBB      ","                 "," BB           BB ","BDB           BDB","BB             BB")
                    .aisle("                 "," BB           BB "," BDB         BDB ","  BB BBBBBBB BB  ","    BBDDDDDBB    ","   BBDBBBBBDBB   ","   BDB     BDB   ","   BDB     BDB   ","   BDB     BDB   ","   BDB     BDB   ","   BDB     BDB   ","   BBDBBBBBDBB   ","    BBDDDDDBB    ","  BB BBBBBBB BB  "," BDB         BDB "," BB           BB ","                 ")
                    .aisle("                 ","                 ","  BB         BB  ","  BDD       DDB  ","   DC BBBBB CD   ","     BBDDDBB     ","    BBDCCCDBB    ","    BDCCCCCDB    ","    BDCCCCCDB    ","    BDCCCCCDB    ","    BBDCCCDBB    ","     BBDDDBB     ","   DC BBBBB CD   ","  BDD       DDB  ","  BB         BB  ","                 ","                 ")
                    .aisle("                 ","                 ","                 ","   DC       CD   ","   CC       CC   ","       BBB       ","      B   B      ","     B     B     ","     B     B     ","     B     B     ","      B   B      ","       BBB       ","   CC       CC   ","   DC       CD   ","                 ","                 ","                 ")
                    .aisle("                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ")
                    .aisle("                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ")
                    .aisle("                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","        A        ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ")
                    .aisle("                 ","                 ","                 ","                 ","                 ","                 ","                 ","        A        ","       AAA       ","        A        ","                 ","                 ","                 ","                 ","                 ","                 ","                 ")
                    .aisle("                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","        A        ","                 ","                 ","                 ","                 ","                 ","                 ","                 ","                 ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:electrolytic_cell"))))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:laser_safe_engraving_casing"))).setMinGlobalLimited(400)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true))
                            .or(abilities(INPUT_LASER)))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:fusion_glass"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:molybdenum_disilicide_coil_block"))))
                    .where(" ", any())
                    .where("#", air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/gcym/laser_safe_engraving_casing"),
                    GTCEu.id("block/multiblock/gcym/mega_vacuum_freezer"))
            .register();

    public final static MultiblockMachineDefinition LARGE_ROCK_CRUSHER = REGISTRATE
            .multiblock("large_rock_crusher", WorkableElectricMultiblockMachine::new)
            .langValue("large_rock_crusher")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.large_rock_crusher")))
            .rotationState(RotationState.ALL)
            .recipeType(LARGE_ROCK_BREAKER_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(CASING_SECURE_MACERATION)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("      AAAAA      ","      AAAAA      ","      AAAAA      ","      AAAAA      ")
                    .aisle("     AAAAAAA     ","     AABBBAA     ","     AABBBAA     ","     AA   AA     ")
                    .aisle("    AAAAAAAAA    ","    AABBBBBAA    ","    AABBBBBAA    ","    AA     AA    ")
                    .aisle("CCC AAAAAAAAA CCC","CDC ABBBBBBBA CDC","CDC ABBBBBBBA CDC","CCC A       A CCC")
                    .aisle("CCC AAAAAAAAA CCC","D CEABBBBBBBAEC D","D CEABBBBBBBAEC D","CCC A       A CCC")
                    .aisle("CCC AAAAAAAAA CCC","CDC ABBBBBBBA CDC","CDC ABBBBBBBA CDC","CCC A       A CCC")
                    .aisle("    AAAAAAAAA    ","    AABBBBBAA    ","    AABBBBBAA    ","    AA     AA    ")
                    .aisle("     AAAAAAA     ","     AABBBAA     ","     AABBBAA     ","     AA   AA     ")
                    .aisle("      AAAAA      ","      AA~AA      ","      AAAAA      ","      AAAAA      ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:secure_maceration_casing"))).setMinGlobalLimited(100).or(autoAbilities(definition.getRecipeTypes())).or(autoAbilities(true, false, true)))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:crushing_wheels"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:clean_machine_casing"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:tempered_glass"))))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:tungstensteel_pipe_casing"))))
                    .where(" ", any())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/gcym/secure_maceration_casing"),
                    GTCEu.id("block/machines/rock_crusher"))
            .register();
    public final static MultiblockMachineDefinition PARTICLE_COLLIDER = REGISTRATE
            .multiblock("particle_collider", WorkableElectricMultiblockMachine::new)
            .langValue("particle_collider")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.particle_collider")))
            .rotationState(RotationState.ALL)
            .recipeType(PARTICLE_COLLIDER_RECIPES)
            .recipeModifiers((m, r) -> ModifierFunction.builder().modifyAllContents(ContentModifier.multiplier(256)).eutMultiplier(256).parallels(256).build(), GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(CASING_ATOMIC)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("                         ","         AAAAAAA         ","       AAAAAAAAAA        ","         AAAAAAA         ","                         ")
                    .aisle("         AAAAAAA         ","       AA       AA       ","      AA         AA      ","       AA       AA       ","         AAAAAAA         ")
                    .aisle("       AAAAAAAAAAA       ","     AA           AA     ","    AA             AA    ","     AA           AA     ","       AAAAAAAAAAA       ")
                    .aisle("     AAAAAAAAAAAAAAA     ","    A               A    ","   A                 A   ","    A               A    ","     AAAAAAAAAAAAAAA     ")
                    .aisle("    AAAAAA     AAAAAA    ","   A      AAAAA      A   ","  A      AAAAAAA      A  ","   A      AAAAA      A   ","    AAAAAA     AAAAAA    ")
                    .aisle("   AAAAA         AAAAA   ","  A     AA     AA     A  ","  A    AA       AA    A  ","  A     AA     AA     A  ","   AAAAA         AAAAA   ")
                    .aisle("   AAAA           AAAA   ","  A    A         A    A  "," A    A           A    A ","  A    A         A    A  ","   AAAA           AAAA   ")
                    .aisle("  AAAA             AAAA  "," A    A           A    A "," A   A             A   A "," A    A           A    A ","  AAAA             AAAA  ")
                    .aisle("  AAA               AAA  "," A   A             A   A ","A    A             A    A"," A   A             A   A ","  AAA               AAA  ")
                    .aisle(" AAAA               AAAA ","A    A             A    A","A   A               A   A","A    A             A    A"," AAAA               AAAA ")
                    .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                    .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                    .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                    .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                    .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                    .aisle(" AAAA               AAAA ","A    A             A    A","A   A               A   A","A    A             A    A"," AAAA               AAAA ")
                    .aisle("  AAA               AAA  "," A   A             A   A ","A    A             A    A"," A   A             A   A ","  AAA               AAA  ")
                    .aisle("  AAAA             AAAA  "," A    A           A    A "," A   A             A   A "," A    A           A    A ","  AAAA             AAAA  ")
                    .aisle("   AAAA           AAAA   ","  A    A         A    A  "," A    A           A    A ","  A    A         A    A  ","   AAAA           AAAA   ")
                    .aisle("   AAAAA         AAAAA   ","  A     AA     AA     A  ","  A    AA       AA    A  ","  A     AA     AA     A  ","   AAAAA         AAAAA   ")
                    .aisle("    AAAAAA     AAAAAA    ","   A      AAAAA      A   ","  A      AAAAAAA      A  ","   A      AAAAA      A   ","    AAAAAA     AAAAAA    ")
                    .aisle("     AAAAAAAAAAAAAAA     ","    A               A    ","   A                 A   ","    A               A    ","     AAAAAAAAAAAAAAA     ")
                    .aisle("       AAAAAAAAAAA       ","     AA           AA     ","    AA             AA    ","     AA           AA     ","       AAAAAAAAAAA       ")
                    .aisle("         AAAAAAA         ","       AA       AA       ","      AA         AA      ","       AA       AA       ","         AAAAAAA         ")
                    .aisle("                         ","         AAAAAAA         ","        AAAA~AAAA        ","         AAAAAAA         ","                         ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:atomic_casing"))).or(autoAbilities(definition.getRecipeTypes())).setMinGlobalLimited(700).or(autoAbilities(true, false, true)))
                    .where(" ", any())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/gcym/atomic_casing"),
                    GTCEu.id("block/multiblock/fusion_reactor"))
            .register();

    public final static MultiblockMachineDefinition[] LARGE_FUSION_REACTOR = registerTieredMultis("large_fusion_reactor",
            FusionReactorMachine::new, (tier, builder) -> builder
                    .rotationState(RotationState.ALL)
                    .langValue("Large Fusion Reactor Computer MK %s".formatted(toRomanNumeral(tier - 5)))
                    .recipeType(GTRecipeTypes.FUSION_RECIPES)
                    .recipeModifiers(GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
                            FusionReactorMachine::recipeModifier)
                    .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
                    .tooltips(
                            Component.translatable("gtceu.machine.fusion_reactor.capacity",
                                    FusionReactorMachine.calculateEnergyStorageFactor(tier, 16) / 1000000L),
                            Component.translatable("gtceu.machine.fusion_reactor.overclocking"),
                            Component.translatable("gtceu.multiblock.%s_fusion_reactor.description"
                                    .formatted(VN[tier].toLowerCase(Locale.ROOT))))
                    .appearanceBlock(() -> FusionReactorMachine.getCasingState(tier))
                    .pattern((definition) -> {
                        var casing = blocks(FusionReactorMachine.getCasingState(tier));
                        return FactoryBlockPattern.start()
                                .aisle("                         ","         AAAAAAA         ","       AAAAAAAAAA        ","         AAAAAAA         ","                         ")
                                .aisle("         AAAAAAA         ","       AA       AA       ","      AA         AA      ","       AA       AA       ","         AAAAAAA         ")
                                .aisle("       AAAAAAAAAAA       ","     AA           AA     ","    AA             AA    ","     AA           AA     ","       AAAAAAAAAAA       ")
                                .aisle("     AAAAAAAAAAAAAAA     ","    A               A    ","   A                 A   ","    A               A    ","     AAAAAAAAAAAAAAA     ")
                                .aisle("    AAAAAA     AAAAAA    ","   A      AAAAA      A   ","  A      AAAAAAA      A  ","   A      AAAAA      A   ","    AAAAAA     AAAAAA    ")
                                .aisle("   AAAAA         AAAAA   ","  A     AA     AA     A  ","  A    AA       AA    A  ","  A     AA     AA     A  ","   AAAAA         AAAAA   ")
                                .aisle("   AAAA           AAAA   ","  A    A         A    A  "," A    A           A    A ","  A    A         A    A  ","   AAAA           AAAA   ")
                                .aisle("  AAAA             AAAA  "," A    A           A    A "," A   A             A   A "," A    A           A    A ","  AAAA             AAAA  ")
                                .aisle("  AAA               AAA  "," A   A             A   A ","A    A             A    A"," A   A             A   A ","  AAA               AAA  ")
                                .aisle(" AAAA               AAAA ","A    A             A    A","A   A               A   A","A    A             A    A"," AAAA               AAAA ")
                                .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                                .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                                .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                                .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                                .aisle(" AAA                 AAA ","A   A               A   A","A   A               A   A","A   A               A   A"," AAA                 AAA ")
                                .aisle(" AAAA               AAAA ","A    A             A    A","A   A               A   A","A    A             A    A"," AAAA               AAAA ")
                                .aisle("  AAA               AAA  "," A   A             A   A ","A    A             A    A"," A   A             A   A ","  AAA               AAA  ")
                                .aisle("  AAAA             AAAA  "," A    A           A    A "," A   A             A   A "," A    A           A    A ","  AAAA             AAAA  ")
                                .aisle("   AAAA           AAAA   ","  A    A         A    A  "," A    A           A    A ","  A    A         A    A  ","   AAAA           AAAA   ")
                                .aisle("   AAAAA         AAAAA   ","  A     AA     AA     A  ","  A    AA       AA    A  ","  A     AA     AA     A  ","   AAAAA         AAAAA   ")
                                .aisle("    AAAAAA     AAAAAA    ","   A      AAAAA      A   ","  A      AAAAAAA      A  ","   A      AAAAA      A   ","    AAAAAA     AAAAAA    ")
                                .aisle("     AAAAAAAAAAAAAAA     ","    A               A    ","   A                 A   ","    A               A    ","     AAAAAAAAAAAAAAA     ")
                                .aisle("       AAAAAAAAAAA       ","     AA           AA     ","    AA             AA    ","     AA           AA     ","       AAAAAAAAAAA       ")
                                .aisle("         AAAAAAA         ","       AA       AA       ","      AA         AA      ","       AA       AA       ","         AAAAAAA         ")
                                .aisle("                         ","         AAAAAAA         ","        AAAA~AAAA        ","         AAAAAAA         ","                         ")
                                .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                                .where("A", casing.setMinGlobalLimited(700).or(autoAbilities(definition.getRecipeTypes())).or(autoAbilities(true, false, true)))
                                .where(" ", any())
                                .build();
                    })
                    .renderer(() -> new FusionReactorRenderer(FusionReactorMachine.getCasingType(tier).getTexture(),
                            GTCEu.id("block/multiblock/fusion_reactor")))
                    .hasTESR(true)
                    .register(),
            LuV, ZPM, UV);
    public final static MultiblockMachineDefinition LARGE_SUPERCRITICAL_STEAM_TURBINE = registerLargeTurbine("supercritical_steam_large_turbine",
            LuV,
            SUPERCRITICAL_STEAM_TURBINE_FUELS,
            CASING_NAQUADAH_ALLOY_TURBINE, CASING_TUNGSTENSTEEL_GEARBOX,
            GTTCore.id("block/machine_casing_turbine_naquadah_alloy"),
            GTCEu.id("block/multiblock/generator/large_steam_turbine"),
            false);
    public final static MultiblockMachineDefinition HUGE_SUPERCRITICAL_STEAM_TURBINE = registerHugeTurbine("supercritical_steam_huge_turbine",
            ZPM,
            SUPERCRITICAL_STEAM_TURBINE_FUELS,
            CASING_NAQUADAH_ALLOY_TURBINE, CASING_TUNGSTENSTEEL_GEARBOX,
            GTTCore.id("block/machine_casing_turbine_naquadah_alloy"),
            GTCEu.id("block/multiblock/generator/large_steam_turbine"),
            false);
    public final static MultiblockMachineDefinition HUGE_STEAM_TURBINE = registerHugeTurbine("steam_huge_turbine",
            EV,
            STEAM_TURBINE_FUELS,
            CASING_STEEL_TURBINE, CASING_STEEL_GEARBOX,
            GTCEu.id("block/casings/mechanic/machine_casing_turbine_steel"),
            GTCEu.id("block/multiblock/generator/large_steam_turbine"),
            false);
    public final static MultiblockMachineDefinition HUGE_GAS_TURBINE = registerHugeTurbine("gas_huge_turbine",
            IV,
            GAS_TURBINE_FUELS,
            CASING_STAINLESS_TURBINE, CASING_STAINLESS_STEEL_GEARBOX,
            GTCEu.id("block/casings/mechanic/machine_casing_turbine_stainless_steel"),
            GTCEu.id("block/multiblock/generator/large_steam_turbine"),
            true);
    public final static MultiblockMachineDefinition HUGE_PLASMA_TURBINE = registerHugeTurbine("plasma_huge_turbine",
            LuV,
            PLASMA_GENERATOR_FUELS,
            CASING_TUNGSTENSTEEL_TURBINE, CASING_TUNGSTENSTEEL_GEARBOX,
            GTCEu.id("block/casings/mechanic/machine_casing_turbine_tungstensteel"),
            GTCEu.id("block/multiblock/generator/large_plasma_turbine"),
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
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:robust_machine_casing"))).or(autoAbilities(definition.getRecipeTypes(), false, false, false, false, true, true)).or(autoAbilities(true, false, false)))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:tungsten_steel_frame"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:tungstensteel_pipe_casing"))))
                    .where(" ", any())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                    GTCEu.id(("block/multiblock/generator/large_steam_turbine")))
            .register();
    public final static MultiblockMachineDefinition HUGE_CHEMICAL_REACTOR = REGISTRATE
            .multiblock("huge_chemical_reactor", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_PERFECT_SUBTICK)
            .appearanceBlock(CASING_PTFE_INERT)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle(" AAA ","AAAAA","AAAAA","AAAAA"," AAA ")
                    .aisle("AAAAA","AB BA","A   A","AB BA","AAAAA")
                    .aisle("AAAAA","A   A","A   A","A   A","AAAAA")
                    .aisle("AAAAA","AB BA","A   A","AB BA","AAAAA")
                    .aisle(" AAA ","AAAAA","AA~AA","AAAAA"," AAA ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:inert_machine_casing"))).or(autoAbilities(definition.getRecipeTypes())).or(autoAbilities(true, false, true)))
                    .where("B", Predicates.heatingCoils())
                    .where(" ", any())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();
    public final static MultiblockMachineDefinition CHEMICAL_PLANT = REGISTRATE
            .multiblock("chemical_plant", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CHEMICAL_PLANT_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_PERFECT_SUBTICK)
            .appearanceBlock(CASING_PTFE_INERT)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAAAAAA","           ","           ","           ","           ","           ","           ","           ","AAAAAAAAAAA")
                    .aisle("AAAAAAAAAAA"," AAAAAAAAA "," ABBBBBBBA "," ABBBBBBBA "," ABBBBBBBA "," ABBBBBBBA "," ABBBBBBBA "," AAAAAAAAA ","AAAAAAAAAAA")
                    .aisle("AAAAAAAAAAA"," ACCCCCCCA "," BCCCCCCCB "," BC#####CB "," BC#####CB "," BC#####CB "," BCCCCCCCB "," ACCCCCCCA ","AAAAAAAAAAA")
                    .aisle("AAACCCCCAAA"," ACCCCCCCA "," BC#####CB "," B#######B "," B#######B "," B#######B "," BC#####CB "," ACCCCCCCA ","AAACCCCCAAA")
                    .aisle("AAACCCCCAAA"," ACCCCCCCA "," BC#####CB "," B#######B "," B#######B "," B#######B "," BC#####CB "," ACCCCCCCA ","AAACCCCCAAA")
                    .aisle("AAACCCCCAAA"," ACCCCCCCA "," BC#####CB "," B#######B "," B#######B "," B#######B "," BC#####CB "," ACCCCCCCA ","AAACCCCCAAA")
                    .aisle("AAACCCCCAAA"," ACCCCCCCA "," BC#####CB "," B#######B "," B#######B "," B#######B "," BC#####CB "," ACCCCCCCA ","AAACCCCCAAA")
                    .aisle("AAACCCCCAAA"," ACCCCCCCA "," BC#####CB "," B#######B "," B#######B "," B#######B "," BC#####CB "," ACCCCCCCA ","AAACCCCCAAA")
                    .aisle("AAAAAAAAAAA"," ACCCCCCCA "," BCCCCCCCB "," BC#####CB "," BC#####CB "," BC#####CB "," BCCCCCCCB "," ACCCCCCCA ","AAAAAAAAAAA")
                    .aisle("AAAAAAAAAAA"," AAAA~AAAA "," ABBBBBBBA "," ABBBBBBBA "," ABBBBBBBA "," ABBBBBBBA "," ABBBBBBBA "," AAAAAAAAA ","AAAAAAAAAAA")
                    .aisle("AAAAAAAAAAA","           ","           ","           ","           ","           ","           ","           ","AAAAAAAAAAA")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:inert_machine_casing"))).or(autoAbilities(definition.getRecipeTypes())).or(autoAbilities(true, false, true)))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:tempered_glass"))))
                    .where("C", Predicates.heatingCoils())
                    .where(" ", any())
                    .where("#", air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
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

    public static MultiblockMachineDefinition registerHugeTurbine(String name, int tier, GTRecipeType recipeType,
                                                                   Supplier<? extends Block> casing,
                                                                   Supplier<? extends Block> gear,
                                                                   ResourceLocation casingTexture,
                                                                   ResourceLocation overlayModel,
                                                                   boolean needsMuffler) {
        return REGISTRATE.multiblock(name, holder -> new HugeTurbineMachine(holder, tier))
                .rotationState(RotationState.ALL)
                .recipeType(recipeType)
                .generator(true)
                .recipeModifier(HugeTurbineMachine::recipeModifier, true)
                .appearanceBlock(casing)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("CCCCCCC", "CHHHHHC", "CHHHHHC", "CHHHHHC", "CCCCCCC")
                        .aisle("CHHHHHC", "HGGGGGH", "HGGGGGH", "HGGGGGH", "CHHHHHC")
                        .aisle("CHHHHHC", "HGGGGGH", "RGGGGGR", "HGGGGGH", "CHHHHHC")
                        .aisle("CHHHHHC", "HGGGGGH", "HGGGGGH", "HGGGGGH", "CHHHHHC")
                        .aisle("CCCCCCC", "CHHHHHC", "CHHSHHC", "CHHHHHC", "CCCCCCC")
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

    public static MultiblockMachineDefinition[] registerTieredMultis(String name,
                                                                     BiFunction<IMachineBlockEntity, Integer, MultiblockControllerMachine> factory,
                                                                     BiFunction<Integer, MultiblockMachineBuilder, MultiblockMachineDefinition> builder,
                                                                     int... tiers) {
        MultiblockMachineDefinition[] definitions = new MultiblockMachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = REGISTRATE
                    .multiblock(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }
}


