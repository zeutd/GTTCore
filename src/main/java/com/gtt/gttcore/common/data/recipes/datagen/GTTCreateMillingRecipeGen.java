package com.gtt.gttcore.common.data.recipes.datagen;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.common.CommonProxy;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.unification.material.MaterialRegistryManager;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTMaterials;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.gtt.gttcore.common.data.recipes.GTTRecipes;
import com.simibubi.create.api.data.recipe.MillingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.NO_SMASHING;

public class GTTCreateMillingRecipeGen extends MillingRecipeGen {
    public GTTCreateMillingRecipeGen(PackOutput output) {
        super(output, GTTCore.MOD_ID);
    }

    {
        for (GTRecipeBuilder recipeBuilder : GTTRecipeTypes.toReRegisterCreateMilling) {
            create(GTTCore.id("gregtech_" + recipeBuilder.id.getPath()), b -> {
                if (recipeBuilder.input.containsKey(ItemRecipeCapability.CAP))
                    recipeBuilder.input.get(ItemRecipeCapability.CAP).stream().filter(content -> content.chance != 0).forEach(content -> b.require((Ingredient) content.content));
                if (recipeBuilder.output.containsKey(ItemRecipeCapability.CAP))
                    recipeBuilder.output.get(ItemRecipeCapability.CAP).forEach(content -> Arrays.stream(((SizedIngredient) content.content).getItems()).toList().forEach(i -> b.output((float) content.chance / content.maxChance, i)));
                return b;
            });
        }
    }
}
