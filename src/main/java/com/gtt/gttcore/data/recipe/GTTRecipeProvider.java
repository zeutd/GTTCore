package com.gtt.gttcore.data.recipe;

import com.gregtechceu.gtceu.common.data.GTRecipes;
import com.gregtechceu.gtceu.data.recipe.GTCraftingComponents;
import com.gtt.gttcore.common.data.recipes.datagen.GTTCreateFillingRecipeGen;
import com.gtt.gttcore.common.data.recipes.datagen.GTTCreateMillingRecipeGen;
import com.gtt.gttcore.common.data.recipes.datagen.GTTCreateMixingRecipeGen;
import com.gtt.gttcore.common.data.recipes.datagen.GTTCreatePressingRecipeGen;
import com.gtt.gttcore.common.data.recipes.remove.CreateRecipeRemoval;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider;
import com.simibubi.create.api.data.recipe.ProcessingRecipeGen;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GTTRecipeProvider extends RecipeProvider {

    static final List<BaseRecipeProvider> GENERATORS = new ArrayList<>();

    public GTTRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

    }

    public static void registerAllProcessing(DataGenerator gen, PackOutput output) {
        GTCraftingComponents.init();
        GTRecipes.recipeAddition(ignored -> {});
//        GENERATORS.add(new GTTCreatePressingRecipeGen(output));
//        GENERATORS.add(new GTTCreateMixingRecipeGen(output));
//        GENERATORS.add(new GTTCreateFillingRecipeGen(output));
//        GENERATORS.add(new GTTCreateMillingRecipeGen(output));
        gen.addProvider(true, new DataProvider() {

            @Override
            public String getName() {
                return "GTT's Create Processing Recipes";
            }

            @Override
            public CompletableFuture<?> run(CachedOutput dc) {
                return CompletableFuture.allOf(GENERATORS.stream()
                        .map(gen -> gen.run(dc))
                        .toArray(CompletableFuture[]::new));
            }
        });
    }
}
