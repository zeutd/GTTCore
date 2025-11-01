package com.gtt.gttcore.mixin;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.steam.SteamMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeLogic.class)
public class RecipeLogicMixin {
    @Shadow(remap = false)
    protected int duration;

    @Shadow(remap = false) @Final public IRecipeLogicMachine machine;

    @Inject(method = "setupRecipe", at = @At(value = "FIELD", target = "Lcom/gregtechceu/gtceu/api/machine/trait/RecipeLogic;duration:I", shift = At.Shift.AFTER), remap = false)
    private void setupRecipeMixin(GTRecipe recipe, CallbackInfo ci){
        if (machine instanceof SteamMachine || machine instanceof SteamParallelMultiblockMachine) duration *= 2;
    }
}
