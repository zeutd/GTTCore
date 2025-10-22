package com.gtt.gttcore.common.data.recipes.datagen;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;

public class GTTCreateMixingRecipeGen extends MixingRecipeGen {
    public GTTCreateMixingRecipeGen(PackOutput output) {
        super(output, GTTCore.MOD_ID);
    }

    {
        for (GTRecipeBuilder recipeBuilder : GTTRecipeTypes.toReRegisterCreateMixing) {
            create(GTTCore.id("gregtech_" + recipeBuilder.id.getPath()), b -> {
                if (recipeBuilder.input.containsKey(ItemRecipeCapability.CAP))
                    recipeBuilder.input.get(ItemRecipeCapability.CAP).stream().filter(content -> content.chance != 0).forEach(content -> b.require((Ingredient) content.content));
                if (recipeBuilder.input.containsKey(FluidRecipeCapability.CAP))
                    recipeBuilder.input.get(FluidRecipeCapability.CAP).forEach(content -> Arrays.stream(((FluidIngredient) content.content).getStacks()).toList().forEach(fluidStack -> b.require(com.simibubi.create.foundation.fluid.FluidIngredient.fromFluidStack(fluidStack))));
                if (recipeBuilder.output.containsKey(ItemRecipeCapability.CAP))
                    recipeBuilder.output.get(ItemRecipeCapability.CAP).forEach(content -> Arrays.stream(((SizedIngredient) content.content).getItems()).toList().forEach(i -> b.output((float) content.chance / content.maxChance, i)));
                if (recipeBuilder.output.containsKey(FluidRecipeCapability.CAP))
                    recipeBuilder.output.get(FluidRecipeCapability.CAP).forEach(content -> Arrays.stream(((FluidIngredient) content.content).getStacks()).toList().forEach(b::output));
                return b;
            });
        }
    }
}
