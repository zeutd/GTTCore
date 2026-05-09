package com.gtt.gttcore.common.data.recipes.datagen;

import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTItems;
import com.gtt.gttcore.common.data.GTTMaterials;
import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

@Deprecated
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
