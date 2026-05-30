package com.gtt.gttcore.util;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntCircuitIngredient;
import com.gregtechceu.gtceu.utils.DummyRecipeUtils;
import com.gtt.gttcore.GTTCore;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class RecipeUtil {

    public static final DummyRecipeUtils.DummyItemHandler itemHandlerWithAllCircuits = new DummyRecipeUtils.DummyItemHandler(IO.IN, NonNullList.of(ItemStack.EMPTY, IntStream.range(1, IntCircuitIngredient.CIRCUIT_MAX).mapToObj(i -> IntCircuitIngredient.of(i).getItems()[0]).toArray(ItemStack[]::new)));

    public static <T extends ProcessingRecipe<?>> T convertGregTechToCreate(GTRecipe gtRecipe, ProcessingRecipeBuilder.ProcessingRecipeFactory<T> factory) {
        ProcessingRecipeBuilder<T> processingRecipeBuilder = new ProcessingRecipeBuilder<>(factory, GTTCore.id("converted_" + gtRecipe.getId().getPath()));
        if (gtRecipe.inputs.containsKey(ItemRecipeCapability.CAP))
            gtRecipe.getInputContents(ItemRecipeCapability.CAP)
                    .stream()
                    .filter(content -> content.chance != 0)
                    .forEach(content -> processingRecipeBuilder.require((Ingredient) content.content));
        if (gtRecipe.inputs.containsKey(FluidRecipeCapability.CAP))
            gtRecipe.getInputContents(FluidRecipeCapability.CAP)
                    .forEach(content ->
                            processingRecipeBuilder.require(com.simibubi.create.foundation.fluid.FluidIngredient.fromFluidStack(((com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient) content.content).getStacks()[0])));
        if (gtRecipe.outputs.containsKey(ItemRecipeCapability.CAP))
            gtRecipe.getOutputContents(ItemRecipeCapability.CAP)
                    .forEach(content -> processingRecipeBuilder.output((float) content.chance / content.maxChance, ((Ingredient) content.content).getItems()[0]));
        if (gtRecipe.outputs.containsKey(FluidRecipeCapability.CAP))
            gtRecipe.getOutputContents(FluidRecipeCapability.CAP).forEach(content -> processingRecipeBuilder.output(((com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient) content.content).getStacks()[0]));
        return processingRecipeBuilder.build();
    }

    public static <T extends ProcessingRecipe<?>> T convertGregTechToCreateOnlyFirstOutput(GTRecipe gtRecipe, ProcessingRecipeBuilder.ProcessingRecipeFactory<T> factory) {
        ProcessingRecipeBuilder<T> processingRecipeBuilder = new ProcessingRecipeBuilder<>(factory, GTTCore.id("converted_" + gtRecipe.getId().getPath()));
        if (gtRecipe.inputs.containsKey(ItemRecipeCapability.CAP))
            gtRecipe.getInputContents(ItemRecipeCapability.CAP)
                    .stream()
                    .filter(content -> content.chance != 0)
                    .forEach(content -> processingRecipeBuilder.require((Ingredient) content.content));
        if (gtRecipe.inputs.containsKey(FluidRecipeCapability.CAP))
            gtRecipe.getInputContents(FluidRecipeCapability.CAP)
                    .forEach(content ->
                            processingRecipeBuilder.require(com.simibubi.create.foundation.fluid.FluidIngredient.fromFluidStack(((com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient) content.content).getStacks()[0])));
        if (gtRecipe.outputs.containsKey(ItemRecipeCapability.CAP))
            gtRecipe.getOutputContents(ItemRecipeCapability.CAP)
                    .stream()
                    .findFirst()
                    .map(content -> processingRecipeBuilder.output((float) content.chance / content.maxChance, ((Ingredient) content.content).getItems()[0]));
        if (gtRecipe.outputs.containsKey(FluidRecipeCapability.CAP))
            gtRecipe.getOutputContents(FluidRecipeCapability.CAP).stream()
                    .findFirst()
                    .map(content -> processingRecipeBuilder.output(((com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient) content.content).getStacks()[0]));
        return processingRecipeBuilder.build();
    }

    public static void consumeAllRecipes(Consumer<Recipe<?>> consumer) {
        Objects.requireNonNull(Minecraft.getInstance()
                        .getConnection())
                .getRecipeManager()
                .getRecipes()
                .forEach(consumer);
    }
}
