package com.gtt.gttcore.common.data.recipes;

import com.simibubi.create.AllFluids;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.ULV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Milk;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Water;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class MixerRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("tea")
                .duration(100)
                .EUt(VA[ULV])
                .inputItems(ItemTags.LEAVES)
                .inputFluids(Water.getFluid(250))
                .inputFluids(Milk.getFluid(250))
                .outputFluids(new FluidStack(AllFluids.TEA.getSource(), 500))
                .save(provider);
    }
}
