package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gtt.gttcore.common.data.GTTBlocks;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.*;

public class MachineRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        registerFissionRecipes(provider);
    }

    private static void registerFissionRecipes(Consumer<FinishedRecipe> provider) {
        FISSION_RECIPES.recipeBuilder("fission_uranium")
                .inputItems(rod, Uranium238, 16)
                .outputItems(rod, DepletedUranium238, 16)
                .EUt(-V[ZPM])
                .duration(100)
                .save(provider);
        FISSION_RECIPES.recipeBuilder("fission_plutonium")
                .inputItems(rod, Plutonium239, 16)
                .outputItems(rod, DepletedPlutonium239, 16)
                .EUt(-V[ZPM])
                .duration(100)
                .save(provider);
        FISSION_RECIPES.recipeBuilder("fission_thorium")
                .inputItems(rod, Thorium, 16)
                .outputItems(rod, DepletedThorium, 16)
                .EUt(-V[ZPM])
                .duration(100)
                .save(provider);
    }
}