package com.gtt.gttcore.common.data.recipes.datagen;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.common.data.GTRecipes;
import com.gregtechceu.gtceu.data.recipe.GTCraftingComponents;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.simibubi.create.api.data.recipe.PressingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;

public class GTTCreatePressingRecipeGen extends PressingRecipeGen {
    public GTTCreatePressingRecipeGen(PackOutput output) {
        super(output, GTTCore.MOD_ID);
    }

    {
        GTTCore.LOGGER.info(GTTRecipeTypes.toReRegisterCreatePressing);
        for (GTRecipeBuilder recipeBuilder : GTTRecipeTypes.toReRegisterCreatePressing) {
            create(GTTCore.id(recipeBuilder.id.getPath()), b -> {
                if (recipeBuilder.input.containsKey(ItemRecipeCapability.CAP))
                    recipeBuilder.input.get(ItemRecipeCapability.CAP).forEach(item -> b.require((Ingredient) item.content));
                if (recipeBuilder.output.containsKey(ItemRecipeCapability.CAP))
                    recipeBuilder.output.get(ItemRecipeCapability.CAP).forEach(item -> Arrays.stream(((SizedIngredient) item.content).getItems()).toList().forEach(b::output));
                return b;
            });
        }
    }
}
