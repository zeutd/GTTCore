package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IMiner;
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
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.data.machines.GTAEMachines;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.AssemblyLineMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeTurbineMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitiveBlastFurnaceMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.gtt.gttcore.common.machine.multiblock.*;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import com.mojang.realmsclient.dto.BackupList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.machine.multiblock.PartAbility.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.api.pattern.util.RelativeDirection.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.ITEM_IMPORT_BUS;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.OC_NON_PERFECT;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createWorkableCasingMachineModel;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toRomanNumeral;
import static com.gtt.gttcore.common.registry.GTTRegistration.REGISTRATE;
import static com.gtt.gttcore.common.data.GTTBlocks.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;


public class GTTMultiMachines {
    public static void init(){}
    public static final MultiblockMachineDefinition EVAPORATION_PLANT = REGISTRATE
            .multiblock("evaporation_plant", WorkableElectricMultiblockMachine::new)
            .langValue("Evaporation Tower")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTTRecipeTypes.EVAPORATION_RECIPES)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(CASING_STAINLESS_EVAPORATION)
            .pattern(definition -> FactoryBlockPattern.start(RIGHT, BACK, UP)
                    .aisle("FYF", "YYY", "FYF")
                    .aisle("YSY", "Y#Y", "YYY")
                    .aisle("XXX", "X#X", "XXX").setRepeatable(2, 5)
                    .aisle(" Z ", "ZZZ", " Z ")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('Y', blocks(CASING_STAINLESS_EVAPORATION.get())
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(1)))
                    .where('X', blocks(CASING_STAINLESS_EVAPORATION.get())
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS_1X).setMinLayerLimited(1)
                                    .setMaxLayerLimited(1)))
                    .where('Z', blocks(CASING_STAINLESS_EVAPORATION.get()))
                    .where('F', Predicates.frames(Aluminium))
                    .where('#', Predicates.air())
                    .where(' ', Predicates.any())
                    .build())
            .allowExtendedFacing(false)
            .partSorter(Comparator.comparingInt(a -> a.self().getPos().getY()))
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_stainless_evaporation"),
                    GTCEu.id("block/multiblock/evaporation_plant"))
            .register();
    public final static MultiblockMachineDefinition LARGE_GREENHOUSE = REGISTRATE.multiblock("large_greenhouse", WorkableElectricMultiblockMachine::new)
            .langValue("Large Greenhouse")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GREENHOUSE_RECIPES)
            .recipeModifiers(OC_NON_PERFECT_SUBTICK, GTRecipeModifiers.PARALLEL_HATCH)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start(BACK,UP,LEFT)
                    .aisle("AAAAAAAAAAAAAAAAAAA","AAAAAAAAAAAAAAAAAAA","ABBBBBABBBBBABBBBBA","ABBBBBABBBBBABBBBBA","ABBBBBABBBBBABBBBBA","ABBBBBABBBBBABBBBBA","AAAAAAAAAAAAAAAAAAA","                   ","                   ","                   ")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","B                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA","                   ","                   ")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","B                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA","                   ","                   ")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","B                 B","B                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA","                   ")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","B                 B","B                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA","                   ")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","A                 B","A                 B","A                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","A                 B","~                 B","A                 B","B                 B","B                 B","B                 B","B                 B","AAAAAAAAAAAAAAAAAAA")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","A                 B","A                 B","A                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","B                 B","B                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA","                   ")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","B                 B","B                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA","                   ")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","B                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA","                   ","                   ")
                    .aisle("AAAAAAAAAAAAAAAAAAA","ACCCCCCCCCCCCCCCCCA","B                 B","B                 B","B                 B","B                 B","B                 B","ABBBBBABBBBBABBBBBA","                   ","                   ")
                    .aisle("AAAAAAAAAAAAAAAAAAA","AAAAAAAAAAAAAAAAAAA","ABBBBBABBBBBABBBBBA","ABBBBBABBBBBABBBBBA","ABBBBBABBBBBABBBBBA","ABBBBBABBBBBABBBBBA","AAAAAAAAAAAAAAAAAAA","                   ","                   ","                   ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:clean_machine_casing")))
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:laminated_glass"))))
                    .where("C", blocks(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:industrial_steam_casing"))))
                    .where(" ", any())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    GTCEu.id("block/multiblock/assembly_line"))
            .register();
    public final static MultiblockMachineDefinition LASER_ENGRAVING_PLANT = REGISTRATE.multiblock("laser_engraving_plant", WorkableElectricMultiblockMachine::new)
            .langValue("Laser Engraving Plant")
            .recipeType(LASER_ENGRAVING_PLANT_RECIPES)
            .recipeModifiers(OC_PERFECT_SUBTICK, GTRecipeModifiers.PARALLEL_HATCH)
            .appearanceBlock(CASING_LASER_SAFE_ENGRAVING)
            .pattern(definition -> FactoryBlockPattern.start(LEFT, UP, BACK)
                    .aisle("                       ","                       ","                       ","                       ","                       ","                       ","                       ","                       ","                       ","          A~A          ","         AAAAA         ","         AAGAA         ","         AAAAA         ","          AAA          ","                       ","                       ","                       ","                       ","                       ","                       ","                       ","                       ","                       ")
                    .aisle("                       ","                       ","                       ","           B           ","           B           ","           B           ","           B           ","           B           ","           B           ","           B           ","          CCC          ","   BBBBBBBCBCBBBBBBB   ","          CCC          ","           B           ","           B           ","           B           ","           B           ","           B           ","           B           ","           B           ","                       ","                       ","                       ")
                    .aisle("                       ","                       ","          AAA          ","                       ","                       ","                       ","                       ","                       ","                       ","                       ","  A       CCC       A  ","  A       CBC       A  ","  A       CCC       A  ","                       ","                       ","                       ","                       ","                       ","                       ","                       ","          AAA          ","                       ","                       ")
                    .aisle("                       ","          AAA          ","          AAA          ","          CCC          ","                       ","                       ","                       ","                       ","                       ","                       "," AAC      AAA      CAA "," AAC      ABA      CAA "," AAC      AAA      CAA ","                       ","                       ","                       ","                       ","                       ","                       ","          CCC          ","          AAA          ","          AAA          ","                       ")
                    .aisle("          AAA          ","          AAA          ","          CCC          ","          CCC          ","          AAA          ","                       ","                       ","                       ","                       ","                       ","AACCA     CCC     ACCAA","AACCA     CBC     ACCAA","AACCA     CCC     ACCAA","                       ","                       ","                       ","                       ","                       ","          AAA          ","          CCC          ","          CCC          ","          AAA          ","          AAA          ")
                    .aisle("                       ","          CCC          ","          CCC          ","          AAA          ","          AAA          ","          CCC          ","                       ","                       ","                       ","           A           "," CCAAC    CAC    CAACC "," CCAAC   ACDCA   CAACC "," CCAAC    CAC    CAACC ","           A           ","                       ","                       ","                       ","          CCC          ","          AAA          ","          AAA          ","          CCC          ","          CCC          ","                       ")
                    .aisle("                       ","                       ","          AAA          ","          AAA          ","          CCC          ","          CCC          ","          AAA          ","           A           ","           A           ","                       ","  AACCA   AAA   ACCAA  ","  AACCAAA A A AAACCAA  ","  AACCA   AAA   ACCAA  ","                       ","           A           ","           A           ","          AAA          ","          CCC          ","          CCC          ","          AAA          ","          AAA          ","                       ","                       ")
                    .aisle("                       ","                       ","                       ","          CCC          ","          CCC          ","          ADA          ","          A A          ","                       ","                       ","                       ","   CCAA         AACC   ","   CCD           DCC   ","   CCAA         AACC   ","                       ","                       ","                       ","          A A          ","          ADA          ","          CCC          ","          CCC          ","                       ","                       ","                       ")
                    .aisle("                       ","                       ","                       ","                       ","          AAA          ","          A A          ","                       ","                       ","                       ","                       ","    AA           AA    ","    A             A    ","    AA           AA    ","                       ","                       ","                       ","                       ","          A A          ","          AAA          ","                       ","                       ","                       ","                       ")
                    .aisle("                       ","                       ","                       ","                       ","           E           ","                       ","                       ","                       ","                       ","                       ","                       ","    E             E    ","                       ","                       ","                       ","                       ","                       ","                       ","           E           ","                       ","                       ","                       ","                       ")
                    .aisle("                       ","                       ","                       ","                       ","           E           ","                       ","                       ","                       ","                       ","                       ","                       ","    E             E    ","                       ","                       ","                       ","                       ","                       ","                       ","           E           ","                       ","                       ","                       ","                       ")
                    .aisle("                       ","                       ","                       ","                       ","                       ","           E           ","                       ","                       ","                       ","                       ","                       ","     E           E     ","                       ","                       ","                       ","                       ","                       ","           E           ","                       ","                       ","                       ","                       ","                       ")
                    .aisle("                       ","                       ","                       ","                       ","                       ","                       ","           E           ","           E           ","                       ","                       ","                       ","      EE       EE      ","                       ","                       ","                       ","           E           ","           E           ","                       ","                       ","                       ","                       ","                       ","                       ")
                    .aisle("                       ","                       ","                       ","                       ","                       ","                       ","                       ","                       ","           E           ","           E           ","           E           ","        EEEFEEE        ","           E           ","           E           ","           E           ","                       ","                       ","                       ","                       ","                       ","                       ","                       ","                       ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:laser_safe_engraving_casing")))
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true))
                    )
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:electrolytic_cell"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:molybdenum_disilicide_coil_block"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:fusion_glass"))))
                    .where("E", Predicates.frames(Tritanium))
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:uhv_machine_casing"))))
                    .where("G", abilities(GTTPartAbility.IMPORT_HIGH_ENERGY_LASER))
                    .where(" ", any())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/laser_safe_engraving_casing"),
                    GTCEu.id("block/multiblock/assembly_line"))
            .register();

    public static final MultiblockMachineDefinition HIGH_ENERGY_LASER_PIPE = REGISTRATE
            .multiblock("high_energy_laser_pipe", HighEnergyLaserPipeMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(HIGH_ENERGY_LASER_PIPE_COOLANT)
            .appearanceBlock(CASING_ATOMIC)
            .pattern(definition -> FactoryBlockPattern.start(BACK, UP, RIGHT)
                    .aisle(" A ", "~IA", " A ")
                    .aisle(" A ", "APA", " A ").setRepeatable(1, 15)
                    .aisle(" A ", "AOA", " A ")
                    .where(" ", any())
                    .where('~', Predicates.controller(Predicates.blocks(definition.get())))
                    .where('A', Predicates.blocks(CASING_ATOMIC.get()).or(autoAbilities(definition.getRecipeTypes())))
                    .where('P', Predicates.blocks(FUSION_COIL.get()))
                    .where('I', Predicates.abilities(GTTPartAbility.IMPORT_HIGH_ENERGY_LASER))
                    .where('O', Predicates.abilities(GTTPartAbility.EXPORT_HIGH_ENERGY_LASER))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/atomic_casing"),
                    GTCEu.id("block/multiblock/assembly_line"))
            .register();
    public final static MultiblockMachineDefinition ROCKET_ASSEMBLER = REGISTRATE.multiblock("rocket_assembler", WorkableElectricMultiblockMachine::new)
            .langValue("Rocket Assembler")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(ROCKET_ASSEMBLER_RECIPES)
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start(LEFT, UP, BACK)
                    .aisle("A AAA A","A A~A A","AA   AA","A     A","A     A","A     A","A     A","ACACACA")
                    .aisle("AAAAAAA","  AAA  "," A   A ","       ","       ","       ","       ","AB B BA")
                    .aisle("A     A"," BAAAB "," A   A "," C   C ","       ","       ","       ","AB B BA").setRepeatable(4, 9)
                    .aisle("AAAAAAA","  AAA  "," A   A ","       ","       ","       ","       ","AB B BA")
                    .aisle("A AAA A","A AAA A","AA   AA","A     A","A     A","A     A","A     A","ACACACA")
                    .where("~", Predicates.controller(Predicates.blocks(definition.getBlock())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:solid_machine_casing"))).or(autoAbilities(definition.getRecipeTypes())))
                    .where("B", frames(Steel))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:steel_gearbox"))))
                    .where(" ", any())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/assembly_line"))
            .register();
    public final static MultiblockMachineDefinition LARGE_STEAM_MIXER = REGISTRATE.multiblock("large_steam_mixer", (m) -> new SteamParallelMultiblockMachine(m, 128))
            .langValue("Large Steam Mixer")
            .recipeType(MIXER_RECIPES)
            .appearanceBlock(CASING_INDUSTRIAL_STEAM)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("   BBBBB   ","   DDDDD   ","   DDDDD   ","     C     ","     C     ","           ","           ")
                    .aisle("  BBBBBBB  ","  D  E  D  ","  D     D  ","           ","     C     ","     C     ","           ")
                    .aisle(" BBBBBBBBB "," D   E   D "," D       D ","           ","           ","     C     ","     C     ")
                    .aisle("BBBBBBBBBBB","D    E    D","D         D","           ","           ","           ","     C     ")
                    .aisle("BBBBBBBBBBB","D    E    D","D         D","           ","           ","           ","     C     ")
                    .aisle("BBBBBBBBBBB","DEEEEFEEEED","D    F    D","C    F    C","CC   F   CC"," CC  F  CC ","  CCCCCCC  ")
                    .aisle("BBBBBBBBBBB","D    E    D","D         D","           ","           ","           ","     C     ")
                    .aisle("BBBBBBBBBBB","D    E    D","D         D","           ","           ","           ","     C     ")
                    .aisle(" BBBBBBBBB "," D   E   D "," D       D ","           ","           ","     C     ","     C     ")
                    .aisle("  BBBBBBB  ","  D  E  D  ","  D     D  ","           ","     C     ","     C     ","           ")
                    .aisle("   BBBBB   ","   DD~DD   ","   DDDDD   ","     C     ","     C     ","           ","           ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:steam_machine_casing")))
                            .or(autoAbilities(true, false, false)
                            .or(abilities(PartAbility.STEAM))
                            .or(autoAbilities(definition.getRecipeTypes(), false, false, true, true, true, true))))
                    .where("C", frames(Bronze))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:industrial_steam_casing"))))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:bronze_pipe_casing"))))
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:bronze_gearbox"))))
                    .where(" ", any())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/industrial_steam_casing"),
                    GTCEu.id("block/machines/mixer"))
            .register();
    public final static MultiblockMachineDefinition LARGE_STEAM_CENTRIFUGE = REGISTRATE.multiblock("large_steam_centrifuge", (m) -> new SteamParallelMultiblockMachine(m, 32))
            .langValue("Large Steam Centrifuge")
            .recipeType(CENTRIFUGE_RECIPES)
            .appearanceBlock(CASING_INDUSTRIAL_STEAM)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("         ","   AAA   ","   AAA   ","         ")
                    .aisle("   AAA   ","  A B A  ","  A B A  ","   AAA   ")
                    .aisle("  AAAAA  "," A  B  A "," A  B  A ","  AAAAA  ")
                    .aisle(" AAAAAAA ","A   B   A","A   B   A"," AAAAAAA ")
                    .aisle(" AAAAAAA ","ABBBCBBBA","ABBBCBBBA"," AAAAAAA ")
                    .aisle(" AAAAAAA ","A   B   A","A   B   A"," AAAAAAA ")
                    .aisle("  AAAAA  "," A  B  A "," A  B  A ","  AAAAA  ")
                    .aisle("   AAA   ","  A B A  ","  A B A  ","   AAA   ")
                    .aisle("         ","   A~A   ","   AAA   ","         ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:industrial_steam_casing")))
                            .or(autoAbilities(true, false, false)
                                    .or(abilities(PartAbility.STEAM))
                                    .or(autoAbilities(definition.getRecipeTypes(), false, false, true, true, true, true))))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:bronze_pipe_casing"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:bronze_gearbox"))))
                    .where(" ", any())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/industrial_steam_casing"),
                    GTCEu.id("block/machines/centrifuge"))
            .register();
    public final static MultiblockMachineDefinition LARGE_STEAM_ORE_WASHER = REGISTRATE.multiblock("large_steam_ore_washer", (m) -> new SteamParallelMultiblockMachine(m, 64))
            .langValue("Large Steam Ore Washer")
            .recipeType(ORE_WASHER_RECIPES)
            .appearanceBlock(CASING_INDUSTRIAL_STEAM)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAA","BBBBBBB","BBBBBBB","BBBBBBB")
                    .aisle("AAAAAAA","BCCCCCB","B     B","B     B")
                    .aisle("AAAAAAA","B     B","B     B","B     B")
                    .aisle("AAAAAAA","B     B","B     B","B     B")
                    .aisle("AAAAAAA","B     B","B     B","B     B")
                    .aisle("AAAAAAA","B     B","B     B","B     B")
                    .aisle("AAAAAAA","B     B","B     B","B     B")
                    .aisle("AAAAAAA","B     B","B     B","B     B")
                    .aisle("AAAAAAA","BCCCCCB","B     B","B     B")
                    .aisle("AAAAAAA","BBB~BBB","BBBBBBB","BBBBBBB")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:steam_machine_casing"))))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:industrial_steam_casing")))
                            .or(autoAbilities(true, false, false)
                            .or(abilities(PartAbility.STEAM))
                            .or(autoAbilities(definition.getRecipeTypes(), false, false, true, true, true, true))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:bronze_pipe_casing"))))
                    .where(" ", any())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/industrial_steam_casing"),
                    GTCEu.id("block/machines/mixer"))
            .register();
    public final static MultiblockMachineDefinition LARGE_STEAM_MACERATOR = REGISTRATE.multiblock("large_steam_macerator", (m) -> new SteamParallelMultiblockMachine(m, 64))
            .langValue("Large Steam Macerator")
            .recipeType(MACERATOR_RECIPES)
            .appearanceBlock(CASING_INDUSTRIAL_STEAM)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAA","BBBBBBB","BBBBBBB","BBBBBBB","BBBBBBB")
                    .aisle("AAAAAAA","BCCCCCB","BCCCCCB","BCCCCCB","B     B")
                    .aisle("AAAAAAA","BCCCCCB","BCCCCCB","BCCCCCB","B     B")
                    .aisle("AAAAAAA","BCCCCCB","BCCCCCB","BCCCCCB","B     B")
                    .aisle("AAAAAAA","BCCCCCB","BCCCCCB","BCCCCCB","B     B")
                    .aisle("AAAAAAA","BCCCCCB","BCCCCCB","BCCCCCB","B     B")
                    .aisle("AAAAAAA","BBBBBBB","BBB~BBB","BBBBBBB","BBBBBBB")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:steam_machine_casing")))
                            .or(autoAbilities(true, false, false)
                            .or(abilities(PartAbility.STEAM))
                            .or(autoAbilities(definition.getRecipeTypes(), false, false, true, true, true, true))))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:industrial_steam_casing"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gttcore:steam_crushing_wheels"))))
                    .where(" ", any())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/industrial_steam_casing"),
                    GTCEu.id("block/machines/mixer"))
            .register();

    public static final MultiblockMachineDefinition STEAM_DISTILLATION_TOWER = REGISTRATE
            .multiblock("steam_distillation_tower", SteamDistillationTowerMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Steam Distillation Tower")
            .recipeType(GTRecipeTypes.DISTILLATION_RECIPES)
            .recipeModifiers(SteamDistillationTowerMachine::recipeModifier)
            .appearanceBlock(CASING_STEEL_SEALED)
            .pattern(definition -> {
                TraceabilityPredicate exportPredicate = abilities(PartAbility.EXPORT_FLUIDS_1X);
                if (GTCEu.Mods.isAE2Loaded()) {
                    exportPredicate = exportPredicate.or(blocks(GTAEMachines.FLUID_EXPORT_HATCH_ME.get()));
                }
                exportPredicate.setMaxLayerLimited(1);
                TraceabilityPredicate maint = autoAbilities(true, false, false)
                        .setMaxGlobalLimited(1);
                return FactoryBlockPattern.start(RIGHT, BACK, UP)
                        .aisle("YYY", "YYY", "YYY")
                        .aisle("ZSZ", "Z#Z", "ZZZ")
                        .aisle("XXX", "X#X", "XXX").setRepeatable(0, 10)
                        .aisle("XXX", "XXX", "XXX")
                        .where('S', Predicates.controller(blocks(definition.getBlock())))
                        .where('Y', blocks(FIREBOX_STEEL.get())
                                .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(1))
                                .or(Predicates.abilities(PartAbility.STEAM).setMinGlobalLimited(1)
                                        .setMaxGlobalLimited(2))
                                .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setExactLimit(1))
                                .or(maint))
                        .where('Z', blocks(CASING_STEEL_SEALED.get())
                                .or(exportPredicate)
                                .or(maint))
                        .where('X', blocks(CASING_STEEL_SEALED.get()).or(exportPredicate))
                        .where('#', Predicates.air())
                        .build();
            })
            .allowExtendedFacing(false)
            .partSorter(Comparator.comparingInt(p -> p.self().getPos().getY()))
            .workableCasingModel(GTTCore.id("block/casings/machine_casing_sealed_steel"),
                    GTCEu.id("block/multiblock/generator/large_steel_boiler"))
            .register();
    public final static MultiblockMachineDefinition LARGE_STEAM_FORGE_HAMMER = REGISTRATE.multiblock("large_steam_forge_hammer", SteamParallelMultiblockMachine::new)
            .langValue("Large Steam Forge Hammer")
            .recipeType(FORGE_HAMMER_RECIPES)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle(" AAA ","     ","     ","     ","     ","     ")
                    .aisle("AAAAA"," AAA ","     ","     ","     ","     ")
                    .aisle("AAAAA","AA AA","A   A","A C A","AADAA"," AEA ")
                    .aisle("AAAAA"," AAA ","     ","     ","     ","     ")
                    .aisle(" A~A ","     ","     ","     ","     ","     ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:steam_machine_casing"))).or(abilities(PartAbility.STEAM)).or(autoAbilities(definition.getRecipeTypes(), false, false, true, true, true, true)))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:wrought_iron_block"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:piston"))))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:bronze_gearbox"))))
                    .where(" ", any())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTCEu.id("block/machines/forge_hammer"))
            .register();
    public final static MultiblockMachineDefinition PRIMITIVE_FERMENTER = REGISTRATE.multiblock("primitive_fermenter", WoodParallelMultiblockMachine::new)
            .langValue("Primitive Fermenter")
            .recipeType(FERMENTING_RECIPES)
            .appearanceBlock(CASING_WOOD_WALL)
            .recipeModifier(WoodParallelMultiblockMachine::recipeModifier)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A A", "BBB", "BBB", "BBB", "BBB")
                    .aisle("   ", "BBB", "B#B", "B#B", "BBB")
                    .aisle("A A", "BBB", "B~B", "BBB", "BBB")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("B", Predicates.blocks(CASING_WOOD_WALL.get())
                            .or(abilities(GTTPartAbility.WOOD_IMPORT_FLUIDS))
                            .or(abilities(GTTPartAbility.WOOD_EXPORT_FLUIDS))
                            .or(abilities(GTTPartAbility.WOOD_IMPORT_ITEMS))
                            .or(abilities(GTTPartAbility.WOOD_EXPORT_ITEMS)))
                    .where("A", Predicates.frames(Wood))
                    .where(" ", any())
                    .where("#", air())
                    .build()
            )
            .sidedWorkableCasingModel(GTCEu.id("block/casings/wood_wall"),
                    GTCEu.id("block/multiblock/multiblock_tank"))
                            .register();
    public final static MultiblockMachineDefinition PRIMITIVE_BREWERY = REGISTRATE.multiblock("primitive_brewery", WoodParallelMultiblockMachine::new)
            .langValue("Primitive Brewery")
            .recipeType(BREWING_RECIPES)
            .appearanceBlock(CASING_WOOD_WALL)
            .recipeModifier(WoodParallelMultiblockMachine::recipeModifier)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("A A", "BBB", "BBB", "BBB")
                    .aisle("   ", "BBB", "B#B", "BBB")
                    .aisle("   ", "BBB", "B#B", "BBB")
                    .aisle("A A", "BBB", "B~B", "BBB")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("B", Predicates.blocks(CASING_WOOD_WALL.get())
                                    .or(abilities(GTTPartAbility.WOOD_IMPORT_FLUIDS))
                                    .or(abilities(GTTPartAbility.WOOD_EXPORT_FLUIDS))
                                    .or(abilities(GTTPartAbility.WOOD_IMPORT_ITEMS))
                                    .or(abilities(GTTPartAbility.WOOD_EXPORT_ITEMS)))
                    .where("A", Predicates.frames(Wood))
                    .where(" ", any())
                    .where("#", air())
                    .build()
            )
            .sidedWorkableCasingModel(GTCEu.id("block/casings/wood_wall"),
                    GTCEu.id("block/multiblock/multiblock_tank"))
            .register();
    public final static MultiblockMachineDefinition LARGE_GAS_COLLECTOR = REGISTRATE
            .multiblock("large_gas_collector", WorkableElectricMultiblockMachine::new)
            .langValue("Large Gas Collector")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gtceu.gas_collector")))
            .tooltips(Component.translatable("gtceu.machine.perfect_oc"))
            .recipeType(GAS_COLLECTOR_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, OC_PERFECT_SUBTICK)
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA","ABBBA","ABBBA","ABBBA","AAAAA")
                    .aisle("ABBBA","B###B","B###B","B###B","ABBBA")
                    .aisle("ABBBA","B###B","B###B","B###B","AB~BA")
                    .aisle("ABBBA","B###B","B###B","B###B","ABBBA")
                    .aisle("AAAAA","ABBBA","ABBBA","ABBBA","AAAAA")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:solid_machine_casing"))))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:assembly_line_grating"))))
                    .where(" ", any())
                    .where("#", air())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/machines/gas_collector"))
            .register();
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
                    .aisle("XXXXXXXXXX", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "GWWWCCWWWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "GWWCPPCWWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "GWCPPPPCWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "G CPPPPC G", "GWCPPPPCWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "G  CPPC  G", "GWWCPPCWWG", "XXXXXXXXXX")
                    .aisle("XXXXXXXXXX", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "G   CC   G", "GWWWCCWWWG", "XXXXXXXXXX")
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
            .workableCasingModel(GTTCore.id("block/casings/machine_casing_radiation_proof"),
                    GTCEu.id("block/multiblock/fusion_reactor"))
            .register();
    public final static MultiblockMachineDefinition GREENHOUSE = REGISTRATE
            .multiblock("greenhouse", WorkableElectricMultiblockMachine::new)
            .langValue("Greenhouse")
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.greenhouse")))
            .recipeType(GREENHOUSE_RECIPES)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAA", "AAAAAAA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "AAAAAAA")
                    .aisle("AAAAAAA", "ACCCCCA", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "B#####B", "AAAAAAA")
                    .aisle("AAAAAAA", "AAA~AAA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "AAAAAAA")
                    .where("~", controller(blocks(definition.get())))
                    .where("B", blocks(Blocks.GLASS))
                    .where("C", blocks(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK))
                    .where("A", blocks(CASING_STEEL_SOLID.get()).setMinGlobalLimited(100)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false)))
                    .where(" ", any())
                    .where("#", air())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
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
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_frost_proof"),
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
            .workableCasingModel(GTCEu.id("block/casings/gcym/secure_maceration_casing"),
                    GTCEu.id("block/machines/rock_crusher"))
            .register();
    public final static MultiblockMachineDefinition PARTICLE_ACCELERATOR = REGISTRATE
            .multiblock("particle_accelerator", WorkableElectricMultiblockMachine::new)
            .langValue("Particle Accelerator")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip",
                    Component.translatable("gttcore.particle_accelerator")))
            .rotationState(RotationState.ALL)
            .recipeType(PARTICLE_ACCELERATOR_RECIPES)
            .recipeModifiers((m, r) -> ModifierFunction.builder().modifyAllContents(ContentModifier.multiplier(256)).eutMultiplier(256).parallels(256).build(), GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(HIGH_POWER_CASING)
            .pattern(definition -> FactoryBlockPattern.start(BACK, UP, LEFT)
                    .aisle("                                                              ","                                                              ","                          AAAAAAAAAAA                         ","                                                              ","                                                              ")
                    .aisle("                                                              ","                          AAAAAAAAAAA                         ","                      EAAABBBBBBBBBBBAAAE                     ","                          AAAAAAAAAAA                         ","                                                              ")
                    .aisle("                                                              ","                      AAAA           AAAA                     ","                   AAABBBBAAAAAAAAAAABBBBAAA                  ","                      AAAA           AAAA                     ","                                                              ")
                    .aisle("                                                              ","                   AAA                   AAA                  ","                 AABBBAAAA           AAAABBBAA                ","                   AAA                   AAA                  ","                                                              ")
                    .aisle("                                                              ","                 AAA                       AAA                ","                ABBBAA                   AABBBA               ","                 AAA                       AAA                ","                                                              ")
                    .aisle("                                                              ","                AA                           AA               ","              AABBAA                       AABBAA             ","                AA                           AA               ","                                                              ")
                    .aisle("                                                              ","              AA                               AA             ","             ABBAA                           AABBA            ","              AA                               AA             ","                                                              ")
                    .aisle("                                                              ","             AA                                 AA            ","           AABBA                               ABBAA          ","             AA                                 AA            ","                                                              ")
                    .aisle("                                                              ","           AA                                     AA          ","          ABBAA                                 AABBA         ","           AA                                     AA          ","                                                              ")
                    .aisle("                                                              ","          AA                                       AA         ","         ABBA                                     ABBA        ","          AA                                       AA         ","                                                              ")
                    .aisle("                                                              ","         AA                                         AA        ","        ABBA                                       ABBA       ","         AA                                         AA        ","                                                              ")
                    .aisle("                                                              ","         A                                           A        ","        ABA                                         ABA       ","         A                                           A        ","                                                              ")
                    .aisle("                                                              ","        A                                             A       ","       ABA                                           ABA      ","        A                                             A       ","                                                              ")
                    .aisle("                                                              ","       AA                                             AA      ","      ABBA                                           ABBA     ","       AA                                             AA      ","                                                              ")
                    .aisle("                                                              ","       A                                               A      ","      ABA                                             ABA     ","       A                                               A      ","                                                              ")
                    .aisle("                                                              ","      A                                                 A     ","     ABA                                               ABA    ","      A                                                 A     ","                                                              ")
                    .aisle("                                                              ","     AA                                                 AA    ","    ABBA                                               ABBA   ","     AA                                                 AA    ","                                                              ")
                    .aisle("                                                              ","     A                                                   A    ","    ABA                                                 ABA   ","     A                                                   A    ","                                                              ")
                    .aisle("                                                              ","    AA                                                   AA   ","   ABBA                                                 ABBA  ","    AA                                                   AA   ","                                                              ")
                    .aisle("                                                              ","    A                                                     A   ","   ABA                                                   ABA  ","    A                                                     A   ","                                                              ")
                    .aisle("                                                              ","    A                                                     A   ","   ABA                                                   ABA  ","    A                                                     A   ","                                                              ")
                    .aisle("                                                              ","   A                                                       A  ","  EBA                                                     ABE ","   A                                                       A  ","                                                              ")
                    .aisle("                                                              ","   A                                                       A  ","  ABA                                                     ABA ","   A                                                       A  ","                                                              ")
                    .aisle("                                                              ","   A                                                       A  ","  ABA                                                     ABA ","   A                                                       A  ","                                                              ")
                    .aisle("                                                              ","   A                                                       A  ","  ABA                                                     ABA ","   A                                                       A  ","                                                              ")
                    .aisle("                                                              ","  A                                                         A "," ABA                                                       ABA","  A                                                         A ","                                                              ")
                    .aisle("                                                              "," CCC                                                        A "," CBC                                                       ABA"," CCC                                                        A ","                                                              ")
                    .aisle(" CCC                                                          ","CCCCC                                                       A ","CCBCC                                                      ABA","CCCCC                                                       A "," CCC                                                          ")
                    .aisle(" CDC                                                          ","C   C                                                       A ","D   D                                                      ABA","C   C                                                       A "," CDC                                                          ")
                    .aisle(" CDC                                                          ","C   C                                                       A ","C   D                                                      ABA","C   C                                                       A "," CDC                                                          ")
                    .aisle(" CDC                                                          ","C   C                                                       A ","~   D                                                      ABA","C   C                                                       A "," CDC                                                          ")
                    .aisle(" CDC                                                          ","C   C                                                       A ","C   D                                                      ABA","C   C                                                       A "," CDC                                                          ")
                    .aisle(" CDC                                                          ","C   C                                                       A ","D   D                                                      ABA","C   C                                                       A "," CDC                                                          ")
                    .aisle(" CCC                                                          ","CCCCC                                                       A ","CCBCC                                                      ABA","CCCCC                                                       A "," CCC                                                          ")
                    .aisle("                                                              "," CCC                                                        A "," CBC                                                       ABA"," CCC                                                        A ","                                                              ")
                    .aisle("                                                              ","  A                                                         A "," ABA                                                       ABA","  A                                                         A ","                                                              ")
                    .aisle("                                                              ","   A                                                       A  ","  ABA                                                     ABA ","   A                                                       A  ","                                                              ")
                    .aisle("                                                              ","   A                                                       A  ","  ABA                                                     ABA ","   A                                                       A  ","                                                              ")
                    .aisle("                                                              ","   A                                                       A  ","  ABA                                                     ABA ","   A                                                       A  ","                                                              ")
                    .aisle("                                                              ","   A                                                       A  ","  EBA                                                     ABE ","   A                                                       A  ","                                                              ")
                    .aisle("                                                              ","    A                                                     A   ","   ABA                                                   ABA  ","    A                                                     A   ","                                                              ")
                    .aisle("                                                              ","    A                                                     A   ","   ABA                                                   ABA  ","    A                                                     A   ","                                                              ")
                    .aisle("                                                              ","    AA                                                   AA   ","   ABBA                                                 ABBA  ","    AA                                                   AA   ","                                                              ")
                    .aisle("                                                              ","     A                                                   A    ","    ABA                                                 ABA   ","     A                                                   A    ","                                                              ")
                    .aisle("                                                              ","     AA                                                 AA    ","    ABBA                                               ABBA   ","     AA                                                 AA    ","                                                              ")
                    .aisle("                                                              ","      A                                                 A     ","     ABA                                               ABA    ","      A                                                 A     ","                                                              ")
                    .aisle("                                                              ","       A                                               A      ","      ABA                                             ABA     ","       A                                               A      ","                                                              ")
                    .aisle("                                                              ","       AA                                             AA      ","      ABBA                                           ABBA     ","       AA                                             AA      ","                                                              ")
                    .aisle("                                                              ","        A                                             A       ","       ABA                                           ABA      ","        A                                             A       ","                                                              ")
                    .aisle("                                                              ","         A                                           A        ","        ABA                                         ABA       ","         A                                           A        ","                                                              ")
                    .aisle("                                                              ","         AA                                         AA        ","        ABBA                                       ABBA       ","         AA                                         AA        ","                                                              ")
                    .aisle("                                                              ","          AA                                       AA         ","         ABBA                                     ABBA        ","          AA                                       AA         ","                                                              ")
                    .aisle("                                                              ","           AA                                     AA          ","          ABBAA                                 AABBA         ","           AA                                     AA          ","                                                              ")
                    .aisle("                                                              ","             AA                                 AA            ","           AABBA                               ABBAA          ","             AA                                 AA            ","                                                              ")
                    .aisle("                                                              ","              AA                               AA             ","             ABBAA                           AABBA            ","              AA                               AA             ","                                                              ")
                    .aisle("                                                              ","                AA                           AA               ","              AABBAA                       AABBAA             ","                AA                           AA               ","                                                              ")
                    .aisle("                                                              ","                 AAA                       AAA                ","                ABBBAA                   AABBBA               ","                 AAA                       AAA                ","                                                              ")
                    .aisle("                                                              ","                   AAA                   AAA                  ","                 AABBBAAAA           AAAABBBAA                ","                   AAA                   AAA                  ","                                                              ")
                    .aisle("                                                              ","                      AAAA           AAAA                     ","                   AAABBBBAAAAAAAAAAABBBBAAA                  ","                      AAAA           AAAA                     ","                                                              ")
                    .aisle("                                                              ","                          AAAAAAAAAAA                         ","                      EAAABBBBBBBBBBBAAAE                     ","                          AAAAAAAAAAA                         ","                                                              ")
                    .aisle("                                                              ","                                                              ","                          AAAAAAAAAAA                         ","                                                              ","                                                              ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:atomic_casing"))))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:fusion_coil"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:high_power_casing"))).or(autoAbilities(definition.getRecipeTypes())).or(autoAbilities(true, false, true)))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:fusion_glass"))))
                    .where(" ", any())
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:atomic_casing"))).or(Predicates.abilities(GTTPartAbility.EXPORT_HIGH_ENERGY_LASER).setMaxGlobalLimited(1)))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/hpca/high_power_casing"),
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
                    .workableCasingModel(FusionReactorMachine.getCasingType(tier).getTexture(), GTCEu.id("block/multiblock/fusion_reactor"))
                    .hasBER(true)
                    .register(),
            LuV, ZPM, UV);
    public final static MultiblockMachineDefinition LARGE_SUPERCRITICAL_STEAM_TURBINE = registerLargeTurbine("supercritical_steam_large_turbine",
            LuV,
            SUPERCRITICAL_STEAM_TURBINE_FUELS,
            CASING_NAQUADAH_ALLOY_TURBINE, CASING_TUNGSTENSTEEL_GEARBOX,
            GTTCore.id("block/casings/machine_casing_turbine_naquadah_alloy"),
            GTCEu.id("block/multiblock/generator/large_steam_turbine"),
            false);
    public final static MultiblockMachineDefinition HUGE_SUPERCRITICAL_STEAM_TURBINE = registerHugeTurbine("supercritical_steam_huge_turbine",
            ZPM,
            SUPERCRITICAL_STEAM_TURBINE_FUELS,
            CASING_NAQUADAH_ALLOY_TURBINE, CASING_TUNGSTENSTEEL_GEARBOX,
            GTTCore.id("block/casings/machine_casing_turbine_naquadah_alloy"),
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
            .recipeModifiers(OC_PERFECT_SUBTICK)
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
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                    GTCEu.id(("block/multiblock/generator/large_steam_turbine")))
            .register();
    public final static MultiblockMachineDefinition HUGE_CHEMICAL_REACTOR = REGISTRATE
            .multiblock("huge_chemical_reactor", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, OC_PERFECT_SUBTICK)
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
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();
    public final static MultiblockMachineDefinition CHEMICAL_PLANT = REGISTRATE
            .multiblock("chemical_plant", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CHEMICAL_PLANT_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, OC_PERFECT_SUBTICK)
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
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static final MultiblockMachineDefinition LARGE_PRIMITIVE_BLAST_FURNACE = GTRegistration.REGISTRATE
            .multiblock("large_primitive_blast_furnace", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.PRIMITIVE_BLAST_FURNACE_RECIPES)
            .model(createWorkableCasingMachineModel(GTCEu.id("block/casings/solid/machine_primitive_bricks"),
                    GTCEu.id("block/multiblock/primitive_blast_furnace"))
                    .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createPBFLavaRender)))
            .hasBER(true)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("  AAAAA  ","  ABBBA  ","  ABBBA  ","  AAAAA  ","  ABBBA  ","  ABBBA  ","  AAAAA  ","  ABBBA  ","  AAAAA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  AAAAA  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  ABBBA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  AAAAA  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  ABBBA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  AAAAA  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  ABBBA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  AAAAA  ","  AACAA  ","  ACCCA  ","  AACAA  ","  AAAAA  ","  AAAAA  ","  AAAAA  ","  AAAAA  ","  AAAAA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("         ","    C    ","   CCC   ","    C    ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  DDDDD  ","  DDCDD  ","  ACCCA  ","  AACAA  ","  AAAAA  ","  AAAAA  ","    A    ","    A    ","    A    ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle(" DDDDDDD "," DDDDDDD "," A     A "," A     A "," A     A "," A     A ","  AAAAA  ","  AAAAA  ","  AAAAA  ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("DDDDDDDDD","DDDDDDDDD","A       A","A       A","A       A","A       A"," AA   AA "," AA   AA "," AA   AA ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","   AAA   ")
                    .aisle("DDDDDDDDD","DDDDDDDDD","A       A","A       A","A       A","A       A"," A     A "," A     A "," A     A "," A     A "," A     A "," A     A "," A     A "," A     A ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ")
                    .aisle("DDDDDDDDD","DDDDDDDDD","A       A","A       ~","A       A","A       A","AA     AA","AA     AA","AA     AA"," A     A "," A     A "," A     A "," A     A "," A     A ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ")
                    .aisle("DDDDDDDDD","DDDDDDDDD","A       A","A       A","A       A","A       A"," A     A "," A     A "," A     A "," A     A "," A     A "," A     A "," A     A "," A     A ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ")
                    .aisle("DDDDDDDDD","DDDDDDDDD","A       A","A       A","A       A","A       A"," AA   AA "," AA   AA "," AA   AA ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","  A   A  ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","   AAA   ")
                    .aisle(" DDDDDDD "," DDDDDDD "," A     A "," A     A "," A     A "," A     A ","  AAAAA  ","  AAAAA  ","  AAAAA  ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","   AAA   ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  DDDDD  ","  DDCDD  ","  ACCCA  ","  AACAA  ","  AAAAA  ","  AAAAA  ","    A    ","    A    ","    A    ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("         ","    C    ","   CCC   ","    C    ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  AAAAA  ","  AACAA  ","  ACCCA  ","  AACAA  ","  AAAAA  ","  AAAAA  ","  AAAAA  ","  AAAAA  ","  AAAAA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  AAAAA  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  ABBBA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  AAAAA  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  ABBBA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  AAAAA  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  B   B  ","  A   A  ","  B   B  ","  ABBBA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .aisle("  AAAAA  ","  ABBBA  ","  ABBBA  ","  AAAAA  ","  ABBBA  ","  ABBBA  ","  AAAAA  ","  ABBBA  ","  AAAAA  ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ","         ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:firebricks")))
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false))
                    )
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:assembly_line_grating"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:bronze_pipe_casing"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:bronze_firebox_casing"))))
                    .where(" ", any())
                    .build())
            .register();

    public static final MultiblockMachineDefinition BEDROCK_DRILL = GTRegistration.REGISTRATE
            .multiblock("bedrock_drill", BedrockDrillMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(DUMMY_RECIPES)
            .model(createWorkableCasingMachineModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/multiblock_workable"))
                    .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createPBFLavaRender)))
            .hasBER(true)
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start(RelativeDirection.LEFT, RelativeDirection.UP, RelativeDirection.BACK)
                    .aisle("       ","  AAA  ","  A~A  ","  AAA  ","       ","       ","       ","       ","       ","       ","       ","       ","       ")
                    .aisle(" B   B ","AAAAAAA","AAAAAAA","AAAAAAA","       ","       ","       ","       ","       ","       ","       ","       ","       ")
                    .aisle("B     B","AA   AA","AA   AA","AA   AA","A     A","       ","       ","       ","       ","       ","       ","       ","       ")
                    .aisle("       ","A     A","A  C  A","A  C  A","A  C  A","A  C  A","   C   ","   C   ","   C   ","   C   ","   C   ","       ","       ")
                    .aisle("       ","A  C  A","A CDC A","A CDC A","A CDC A","ABCDCBA","ABCDCBA"," BCDCB ","  CDC  ","  CDC  ","  CDC  ","   C   ","       ")
                    .aisle("B     B","AA   AA","AA C AA","AA C AA","AA C AA","AA C AA","AA C AA","AA C AA","AA C AA","   C   ","   C   ","       ","       ")
                    .aisle(" B   B "," AA AA "," AA AA "," AA AA "," AA AA "," AABAA "," AABAA "," AABAA "," AABAA "," AABAA "," AABAA ","       ","       ")
                    .aisle("  B B  ","  AAA  ","  AAA  ","  AAA  ","  AAA  ","  AAA  ","  AAA  ","  AAA  ","  A A  ","  A A  ","  A A  ","  A A  ","  A A  ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:solid_machine_casing")))
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false))
                    )
                    .where("B", Predicates.frames(Steel))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:stable_machine_casing"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:titanium_gearbox"))))
                    .where(" ", any())
                    .build())
            .register();

    public static final MultiblockMachineDefinition NETHER_CAPSULE = GTRegistration.REGISTRATE
            .multiblock("nether_capsule", NetherCapsuleMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(DUMMY_RECIPES)
            .model(createWorkableCasingMachineModel(GTCEu.id("block/casings/solid/machine_casing_stable_titanium"),
                    GTCEu.id("block/multiblock/multiblock_workable"))
                    .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createPBFLavaRender)))
            .hasBER(true)
            .appearanceBlock(CASING_TITANIUM_STABLE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("   "," A "," ~ ","   ")
                    .aisle(" A ","A A","A A"," A ")
                    .aisle("   ","   ","   ","   ")
                    .where("~", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:stable_machine_casing"))))
                    .where(" ", any())
                    .build())
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
                .workableCasingModel(casingTexture, overlayModel)
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
                .workableCasingModel(casingTexture, overlayModel)
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


