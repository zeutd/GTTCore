package com.gtt.gttcore.common.data.recipes.datagen;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTItems;
import com.gtt.gttcore.common.data.GTTMaterials;
import com.gtt.gttcore.common.data.recipes.GTTRecipeTypes;
import com.simibubi.create.AllTags;
import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;

public class GTTCreateFillingRecipeGen extends FillingRecipeGen {
    public GTTCreateFillingRecipeGen(PackOutput output) {
        super(output, GTTCore.MOD_ID);
    }
    GeneratedRecipe

            BEER_BOTTLE = create("beer_bottle", b -> b.require(GTTMaterials.Beer.getFluid(), 250)
            .require(Items.GLASS_BOTTLE)
            .output(GTTItems.BEER_BOTTLE))
            ;
}
