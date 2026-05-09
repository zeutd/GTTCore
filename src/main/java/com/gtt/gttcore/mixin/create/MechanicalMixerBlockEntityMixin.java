package com.gtt.gttcore.mixin.create;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.utils.DummyRecipeUtils;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import net.minecraft.core.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import static com.gregtechceu.gtceu.api.recipe.RecipeHelper.matchRecipe;

@Mixin(MechanicalMixerBlockEntity.class)
public abstract class MechanicalMixerBlockEntityMixin {

    @Unique
    public GTRecipe gTTCore$lastGTRecipe;

    @Unique
    protected void gTTCore$handleSearchingRecipes(Iterator<GTRecipe> matches) {
        //MechanicalMixerBlockEntity self = (MechanicalMixerBlockEntity)(Object) this;
        while (matches.hasNext()) {
            GTRecipe match = matches.next();

            // If a new recipe was found, cache found recipe.
            if (checkMatchedRecipeAvailable(match))
                return;

            if (!matchRecipe(new DummyRecipeUtils.DummyRecipeCapabilityHolder(new DummyRecipeUtils.DummyItemHandler(IO.IN, NonNullList.of(getBasin()))), match).isSuccess()) {
                continue;
            }

            // cache matching recipes.
            if (lastFailedMatches == null) {
                lastFailedMatches = new ArrayList<>();
            }
            lastFailedMatches.add(match);
        }
    }
}
