package com.gtt.gttcore.common.data.recipes;

import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.EV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CENTRIFUGE_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DISTILLATION_RECIPES;
import static com.gtt.gttcore.common.data.GTTMaterials.LiquidVenusAir;
import static com.gtt.gttcore.common.data.GTTMaterials.VenusAir;

public class SeparationRecipes {
    public void init(Consumer<FinishedRecipe> provider){
        CENTRIFUGE_RECIPES.recipeBuilder(GTTCore.id("venus_air_separation")).duration(1600).EUt(VA[EV])
                .inputFluids(VenusAir.getFluid(10000))
                .outputFluids(SulfurTrioxide.getFluid(7800))
                .outputFluids(Argon.getFluid(1000))
                .save(provider);
        DISTILLATION_RECIPES.recipeBuilder(GTTCore.id("distill_liquid_venus_air"))
                .inputFluids(LiquidVenusAir.getFluid(100000))
                .outputFluids(SulfurTrioxide.getFluid(78000))
                .outputFluids(Argon.getFluid(10000))
                .outputFluids(Ethenone.getFluid(5000))
                .outputFluids(Tetrafluoroethylene.getFluid(5000))
                .chancedOutput(dust, Sulfur, 2250, 0)
                .disableDistilleryRecipes(true)
                .duration(2000).EUt(VA[EV]).save(provider);
    }
}
