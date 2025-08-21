package com.gtt.gttcore.common.data.recipes.datagen;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.simibubi.create.api.data.recipe.MillingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;

public class GTTCreateMillingRecipeGen extends MillingRecipeGen {
    public GTTCreateMillingRecipeGen(PackOutput output) {
        super(output, GTTCore.MOD_ID);
    }

    {
        for (GTRecipeBuilder recipeBuilder : GTTRecipeTypes.toReRegisterCreateMilling) {
            create(GTTCore.id(recipeBuilder.id.getPath()), b -> {
                if (recipeBuilder.input.containsKey(ItemRecipeCapability.CAP))
                    recipeBuilder.input.get(ItemRecipeCapability.CAP).stream().filter(content -> content.chance != 0).forEach(content -> b.require((Ingredient) content.content));
                if (recipeBuilder.output.containsKey(ItemRecipeCapability.CAP))
                    recipeBuilder.output.get(ItemRecipeCapability.CAP).forEach(content -> Arrays.stream(((SizedIngredient) content.content).getItems()).toList().forEach(b::output));
                return b;
            });
        }
    }
}
