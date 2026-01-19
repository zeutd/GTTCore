package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.GTCraftingComponents;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gtt.gttcore.GTTCore;
import earth.terrarium.adastra.common.registry.ModItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.BlockTags;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.PUMP;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;

public class RocketRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("steel_fuel_tank"))
                .EUt(VA[MV])
                .inputItems(plate, Steel, 16)
                .inputItems(pipeSmallFluid, Steel, 2)
                .outputItems(ModItems.STEEL_TANK)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("desh_fuel_tank"))
                .EUt(VA[MV])
                .inputItems(plate, Desh, 16)
                .inputItems(pipeSmallFluid, Desh, 2)
                .outputItems(ModItems.DESH_TANK)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("ostrum_fuel_tank"))
                .EUt(VA[HV])
                .inputItems(plate, Ostrum, 16)
                .inputItems(pipeSmallFluid, Ostrum, 2)
                .outputItems(ModItems.OSTRUM_TANK)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("calorite_fuel_tank"))
                .EUt(VA[EV])
                .inputItems(plate, Calorite, 16)
                .inputItems(pipeSmallFluid, Calorite, 2)
                .outputItems(ModItems.CALORITE_TANK)
                .save(provider);
        ROCKET_ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("rocket_t1"))
                .EUt(VA[HV])
                .inputItems(ModItems.ROCKET_NOSE_CONE)
                .inputItems(plate, Steel, 48)
                .inputItems(frameGt, Steel, 2)
                .inputItems(pipeSmallFluid, Steel, 2)
                .inputItems(ModItems.STEEL_TANK, 2)
                .inputItems(ModItems.ROCKET_FIN, 2)
                .inputItems(ModItems.STEEL_ENGINE, 1)
                .inputItems(CustomTags.EV_CIRCUITS, 4)
                .outputItems(ModItems.TIER_1_ROCKET)
                .save(provider);
        ROCKET_ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("rocket_t2"))
                .EUt(VA[EV])
                .inputItems(ModItems.ROCKET_NOSE_CONE)
                .inputItems(plate, Desh, 48)
                .inputItems(frameGt, Steel, 2)
                .inputItems(pipeSmallFluid, Desh, 2)
                .inputItems(ModItems.DESH_TANK, 2)
                .inputItems(ModItems.ROCKET_FIN, 2)
                .inputItems(ModItems.DESH_ENGINE, 1)
                .inputItems(CustomTags.IV_CIRCUITS, 4)
                .outputItems(ModItems.TIER_2_ROCKET)
                .save(provider);
        ROCKET_ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("rocket_t3"))
                .EUt(VA[IV])
                .inputItems(ModItems.ROCKET_NOSE_CONE)
                .inputItems(plate, Ostrum, 48)
                .inputItems(frameGt, Steel, 2)
                .inputItems(pipeSmallFluid, Ostrum, 2)
                .inputItems(ModItems.OSTRUM_TANK, 2)
                .inputItems(ModItems.ROCKET_FIN, 2)
                .inputItems(ModItems.OSTRUM_ENGINE, 1)
                .inputItems(ModItems.DESH_ENGINE, 2)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .outputItems(ModItems.TIER_3_ROCKET)
                .save(provider);
        ROCKET_ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("rocket_t4"))
                .EUt(VA[LuV])
                .inputItems(ModItems.ROCKET_NOSE_CONE)
                .inputItems(plate, Calorite, 48)
                .inputItems(frameGt, TungstenSteel, 3)
                .inputItems(pipeSmallFluid, Calorite, 2)
                .inputItems(ModItems.CALORITE_TANK, 2)
                .inputItems(ModItems.ROCKET_FIN, 2)
                .inputItems(ModItems.CALORITE_ENGINE, 3)
                .inputItems(ModItems.OSTRUM_ENGINE, 2)
                .inputItems(CustomTags.ZPM_CIRCUITS, 4)
                .outputItems(ModItems.TIER_4_ROCKET)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("rocket_nose_cone"))
                .EUt(VA[HV])
                .inputItems(plate, Steel, 12)
                .inputItems(gemFlawless, Ruby)
                .outputItems(ModItems.ROCKET_NOSE_CONE)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("steel_engine"))
                .EUt(VA[HV])
                .inputItems(plate, Steel, 12)
                .inputItems(PUMP.get(HV))
                .inputItems(GTCraftingComponents.ROTOR.get(HV))
                .inputItems(GTCraftingComponents.MOTOR.get(HV))
                .outputItems(ModItems.STEEL_ENGINE)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("desh_engine"))
                .EUt(VA[HV])
                .inputItems(plate, Desh, 12)
                .inputItems(PUMP.get(EV))
                .inputItems(GTCraftingComponents.ROTOR.get(EV))
                .inputItems(GTCraftingComponents.MOTOR.get(EV))
                .outputItems(ModItems.DESH_ENGINE)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("ostrum_engine"))
                .EUt(VA[EV])
                .inputItems(plate, Ostrum, 12)
                .inputItems(PUMP.get(IV))
                .inputItems(GTCraftingComponents.ROTOR.get(IV))
                .inputItems(GTCraftingComponents.MOTOR.get(IV))
                .outputItems(ModItems.OSTRUM_ENGINE)
                .save(provider);
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("calorite_engine"))
                .EUt(VA[IV])
                .inputItems(plate, Calorite, 12)
                .inputItems(PUMP.get(LuV))
                .inputItems(GTCraftingComponents.ROTOR.get(LuV))
                .inputItems(GTCraftingComponents.MOTOR.get(LuV))
                .outputItems(ModItems.CALORITE_ENGINE)
                .save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, false, GTTCore.id("space_suit"),
            ModItems.SPACE_SUIT.get().getDefaultInstance(),
            "SWS",
            "SPS",
            "CRC",
                'S', new MaterialEntry(plate, Steel),
                'W', BlockTags.WOOL,
                'P', PUMP.get(HV),
                'C', GTItems.FLUID_CELL_LARGE_STEEL,
                'R', new MaterialEntry(rotor, StainlessSteel)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, false, GTTCore.id("netherite_space_suit"),
                ModItems.NETHERITE_SPACE_SUIT.get().getDefaultInstance(),
                "S S",
                "SPS",
                "CRC",
                'S', new MaterialEntry(plate, Netherite),
                'P', PUMP.get(EV),
                'C', GTItems.FLUID_CELL_LARGE_STEEL,
                'R', new MaterialEntry(rotor, StainlessSteel)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, false, GTTCore.id("jet_suit"),
                ModItems.JET_SUIT.get().getDefaultInstance(),
                "AWA",
                "PSP",
                "CRC",
                'A', new MaterialEntry(plate, Calorite),
                'S', ModItems.NETHERITE_SPACE_SUIT,
                'W', ModItems.OSTRUM_ENGINE,
                'P', PUMP.get(IV),
                'C', GTItems.LAPOTRON_CRYSTAL,
                'R', new MaterialEntry(rotor, StainlessSteel)
        );
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("zip_gun"))
                .EUt(VA[MV])
                .inputFluids(Polyethylene.getFluid(144))
                .inputItems(pipeSmallFluid, Ostrum, 2)
                .inputItems(GTItems.FLUID_CELL_LARGE_STAINLESS_STEEL)
                .outputItems(ModItems.ZIP_GUN)
                .save(provider);
    }
}
