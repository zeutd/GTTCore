package com.gtt.gttcore.common.data.recipes.lines;

import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.HV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;

public class MolybdeniteSmokeDustLine {
    public static void init(Consumer<FinishedRecipe> provider) {
        BLAST_RECIPES.recipeBuilder(GTTCore.id("molybdenum_smoke_dust"))
                .inputItems(dust, Molybdenite)
                .chancedOutput(dust, MolybdenumSmokeDust, 1100, 0)
                .outputItems(dust, Molybdenum)
                .duration(400)
                .EUt(VA[HV])
                .blastFurnaceTemp(823)
                .save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder(GTTCore.id("molybdenum_smoke_dust_separation"))
                .inputItems(dust, MolybdenumSmokeDust)
                .chancedOutput(dust, Molybdenum, 5000, 0)
                .chancedOutput(dust, Chalcocite, 2000, 0)
                .chancedOutput(dust, GermaniumDioxide, 1100, 0)
                .chancedOutput(dust, RheniumOxide, 300, 0)
                .duration(400)
                .EUt(VA[HV])
                .blastFurnaceTemp(823)
                .save(provider);
        CHEMICAL_BATH_RECIPES.recipeBuilder(GTTCore.id("rhenium_solution"))
                .inputItems(dust, RheniumOxide, 9)
                .inputFluids(Water.getFluid(2000))
                .outputFluids(RheniumSolution.getFluid(2000))
                .duration(400)
                .EUt(VA[HV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("ammonium_perrhenate"))
                .inputFluids(RheniumSolution.getFluid(1000))
                .inputFluids(Ammonia.getFluid(1000))
                .outputItems(dust, AmmoniumPerrhenate, 10)
                .duration(200)
                .EUt(VA[HV])
                .save(provider);
        BLAST_RECIPES.recipeBuilder(GTTCore.id("rhenium_dust"))
                .inputFluids(Hydrogen.getFluid(14000))
                .inputItems(dust, AmmoniumPerrhenate, 20)
                .outputItems(dust, Rhenium, 2)
                .outputFluids(Water.getFluid(8000))
                .outputFluids(Ammonia.getFluid(2000))
                .duration(200)
                .EUt(VA[HV])
                .save(provider);
        BLAST_RECIPES.recipeBuilder(GTTCore.id("germanium_dust"))
                .inputFluids(Hydrogen.getFluid(4000))
                .inputItems(dust, GermaniumDioxide, 3)
                .inputItems(dust, Germanium, 1)
                .outputFluids(Water.getFluid(2000))
                .duration(200)
                .EUt(VA[HV])
                .save(provider);
    }
}
