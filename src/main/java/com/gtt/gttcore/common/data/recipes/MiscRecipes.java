package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTBlocks;
import com.gtt.gttcore.common.data.GTTMaterials;
import com.gtt.gttcore.data.recipe.GTTTags;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTBlocks.*;
import static com.gtt.gttcore.common.data.GTTItems.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.GTTMultiMachines.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;
import static com.simibubi.create.AllItems.WHEAT_FLOUR;
import static com.simibubi.create.AllItems.ZINC_NUGGET;
import static net.minecraft.world.item.Items.DIRT;
import static net.minecraft.world.item.Items.DIRT_PATH;

public class MiscRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        registerMachineRecipes(provider);
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
        ASSEMBLER_RECIPES.recipeBuilder("huge_supercritical_steam_turbine_machine")
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
                'A', new MaterialEntry(block, Andesite),
                'B', GTTTags.andesiteAlloyable
        );

        for (Material material : andesiteAlloyIngredient) {
            VanillaRecipeHelper.addShapedRecipe(provider, String.format("mortar_grind_%s", material.getName()),
                    ChemicalHelper.get(dust, material), "X", "m", 'X', new MaterialEntry(rawOre, material));
        }





        SIFTER_RECIPES.recipeBuilder(GTTCore.id("dirt_from_coarse_dirt"))
                .EUt(ULV)
                .inputItems(DIRT)
                .duration(20 * 10)
                .outputItems(DIRT_PATH)
                .save(provider);
    }
}