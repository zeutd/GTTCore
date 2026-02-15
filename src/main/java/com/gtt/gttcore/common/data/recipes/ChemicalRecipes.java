package com.gtt.gttcore.common.data.recipes;

import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.GTValues.L;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CHEMICAL_RECIPES;
import static com.gtt.gttcore.common.data.GTTMaterials.*;

public class ChemicalRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("phenolic_resin"))
                .duration(100)
                .EUt(VA[ULV])
                .inputFluids(Phenol.getFluid(L))
                .inputFluids(Formaldehyde.getFluid(L * 2))
                .outputFluids(PhenolicResin.getFluid(L * 3))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("ultra_high_molecular_weight_polyethylene"))
                .inputFluids(Ethylene.getFluid(144))
                .notConsumable(dust, ZieglerNattaCatalyst)
                .outputFluids(UltraHighMolecularWeightPolyethylene.getFluid(216))
                .duration(100)
                .EUt(VA[2])
                .save(provider);
    }
}
