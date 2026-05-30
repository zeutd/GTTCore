package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import net.minecraft.Util;

import java.util.function.Function;

public class GTTRecipeModifiers {
    public static final Function<Integer, RecipeModifier> baseSpeedMutiplier = Util.memoize(multiplier -> (machine, recipe) -> ModifierFunction.builder()
            .durationMultiplier(multiplier)
            .build());

    public static final Function<Integer, RecipeModifier> baseParallel = Util.memoize(maxParallel -> (machine, recipe) -> {
        int parallels = ParallelLogic.getParallelAmount(machine, recipe, maxParallel);
        return ModifierFunction.builder()
                .modifyAllContents(ContentModifier.multiplier(parallels))
                .eutMultiplier(parallels)
                .parallels(parallels)
                .build();
    });

    //public static final RecipeModifier removeCircuit = (machine, recipe) -> recipe1 -> recipe1.inputs.get(ItemRecipeCapability.CAP).removeIf()
}
