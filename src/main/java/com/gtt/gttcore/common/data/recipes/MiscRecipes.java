package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gtt.gttcore.common.data.GTTBlocks;
import com.gtt.gttcore.common.data.GTTItems;
import com.gtt.gttcore.common.data.GTTMultiblockMachines;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTBlocks.*;
import static com.gtt.gttcore.common.data.GTTItems.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.GTTMultiblockMachines.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;

public class MiscRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        registerMachineRecipes(provider);
        CHEMICAL_RECIPES.recipeBuilder("ultra_high_molecular_weight_polyethylene")
                .inputFluids(Ethylene.getFluid(144))
                .notConsumable(dust, ZieglerNattaCatalyst)
                .outputFluids(UltraHighMolecularWeightPolyethylene.getFluid(216))
                .duration(100)
                .EUt(V[2])
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
        VanillaRecipeHelper.addShapedRecipe(provider, true, "casing_zirconium_pipe",
                GTTBlocks.CASING_ZIRCONIUM_PIPE.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft), "PIP", "IFI",
                "PIP", 'P', new UnificationEntry(TagPrefix.plate, Zirconium), 'F',
                new UnificationEntry(TagPrefix.frameGt, GTMaterials.Zirconium), 'I',
                new UnificationEntry(TagPrefix.pipeNormalFluid, GTMaterials.Zirconium));
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
                .EUt(-V[ZPM])
                .save(provider);
    }
    public static void registerMachineRecipes(Consumer<FinishedRecipe> provider){
        VanillaRecipeHelper.addShapedRecipe(provider, true, "greenhouse_machine",
                GREENHOUSE.asStack(),
                "AWA",
                "GSG",
                "WAW",
                'A', CustomTags.LV_CIRCUITS,
                'W', new UnificationEntry(cableGtSingle, Copper),
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
        VanillaRecipeHelper.addShapedRecipe(provider, true, "large_supercritical_steam_turbine_machine",
                LARGE_SUPERCRITICAL_STEAM_TURBINE.asStack(), "PSP", "SAS", "CSC", 'S',
                new UnificationEntry(TagPrefix.gear, NaquadahAlloy), 'P', CustomTags.ZPM_CIRCUITS, 'A',
                GTMachines.HULL[GTValues.ZPM].asStack(), 'C',
                new UnificationEntry(TagPrefix.pipeLargeFluid, GTMaterials.NaquadahAlloy));
        ASSEMBLER_RECIPES.recipeBuilder("fission_reactor_machine")
                .inputItems(CASING_LEAD_RADIATION_PROOF)
                .inputItems(CustomTags.LuV_CIRCUITS, 5)
                .inputItems(NEUTRON_REFLECTOR, 5)
                .inputItems(CustomTags.IV_CIRCUITS, 6)
                .inputItems(ELECTRIC_PUMP_IV, 10)
                .inputItems(ELECTRIC_PISTON_IV, 10)
                .outputItems(FISSION_REACTOR)
                .EUt(VA[IV]).duration(200).save(provider);
    }
}