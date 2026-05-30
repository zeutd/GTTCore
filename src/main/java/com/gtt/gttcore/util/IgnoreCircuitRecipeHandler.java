package com.gtt.gttcore.util;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntCircuitIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.IntStream;

public class IgnoreCircuitRecipeHandler implements IRecipeHandler<Ingredient> {

    public static final IgnoreCircuitRecipeHandler INSTANCE = new IgnoreCircuitRecipeHandler();
    private static final List<Object> allCircuits = IntStream.range(1, IntCircuitIngredient.CIRCUIT_MAX).mapToObj(i -> (Object) IntCircuitIngredient.of(i).getItems()[0]).toList();

    @Override
    public @Nullable List<Ingredient> handleRecipeInner(IO io, GTRecipe recipe, List<Ingredient> left, boolean simulate) {
        if (io == IO.IN) {
            left.removeIf(ingredient -> ingredient instanceof IntCircuitIngredient || (ingredient instanceof SizedIngredient sized && sized.getInner() instanceof IntCircuitIngredient));
        }
        return left.isEmpty() ? null : left;
    }

    @Override
    public @NotNull List<Object> getContents() {
        return allCircuits;
    }

    @Override
    public double getTotalContentAmount() {
        return 32;
    }

    @Override
    public RecipeCapability<Ingredient> getCapability() {
        return ItemRecipeCapability.CAP;
    }
}
