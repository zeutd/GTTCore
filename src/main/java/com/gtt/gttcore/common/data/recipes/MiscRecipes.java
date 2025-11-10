package com.gtt.gttcore.common.data.recipes;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.recipe.CraftingComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.GTCraftingComponents;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTBlocks;
import com.gtt.gttcore.common.data.GTTItems;
import com.gtt.gttcore.common.data.GTTMultiMachines;
import com.gtt.gttcore.data.recipe.GTTTags;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.*;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.COIL_HEATING_DOUBLE;
import static com.gtt.gttcore.common.data.GTTBlocks.*;
import static com.gtt.gttcore.common.data.GTTItems.*;
import static com.gtt.gttcore.common.data.GTTMachines.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.GTTMultiMachines.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;

public class MiscRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        registerMachineRecipes(provider);
        registerAE2Recipes(provider);
        CHEMICAL_RECIPES.recipeBuilder("ultra_high_molecular_weight_polyethylene")
                .inputFluids(Ethylene.getFluid(144))
                .notConsumable(dust, ZieglerNattaCatalyst)
                .outputFluids(UltraHighMolecularWeightPolyethylene.getFluid(216))
                .duration(100)
                .EUt(VA[2])
                .save(provider);
        for(int i = 0; i < PACKAGED_CIRCUITS_ARRAY.length; i++){
            PACKER_RECIPES.recipeBuilder(PACKAGED_CIRCUITS_ARRAY[i].getId().getPath())
                    .inputItems(CustomTags.CIRCUITS_ARRAY[i])
                    .outputItems(PACKAGED_CIRCUITS_ARRAY[i].get())
                    .duration(20)
                    .EUt(VH[0])
                    .save(provider);
        }
        ASSEMBLER_RECIPES.recipeBuilder("casing_iridium_reinforced").EUt(16).inputItems(plate, Iridium, 6)
                .inputItems(frameGt, Osmiridium).circuitMeta(6)
                .outputItems(GTTBlocks.CASING_IRIDIUM_REINFORCED.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("casing_lead_radiation_proof").EUt(16).inputItems(plate, Lead, 6)
                .inputItems(frameGt, Steel).circuitMeta(6)
                .outputItems(GTTBlocks.CASING_LEAD_RADIATION_PROOF.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("casing_atomic").EUt(16).inputItems(plate, AtomicSteel, 6)
                .inputItems(frameGt, NaquadahAlloy).circuitMeta(6)
                .outputItems(GCYMBlocks.CASING_ATOMIC.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("casing_low_neutron_absorption").EUt(16).inputItems(plate, Zirconium, 6)
                .inputItems(frameGt, Zirconium).circuitMeta(6)
                .outputItems(CASING_LOW_NEUTRON_ABSORPTION.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("casing_tungsteensteel_robust").EUt(16).inputItems(plate, Zirconium, 6)
                .inputItems(frameGt, Zirconium).circuitMeta(6)
                .outputItems(
                        CASING_LOW_NEUTRON_ABSORPTION.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("casing_zirconium_pipe"),
                GTTBlocks.CASING_ZIRCONIUM_PIPE.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "PIP", "IFI",
                "PIP", 'P', new MaterialEntry(TagPrefix.plate, Zirconium), 'F',
                new MaterialEntry(TagPrefix.frameGt, GTMaterials.Zirconium), 'I',
                new MaterialEntry(TagPrefix.pipeNormalFluid, GTMaterials.Zirconium));
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder("lead_glass").EUt(16)
                .inputItems(Tags.Blocks.GLASS)
                .inputFluids(Lead.getFluid(L * 2))
                .duration(50).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("depleted_uranium")
                .EUt(VA[EV])
                .inputItems(dust, DepletedUranium238)
                .outputItems(dustTiny, Plutonium239, 6)
                .outputItems(dustTiny, Uranium238, 1)
                .outputItems(dustTiny, Neptunium, 2)
                .duration(400).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("depleted_plutonium")
                .EUt(VA[EV])
                .inputItems(dust, DepletedPlutonium239)
                .outputItems(dustTiny, Americium, 6)
                .outputItems(dustTiny, Plutonium239, 1)
                .outputItems(dustTiny, Plutonium241, 2)
                .duration(400).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("depleted_thorium")
                .EUt(VA[EV])
                .inputItems(dust, DepletedThorium)
                .outputItems(dustTiny, Polonium, 6)
                .outputItems(dustTiny, Thorium, 1)
                .outputItems(dustTiny, Radium, 2)
                .duration(400).save(provider);
        SUPERCRITICAL_STEAM_TURBINE_FUELS.recipeBuilder("supercritical_steam")
                .inputFluids(SupercriticalSteam.getFluid(64))
                .outputFluids(DistilledWater.getFluid(4))
                .duration(5)
                .EUt(-VA[ZPM])
                .save(provider);
    }
    public static void registerMachineRecipes(Consumer<FinishedRecipe> provider){
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("greenhouse_machine"),
                GREENHOUSE.asStack(),
                "AWA",
                "GSG",
                "WAW",
                'A', CustomTags.LV_CIRCUITS,
                'W', new MaterialEntry(cableGtSingle, Copper),
                'G', Tags.Blocks.GLASS,
                'S', CASING_STEEL_SOLID.asStack()
        );
        ASSEMBLER_RECIPES.recipeBuilder("fission_reactor_machine")
                .inputItems(CASING_LEAD_RADIATION_PROOF)
                .inputItems(CustomTags.LuV_CIRCUITS, 5)
                .inputItems(NEUTRON_REFLECTOR, 5)
                .inputItems(CustomTags.IV_CIRCUITS, 6)
                .inputItems(ELECTRIC_PUMP_IV, 10)
                .inputItems(ELECTRIC_PISTON_IV, 10)
                .outputItems(FISSION_REACTOR)
                .EUt(VA[IV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("huge_steam_turbine_machine")
                .inputItems(CASING_STEEL_SOLID)
                .inputItems(gear, StainlessSteel, 5)
                .inputItems(CustomTags.HV_CIRCUITS, 4)
                .inputItems(ELECTRIC_PUMP_HV, 2)
                .outputItems(HUGE_STEAM_TURBINE)
                .EUt(VA[EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("huge_gas_turbine_machine")
                .inputItems(CASING_STEEL_SOLID)
                .inputItems(CustomTags.EV_CIRCUITS, 2)
                .inputItems(gear, TungstenSteel, 5)
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputItems(ELECTRIC_PUMP_EV, 2)
                .outputItems(HUGE_GAS_TURBINE)
                .EUt(VA[EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("huge_plasma_turbine_machine")
                .inputItems(CASING_STEEL_SOLID)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(gear, NaquadahAlloy, 5)
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(ELECTRIC_PUMP_IV, 2)
                .outputItems(HUGE_PLASMA_TURBINE)
                .EUt(VA[EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("huge_supercritical_steam_turbine_machine"))
                .inputItems(CASING_STEEL_SOLID)
                .inputItems(CustomTags.ZPM_CIRCUITS, 2)
                .inputItems(gear, Neutronium, 5)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(ELECTRIC_PUMP_LuV, 2)
                .outputItems(HUGE_SUPERCRITICAL_STEAM_TURBINE)
                .EUt(VA[EV]).duration(200).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_supercritical_steam_turbine_machine"),
                LARGE_SUPERCRITICAL_STEAM_TURBINE.asStack(), "PSP", "SAS", "CSC", 'S',
                new MaterialEntry(TagPrefix.gear, NaquadahAlloy), 'P', CustomTags.ZPM_CIRCUITS, 'A',
                GTMachines.HULL[GTValues.ZPM].asStack(), 'C',
                new MaterialEntry(TagPrefix.pipeLargeFluid, GTMaterials.NaquadahAlloy));
        ASSEMBLER_RECIPES.recipeBuilder("fission_reactor_machine")
                .inputItems(CASING_LEAD_RADIATION_PROOF)
                .inputItems(CustomTags.LuV_CIRCUITS, 5)
                .inputItems(NEUTRON_REFLECTOR, 5)
                .inputItems(CustomTags.IV_CIRCUITS, 6)
                .inputItems(ELECTRIC_PUMP_IV, 10)
                .inputItems(ELECTRIC_PISTON_IV, 10)
                .outputItems(FISSION_REACTOR)
                .EUt(VA[IV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("create_track")
                .inputItems(AllTags.AllItemTags.SLEEPERS.tag)
                .inputItems(Ingredient.fromValues(Stream.of(new Ingredient.TagValue(Tags.Items.NUGGETS_IRON), new Ingredient.TagValue(TagUtil.createItemTag("nuggets/zinc")))))
                .outputItems(AllBlocks.TRACK, 16)
                .EUt(VH[ULV])
                .duration(100)
                .save(provider);




        BREWING_RECIPES.recipeBuilder("raw_beer")
                .inputItems(dust, Wheat)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(RawBeer.getFluid(1000))
                .save(provider);
        FERMENTING_RECIPES.recipeBuilder("beer")
                .inputFluids(RawBeer.getFluid(1000))
                .outputFluids(Beer.getFluid(1000))
                .save(provider);
        CANNER_RECIPES.recipeBuilder("beer_bottle")
                .inputFluids(Beer.getFluid(250))
                .inputItems(Items.GLASS_BOTTLE)
                .outputItems(BEER_BOTTLE)
                .save(provider);













        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("conveyor_from_rubber_plate"),
                AllItems.BELT_CONNECTOR.asStack(4),
                "SSS",
                        "SSS",
                        "   ",
                'S', new MaterialEntry(plate, Rubber)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("conveyor_from_styrene_butadiene_rubber_plate"),
                AllItems.BELT_CONNECTOR.asStack(4),
                "SSS",
                "SSS",
                "   ",
                'S', new MaterialEntry(plate, StyreneButadieneRubber)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("conveyor_from_silicon_rubber_plate"),
                AllItems.BELT_CONNECTOR.asStack(4),
                "SSS",
                "SSS",
                "   ",
                'S', new MaterialEntry(plate, SiliconeRubber)
        );







        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("andesite_alloy"),
            AllItems.ANDESITE_ALLOY.asStack(1),
                "BA",
                "AB",
                'A', new MaterialEntry(dust, Andesite),
                'B', GTTTags.andesiteAlloyable
        );

        for (Material material : andesiteAlloyIngredient) {
            VanillaRecipeHelper.addShapedRecipe(provider, String.format("mortar_grind_%s", material.getName()),
                    ChemicalHelper.get(dust, material), "X", "m", 'X', new MaterialEntry(rawOre, material));
        }
        ASSEMBLER_RECIPES.recipeBuilder("casing_stainless_evaporation")
                .inputItems(GTBlocks.CASING_STAINLESS_CLEAN.asStack(1))
                .inputItems(wireGtDouble, AnnealedCopper, 4)
                .inputFluids(PolyvinylChloride, L * 2)
                .outputItems(
                        GTTBlocks.CASING_STAINLESS_EVAPORATION.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(30).EUt(VA[HV]).addMaterialInfo(true, true).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("evaporation_plant"),
                GTTMultiMachines.EVAPORATION_PLANT.asStack(), "CBC", "FMF", "CBC", 'M', GTMachines.HULL[HV].asStack(),
                'B', new MaterialEntry(TagPrefix.wireGtDouble, GTMaterials.Kanthal), 'C', CustomTags.HV_CIRCUITS,
                'F', GTItems.ELECTRIC_PUMP_HV);
        VanillaRecipeHelper.addShapedRecipe(provider, GTTCore.id("steam_crushing_wheels"), CRUSHING_WHEELS.asStack(2),
                "TTT", "UCU",
                "UMU", 'T', new MaterialEntry(gearSmall, WroughtIron), 'U', ChemicalHelper.get(gear, Steel),
                'C', CASING_INDUSTRIAL_STEAM.asStack(), 'M', ChemicalHelper.get(pipeLargeFluid, Bronze));
        VanillaRecipeHelper.addShapedRecipe(provider, GTTCore.id("greenhouse"), GREENHOUSE.asStack(),
                "CGC",
                "RAR",
                "BPB",
                'B', Items.BONE_MEAL,
                'C', CustomTags.ULV_CIRCUITS,
                'G', ChemicalHelper.get(gear, Steel),
                'R', GTCraftingComponents.ROBOT_ARM.get(0),
                'A', CASING_STEEL_SOLID,
                'P', ELECTRIC_PUMP_ULV
                );

        SIFTER_RECIPES.recipeBuilder(GTTCore.id("dirt_from_coarse_dirt"))
                .EUt(VA[ULV])
                .inputItems(Items.ROOTED_DIRT)
                .duration(20 * 10)
                .outputItems(Items.DIRT)
                .save(provider);









        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("casing_grate_casing"),
                GTBlocks.CASING_GRATE.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "PVP", "PFP", "PMP", 'P',
                new ItemStack(Blocks.IRON_BARS, 1), 'F', new MaterialEntry(TagPrefix.frameGt, GTMaterials.Steel),
                'M', GTTItems.ELECTRIC_MOTOR_ULV, 'V', new MaterialEntry(TagPrefix.rotor, GTMaterials.Steel));
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("casing_grate_casing"))
                .inputItems(Items.IRON_BARS, 6)
                .inputItems(frameGt, Steel)
                .inputItems(ELECTRIC_MOTOR_ULV)
                .inputItems(rotor, Steel)
                .outputItems(GTBlocks.CASING_GRATE, ConfigHolder.INSTANCE.recipes.casingsPerCraft)
                .duration(800)
                .EUt(VA[ULV])
                .save(provider);




        MIXER_RECIPES.recipeBuilder(GTTCore.id("tin_alloy")).duration(100).EUt(VA[LV])
                .inputItems(dust, Tin)
                .inputItems(dust, Iron)
                .circuitMeta(1)
                .outputItems(dust, TinAlloy, 2)
                .save(provider);



        DISTILLATION_RECIPES.recipeBuilder(GTTCore.id("distill_fermented_biomass"))
                .inputFluids(FermentedBiomass.getFluid(1000))
                .outputItems(FERTILIZER)
                .outputFluids(AceticAcid.getFluid(25))
                .outputFluids(Water.getFluid(375))
                .outputFluids(Ethanol.getFluid(150))
                .outputFluids(Methanol.getFluid(150))
                .outputFluids(Ammonia.getFluid(100))
                .outputFluids(CarbonDioxide.getFluid(400))
                .outputFluids(Methane.getFluid(600))
                .duration(75).EUt(VA[MV]).save(provider);



        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("phenolic_resin"))
                .duration(100)
                .EUt(VA[ULV])
                .inputFluids(Phenol.getFluid(L))
                .inputFluids(Formaldehyde.getFluid(L * 2))
                .outputFluids(PhenolicResin.getFluid(L * 3))
                .save(provider);
    }

    public static void registerAE2Recipes(Consumer<FinishedRecipe> provider){
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("calculation_processor_press"))
                .notConsumable(AEItems.CALCULATION_PROCESSOR_PRESS.asItem())
                .inputItems(Blocks.IRON_BLOCK.asItem())
                .outputItems(AEItems.CALCULATION_PROCESSOR_PRESS.asItem())
                .EUt(VA[LV])
                .duration(20 * 5)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("engineering_processor_press"))
                .notConsumable(AEItems.ENGINEERING_PROCESSOR_PRESS.asItem())
                .inputItems(Blocks.IRON_BLOCK.asItem())
                .outputItems(AEItems.ENGINEERING_PROCESSOR_PRESS.asItem())
                .EUt(VA[LV])
                .duration(20 * 5)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("logic_processor_press"))
                .notConsumable(AEItems.LOGIC_PROCESSOR_PRESS.asItem())
                .inputItems(Blocks.IRON_BLOCK.asItem())
                .outputItems(AEItems.LOGIC_PROCESSOR_PRESS.asItem())
                .EUt(VA[LV])
                .duration(20 * 5)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("silicon_processor_press"))
                .notConsumable(AEItems.SILICON_PRESS.asItem())
                .inputItems(Blocks.IRON_BLOCK.asItem())
                .outputItems(AEItems.SILICON_PRESS.asItem())
                .EUt(VA[LV])
                .duration(20 * 5)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("silicon_print"))
                .notConsumable(AEItems.SILICON_PRESS.asItem())
                .inputItems(plate, Silicon)
                .outputItems(AEItems.SILICON_PRINT.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("engineering_processor_print"))
                .notConsumable(AEItems.ENGINEERING_PROCESSOR_PRESS.asItem())
                .inputItems(plate, Diamond)
                .outputItems(AEItems.ENGINEERING_PROCESSOR_PRINT.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("logic_processor_print"))
                .notConsumable(AEItems.LOGIC_PROCESSOR_PRESS.asItem())
                .inputItems(plate, Gold)
                .outputItems(AEItems.LOGIC_PROCESSOR_PRINT.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("calculation_processor_print"))
                .notConsumable(AEItems.CALCULATION_PROCESSOR_PRESS.asItem())
                .inputItems(plate, CertusQuartz)
                .outputItems(AEItems.CALCULATION_PROCESSOR_PRINT.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("calculation_processor"))
                .inputItems(AEItems.CALCULATION_PROCESSOR_PRINT.asItem())
                .inputItems(plate, Redstone)
                .inputItems(AEItems.SILICON_PRINT.asItem())
                .outputItems(AEItems.CALCULATION_PROCESSOR.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("engineering_processor"))
                .inputItems(AEItems.ENGINEERING_PROCESSOR_PRINT.asItem())
                .inputItems(plate, Redstone)
                .inputItems(AEItems.SILICON_PRINT.asItem())
                .outputItems(AEItems.ENGINEERING_PROCESSOR.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        FORMING_PRESS_RECIPES.recipeBuilder(GTTCore.id("logic_processor"))
                .inputItems(AEItems.LOGIC_PROCESSOR_PRINT.asItem())
                .inputItems(plate, Redstone)
                .inputItems(AEItems.SILICON_PRINT.asItem())
                .outputItems(AEItems.LOGIC_PROCESSOR.asItem())
                .EUt(VA[LV])
                .duration(20)
                .save(provider);
        ALLOY_SMELTER_RECIPES.recipeBuilder(GTTCore.id("quartz_glass"))
                .inputItems(gem, CertusQuartz)
                .inputItems(block, Glass)
                .outputItems(AEBlocks.QUARTZ_GLASS.asItem())
                .EUt(VA[LV])
                .duration(100)
                .save(provider);

        ALLOY_SMELTER_RECIPES.recipeBuilder(GTTCore.id("quartz_vibrant_glass"))
                .inputItems(dust, Glowstone)
                .inputItems(AEBlocks.QUARTZ_GLASS.asItem())
                .outputItems(AEBlocks.QUARTZ_VIBRANT_GLASS.asItem())
                .EUt(VA[LV])
                .duration(100)
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("fluix_crystal"))
                .inputItems(dust, Redstone)
                .inputItems(dust, CertusQuartz)
                .inputItems(dust, NetherQuartz)
                .inputFluids(Water.getFluid(250))
                .outputItems(gem, FluixCrystal, 2)
                .duration(20 * 90)
                .EUt(VA[LV])
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("fluix_crystal_distilled"))
                .inputItems(dust, Redstone)
                .inputItems(dust, CertusQuartz)
                .inputItems(dust, NetherQuartz)
                .inputFluids(DistilledWater.getFluid(250))
                .outputItems(gem, FluixCrystal, 2)
                .duration(20 * 60)
                .EUt(VA[LV])
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("damaged_budding_quartz"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .inputItems(block, CertusQuartz)
                .inputFluids(DistilledWater.getFluid(250))
                .outputItems(AEBlocks.DAMAGED_BUDDING_QUARTZ.asItem())
                .duration(20 * 60)
                .EUt(VA[LV])
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("chipped_budding_quartz"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .inputItems(AEBlocks.DAMAGED_BUDDING_QUARTZ.asItem())
                .inputFluids(DistilledWater.getFluid(250))
                .outputItems(AEBlocks.CHIPPED_BUDDING_QUARTZ.asItem())
                .duration(20 * 60)
                .EUt(VA[LV])
                .save(provider);
        AUTOCLAVE_RECIPES.recipeBuilder(GTTCore.id("flawed_budding_quartz"))
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .inputItems(AEBlocks.CHIPPED_BUDDING_QUARTZ.asItem())
                .inputFluids(DistilledWater.getFluid(250))
                .outputItems(AEBlocks.FLAWED_BUDDING_QUARTZ.asItem())
                .duration(20 * 60)
                .EUt(VA[LV])
                .save(provider);
        IMPLOSION_RECIPES.recipeBuilder(GTTCore.id("entangled_singularity"))
                .inputItems(AEItems.SINGULARITY.asItem())
                .inputItems(dust, EnderPearl)
                .outputItems(AEItems.QUANTUM_ENTANGLED_SINGULARITY.asItem(), 2)
                .duration(20)
                .EUt(VA[LV])
                .save(provider);
        IMPLOSION_RECIPES.recipeBuilder(GTTCore.id("entangled_singularity_from_pearl"))
                .inputItems(AEItems.SINGULARITY.asItem())
                .inputItems(gem, EnderPearl)
                .outputItems(AEItems.QUANTUM_ENTANGLED_SINGULARITY.asItem(), 2)
                .duration(20)
                .EUt(VA[LV])
                .save(provider);
        MACERATOR_RECIPES.recipeBuilder(GTTCore.id("sky_dust"))
                .inputItems(AEBlocks.SKY_STONE_BLOCK.asItem())
                .outputItems(AEItems.SKY_DUST.asItem())
                .EUt(VA[LV])
                .duration(20 * 20)
                .save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder(GTTCore.id("sky_dust_separation"))
                .inputItems(AEItems.SKY_DUST.asItem())
                .chancedOutput(dust, CertusQuartz, 700, 0)
                .chancedOutput(dust, FluixCrystal, 700, 0)
                .chancedOutput(dust, Tantalite, 700, 0)
                .chancedOutput(dust, Iron, 2500, 0)
                .EUt(VA[LV])
                .duration(20 * 15)
                .save(provider);
        ARC_FURNACE_RECIPES.recipeBuilder(GTTCore.id("certus_quartz_charging"))
                .inputItems(gem, CertusQuartz)
                .outputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem())
                .EUt(VA[LV])
                .duration(20 * 15)
                .save(provider);

    }
}