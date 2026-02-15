package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.config.ConfigHolder;
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
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.CASING_INDUSTRIAL_STEAM;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMultiMachines.LARGE_CHEMICAL_REACTOR;
import static com.gregtechceu.gtceu.common.data.machines.GTMultiMachines.PRIMITIVE_BLAST_FURNACE;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.*;
import static com.gtt.gttcore.common.data.GTTBlocks.*;
import static com.gtt.gttcore.common.data.GTTItems.*;
import static com.gtt.gttcore.common.data.GTTMachines.STEAM_CENTRIFUGE;
import static com.gtt.gttcore.common.data.GTTMachines.STEAM_MIXER;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.GTTMultiMachines.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.SUPERCRITICAL_STEAM_TURBINE_FUELS;

@SuppressWarnings("removal")
public class MiscRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        registerMachineRecipes(provider);











        for(int i = 0; i < PACKAGED_CIRCUITS_ARRAY.length; i++){
            PACKER_RECIPES.recipeBuilder(GTTCore.id(PACKAGED_CIRCUITS_ARRAY[i].getId().getPath()))
                    .inputItems(CustomTags.CIRCUITS_ARRAY[i])
                    .outputItems(PACKAGED_CIRCUITS_ARRAY[i].get())
                    .duration(20)
                    .EUt(VH[0])
                    .save(provider);
        }
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("casing_iridium_reinforced")).EUt(16).inputItems(plate, Iridium, 6)
                .inputItems(frameGt, Osmiridium).circuitMeta(6)
                .outputItems(GTTBlocks.CASING_IRIDIUM_REINFORCED.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("casing_lead_radiation_proof")).EUt(16).inputItems(plate, Lead, 6)
                .inputItems(frameGt, Steel).circuitMeta(6)
                .outputItems(GTTBlocks.CASING_LEAD_RADIATION_PROOF.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("casing_atomic")).EUt(16).inputItems(plate, AtomicSteel, 6)
                .inputItems(frameGt, NaquadahAlloy).circuitMeta(6)
                .outputItems(GCYMBlocks.CASING_ATOMIC.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("casing_low_neutron_absorption")).EUt(16).inputItems(plate, Zirconium, 6)
                .inputItems(frameGt, Zirconium).circuitMeta(6)
                .outputItems(CASING_LOW_NEUTRON_ABSORPTION.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("casing_processing")).EUt(16).inputItems(plate, AluminiumAlloy6061, 6)
                .inputItems(frameGt, StainlessSteel).circuitMeta(6)
                .inputItems(MOTOR.get(MV))
                .inputItems(PISTON.get(MV))
                .outputItems(CASING_PROCESSING.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "casing_processing",
                CASING_PROCESSING.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "PWP", "PFP",
                "PMP", 'P', new MaterialEntry(TagPrefix.plate, GTMaterials.StainlessSteel), 'F',
                new MaterialEntry(TagPrefix.frameGt, GTMaterials.StainlessSteel), 'W', PISTON.get(MV),
                'M', MOTOR.get(MV));
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("casing_zirconium_pipe"),
                GTTBlocks.CASING_ZIRCONIUM_PIPE.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "PIP", "IFI",
                "PIP", 'P', new MaterialEntry(TagPrefix.plate, Zirconium), 'F',
                new MaterialEntry(TagPrefix.frameGt, GTMaterials.Zirconium), 'I',
                new MaterialEntry(TagPrefix.pipeNormalFluid, GTMaterials.Zirconium));
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder(GTTCore.id("lead_glass")).EUt(16)
                .inputItems(Tags.Blocks.GLASS)
                .inputFluids(Lead.getFluid(L * 2))
                .duration(50).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder(GTTCore.id("depleted_uranium"))
                .EUt(VA[EV])
                .inputItems(dust, DepletedUranium238)
                .outputItems(dustTiny, Plutonium239, 6)
                .outputItems(dustTiny, Uranium238, 1)
                .outputItems(dustTiny, Neptunium, 2)
                .duration(400).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder(GTTCore.id("depleted_plutonium"))
                .EUt(VA[EV])
                .inputItems(dust, DepletedPlutonium239)
                .outputItems(dustTiny, Americium, 6)
                .outputItems(dustTiny, Plutonium239, 1)
                .outputItems(dustTiny, Plutonium241, 2)
                .duration(400).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder(GTTCore.id("depleted_thorium"))
                .EUt(VA[EV])
                .inputItems(dust, DepletedThorium)
                .outputItems(dustTiny, Polonium, 6)
                .outputItems(dustTiny, Thorium, 1)
                .outputItems(dustTiny, Radium, 2)
                .duration(400).save(provider);
        SUPERCRITICAL_STEAM_TURBINE_FUELS.recipeBuilder(GTTCore.id("supercritical_steam"))
                .inputFluids(SupercriticalSteam.getFluid(64))
                .outputFluids(DistilledWater.getFluid(4))
                .duration(5)
                .EUt(-VA[ZPM])
                .save(provider);













        GAS_COLLECTOR_RECIPES.recipeBuilder(GTTCore.id("venus_air"))
                .circuitMeta(4)
                .outputFluids(VenusAir.getFluid(10000))
                .dimension(new ResourceLocation("ad_astra:venus"))
                .duration(200).EUt(64).save(provider);
        VACUUM_RECIPES.recipeBuilder(GTTCore.id("liquid_venus_air"))
                .inputFluids(VenusAir.getFluid(4000))
                .outputFluids(LiquidVenusAir.getFluid(4000))
                .duration(80).EUt(VA[EV]).save(provider);
    }
    public static void registerMachineRecipes(Consumer<FinishedRecipe> provider){
//        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("fission_reactor_machine"))
//                .inputItems(CASING_LEAD_RADIATION_PROOF)
//                .inputItems(CustomTags.LuV_CIRCUITS, 5)
//                .inputItems(NEUTRON_REFLECTOR, 5)
//                .inputItems(CustomTags.IV_CIRCUITS, 6)
//                .inputItems(ELECTRIC_PUMP_IV, 10)
//                .inputItems(ELECTRIC_PISTON_IV, 10)
//                .outputItems(FISSION_REACTOR)
//                .EUt(VA[IV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("huge_steam_turbine_machine"))
                .inputItems(CASING_STEEL_SOLID)
                .inputItems(gear, Steel, 5)
                .inputItems(gearSmall, Steel, 3)
                .inputItems(CustomTags.HV_CIRCUITS, 4)
                .inputItems(ELECTRIC_PUMP_HV, 2)
                .outputItems(HUGE_STEAM_TURBINE)
                .EUt(VA[EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("huge_gas_turbine_machine"))
                .inputItems(CASING_STAINLESS_TURBINE)
                .inputItems(CustomTags.EV_CIRCUITS, 2)
                .inputItems(gear, StainlessSteel, 5)
                .inputItems(gearSmall, StainlessSteel, 3)
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputItems(ELECTRIC_PUMP_EV, 2)
                .outputItems(HUGE_GAS_TURBINE)
                .EUt(VA[EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("huge_plasma_turbine_machine"))
                .inputItems(CASING_TUNGSTENSTEEL_TURBINE)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(gear, TungstenSteel, 5)
                .inputItems(gearSmall, TungstenSteel, 3)
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(ELECTRIC_PUMP_IV, 2)
                .outputItems(HUGE_PLASMA_TURBINE)
                .EUt(VA[EV]).duration(200).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("huge_supercritical_steam_turbine_machine"))
                .inputItems(CASING_INCONEL_718_TURBINE)
                .inputItems(CustomTags.ZPM_CIRCUITS, 2)
                .inputItems(gear, Inconel718, 5)
                .inputItems(gearSmall, RhodiumPlatedPalladium, 3)
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(ELECTRIC_PUMP_LuV, 2)
                .outputItems(HUGE_SUPERCRITICAL_STEAM_TURBINE)
                .EUt(VA[EV]).duration(200).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_supercritical_steam_turbine_machine"),
                LARGE_SUPERCRITICAL_STEAM_TURBINE.asStack(), "PSP", "SAS", "CSC", 'S',
                new MaterialEntry(TagPrefix.gear, Inconel718), 'P', CustomTags.ZPM_CIRCUITS, 'A',
                GTMachines.HULL[GTValues.ZPM].asStack(), 'C',
                new MaterialEntry(TagPrefix.pipeLargeFluid, Inconel718));
        if (GTCEu.Mods.isCreateLoaded()) {
            ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("create_track"))
                    .inputItems(AllTags.AllItemTags.SLEEPERS.tag)
                    .inputItems(Ingredient.fromValues(Stream.of(new Ingredient.TagValue(Tags.Items.NUGGETS_IRON), new Ingredient.TagValue(TagUtil.createItemTag("nuggets/zinc")))))
                    .outputItems(AllBlocks.TRACK, 16)
                    .EUt(VH[ULV])
                    .duration(100)
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
        }




        BREWING_RECIPES.recipeBuilder(GTTCore.id("raw_beer"))
                .inputItems(dust, Wheat)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(RawBeer.getFluid(1000))
                .save(provider);
        FERMENTING_RECIPES.recipeBuilder(GTTCore.id("beer"))
                .inputFluids(RawBeer.getFluid(1000))
                .outputFluids(Beer.getFluid(1000))
                .save(provider);
        CANNER_RECIPES.recipeBuilder(GTTCore.id("beer_bottle"))
                .inputFluids(Beer.getFluid(250))
                .inputItems(Items.GLASS_BOTTLE)
                .outputItems(BEER_BOTTLE)
                .save(provider);






        for (Material material : andesiteAlloyIngredient) {
            VanillaRecipeHelper.addShapedRecipe(provider, String.format("mortar_grind_%s", material.getName()),
                    ChemicalHelper.get(dust, material), "X", "m", 'X', new MaterialEntry(rawOre, material));
        }
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("casing_stainless_evaporation"))
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
        VanillaRecipeHelper.addShapedRecipe(provider, GTTCore.id("steam_crushing_wheels"), STEAM_CRUSHING_WHEELS.asStack(2),
                "TTT", "UCU",
                "UMU", 'T', new MaterialEntry(gearSmall, WroughtIron), 'U', ChemicalHelper.get(gear, Steel),
                'C', CASING_INDUSTRIAL_STEAM.asStack(), 'M', ChemicalHelper.get(pipeLargeFluid, Bronze));
        ASSEMBLER_RECIPES.recipeBuilder("inconel_718_turbine_casing").EUt(16)
                .inputItems(GTBlocks.CASING_STEEL_TURBINE.asStack()).inputItems(plate, Inconel718, 6).inputItems(plate, TantalumCarbide, 3).circuitMeta(6)
                .outputItems(CASING_INCONEL_718_TURBINE.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50)
                .EUt(VA[HV])
                .addMaterialInfo(true).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("greenhouse"), GREENHOUSE.asStack(),
                "CGC",
                "RAR",
                "BPB",
                'B', Items.BONE_MEAL,
                'C', CustomTags.ULV_CIRCUITS,
                'G', ChemicalHelper.get(gear, Steel),
                'R', GTCraftingComponents.ROBOT_ARM.get(0),
                'A', CASING_STEEL_SOLID.asStack(),
                'P', ELECTRIC_PUMP_ULV
                );
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_greenhouse"), LARGE_GREENHOUSE.asStack(),
                "PCP",
                "RMR",
                "EWE",
                'M', GREENHOUSE.asStack(),
                'E', ROBOT_ARM.get(IV),
                'R', new MaterialEntry(gear, Platinum),
                'C', CustomTags.IV_CIRCUITS,
                'W', Items.BONE_MEAL,
                'P', new MaterialEntry(TagPrefix.pipeLargeFluid, TungstenSteel));
        VanillaRecipeHelper.addShapedRecipe(provider, true, "huge_chemical_reactor",
                HUGE_CHEMICAL_REACTOR.asStack(), "CRC", "CMP", "HHH", 'C', CustomTags.IV_CIRCUITS, 'R',
                ChemicalHelper.get(TagPrefix.rotor, TitaniumCarbide), 'P',
                ChemicalHelper.get(TagPrefix.pipeHugeFluid, GTMaterials.Polytetrafluoroethylene), 'M',
                GTItems.ELECTRIC_MOTOR_IV.asStack(), 'H', LARGE_CHEMICAL_REACTOR.asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("rocket_assembler"), ROCKET_ASSEMBLER.asStack(),
                "AGM",
                "MSC",
                "CCA",
                'A', ROBOT_ARM.get(HV),
                'G', new MaterialEntry(gear, BlueSteel),
                'M', MOTOR.get(HV),
                'C', CustomTags.HV_CIRCUITS,
                'S', CASING_STEEL_SOLID.asStack()
        );

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_steam_mixer"), LARGE_STEAM_MIXER.asStack(),
                "RGR",
                "CSC",
                "BBB",
                'R', ROTOR.get(ULV),
                'G', new MaterialEntry(gear, Steel),
                'C', CustomTags.ULV_CIRCUITS,
                'S', STEAM_MIXER.right().asStack(),
                'B', CASING_INDUSTRIAL_STEAM.asStack()
        );

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_steam_centrifuge"), LARGE_STEAM_CENTRIFUGE.asStack(),
                "GGG",
                "CSC",
                "CBC",
                'R', ROTOR.get(ULV),
                'G', new MaterialEntry(gear, Bronze),
                'C', CustomTags.ULV_CIRCUITS,
                'S', STEAM_CENTRIFUGE.right().asStack(),
                'B', CASING_INDUSTRIAL_STEAM.asStack()
        );

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_steam_centrifuge"), LARGE_STEAM_CENTRIFUGE.asStack(),
                "GGG",
                "CSC",
                "CBC",
                'G', new MaterialEntry(gear, Bronze),
                'C', CustomTags.ULV_CIRCUITS,
                'S', STEAM_CENTRIFUGE.right().asStack(),
                'B', CASING_INDUSTRIAL_STEAM.asStack()
        );

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_steam_ore_washer"), LARGE_STEAM_ORE_WASHER.asStack(),
                "PPP",
                "CSC",
                "GBG",
                'P', new MaterialEntry(pipeNormalFluid, Bronze),
                'G', new MaterialEntry(gear, BismuthBronze),
                'C', CustomTags.ULV_CIRCUITS,
                'S', Items.CAULDRON,
                'B', CASING_INDUSTRIAL_STEAM.asStack()
        );

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_steam_macerator"), LARGE_STEAM_MACERATOR.asStack(),
                "GGG",
                "CSC",
                "GBG",
                'G', new MaterialEntry(gear, Steel),
                'C', CustomTags.ULV_CIRCUITS,
                'S', STEAM_MACERATOR.right().asStack(),
                'B', CASING_INDUSTRIAL_STEAM.asStack()
        );

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("steam_distillation_tower"), STEAM_DISTILLATION_TOWER.asStack(),
                "CPC",
                "FSF",
                "PCP",
                'P', new MaterialEntry(pipeNormalFluid, Steel),
                'C', CustomTags.ULV_CIRCUITS,
                'S', CASING_STEEL_SEALED.asStack(),
                'F', FIREBOX_STEEL.asStack()
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "casing_steel_sealed",
                CASING_STEEL_SEALED.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "SPS", "PFP", "SPS",
                'P', new MaterialEntry(TagPrefix.plate, GTMaterials.Steel), 'F',
                new MaterialEntry(TagPrefix.frameGt, GTMaterials.Steel), 'S', new MaterialEntry(screw, Steel));

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_steam_forge_hammer"), LARGE_STEAM_FORGE_HAMMER.asStack(),
                "IPI",
                "CSC",
                "WAW",
                'I', Items.PISTON,
                'P', new MaterialEntry(pipeLargeFluid, Steel),
                'C', CustomTags.ULV_CIRCUITS,
                'S', STEAM_HAMMER.right().asStack(),
                'W', new MaterialEntry(block, WroughtIron),
                'A', CASING_BRONZE_BRICKS.asStack()
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("primitive_fermenter"), PRIMITIVE_FERMENTER.asStack(),
                "RhR",
                "SWS",
                "OdO",
                'W', CASING_WOOD_WALL.asStack(),
                'R', new MaterialEntry(ring, Copper),
                'S', new MaterialEntry(screw, Iron),
                'O', new MaterialEntry(rod, Copper)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("primitive_brewery"), PRIMITIVE_BREWERY.asStack(),
                "OhO",
                "SWS",
                "RdR",
                'W', CASING_WOOD_WALL.asStack(),
                'R', new MaterialEntry(ring, Copper),
                'S', new MaterialEntry(screw, Iron),
                'O', new MaterialEntry(rod, Copper)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_primitive_blast_furnace"), LARGE_PRIMITIVE_BLAST_FURNACE.asStack(),
                "PRP",
                "SSS",
                "PCP",
                'P', new MaterialEntry(plate, Steel),
                'R', new MaterialEntry(rotor, Steel),
                'S', PRIMITIVE_BLAST_FURNACE.asStack(),
                'C', CustomTags.ULV_CIRCUITS
        );

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("bedrock_drill"), BEDROCK_DRILL.asStack(),
                "CPC",
                "MSM",
                "DDD",
                'S', CASING_STEEL_SOLID.asStack(),
                'D', new MaterialEntry(toolHeadDrill, Ultimet),
                'M', MOTOR.get(EV),
                'C', CustomTags.EV_CIRCUITS,
                'P', PISTON.get(EV)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("large_gas_collector"), LARGE_GAS_COLLECTOR.asStack(),
                "MRM",
                "SCS",
                "CWC",
                'M', MOTOR.get(IV),
                'R', ROTOR.get(IV),
                'S', GAS_COLLECTOR[IV].asStack(),
                'C', CustomTags.IV_CIRCUITS,
                'W', new MaterialEntry(cableGtSingle, Platinum)
        );


        VanillaRecipeHelper.addShapedRecipe(provider, true, GTTCore.id("casing_industrial_steam"), CASING_INDUSTRIAL_STEAM.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft),
                "PhP", "PBP", "PwP", 'P', new MaterialEntry(TagPrefix.plate, GTMaterials.Brass), 'B',
                new MaterialEntry(frameGt, Bronze)
        );
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("casing_industrial_steam")).EUt(16).inputItems(plate, Brass, 6)
                .inputItems(frameGt, Bronze).circuitMeta(6)
                .outputItems(CASING_INDUSTRIAL_STEAM.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .duration(50).save(provider);




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




    }
}