package com.gtt.gttcore.mixin.gtm;

import com.gregtechceu.gtceu.api.capability.IMiner;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.trait.miner.MinerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/** Just a temporary fix*/
@Mixin(value = MinerLogic.class, remap = false)
public class Fix2 {
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/api/capability/IMiner;getRecipeType()Lcom/gregtechceu/gtceu/api/recipe/GTRecipeType;"))
    private GTRecipeType fix2(IMiner instance){
        return GTRecipeTypes.DUMMY_RECIPES;
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/common/machine/trait/miner/MinerLogic;getRLMachine()Lcom/gregtechceu/gtceu/api/capability/IMiner;"))
    private IMiner fix2(MinerLogic instance){
        return null;
    }
}
