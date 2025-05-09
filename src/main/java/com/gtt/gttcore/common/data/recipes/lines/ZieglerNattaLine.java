package com.gtt.gttcore.common.data.recipes.lines;

import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;

public class ZieglerNattaLine {
    public static void init(Consumer<FinishedRecipe> provider) {
        FERMENTING_RECIPES.recipeBuilder(GTTCore.id("cultivate_products"))
                .inputFluids(FermentedBiomass.getFluid(100))
                .outputFluids(CultivateProducts.getFluid(100))
                .duration(300)
                .EUt(VH[LV])
                .circuitMeta(1)
                .save(provider);
        DISTILLATION_RECIPES.recipeBuilder(GTTCore.id("cultivate_products_distillation"))
                .inputFluids(CultivateProducts.getFluid(1000))
                .outputFluids(Butanol.getFluid(250))
                .outputFluids(Ethanol.getFluid(500))
                .outputFluids(Acetone.getFluid(250))
                .duration(100)
                .EUt(VH[3])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("butanol_from_butene")
                .inputFluids(Butene.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .outputFluids(Butanol.getFluid(1000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("bromobutane")
                .inputItems(dust, SodiumBromide)
                .inputFluids(Butanol.getFluid(1000))
                .inputFluids(SulfuricAcid.getFluid(1000))
                .inputFluids(Bromine.getFluid(1000))
                .outputFluids(Bromobutane.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .outputItems(dust, SodiumSulfate)
                .duration(100)
                .EUt(VH[2])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("wet_dibutyl_magnesium")
                .inputFluids(Bromobutane.getFluid(1000))
                .inputFluids(Nitrogen.getFluid(1000))
                .inputItems(dust, Magnesium)
                .outputFluids(WetDibutylMagnesium.getFluid(1000))
                .duration(100)
                .EUt(VH[2])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("dibutyl_magnesium")
                .inputFluids(WetDibutylMagnesium.getFluid(1000))
                .inputItems(dust, Agar)
                .outputFluids(DibutylMagnesium.getFluid(1000))
                .duration(80)
                .EUt(VH[2])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("octanol")
                .inputFluids(Octane.getFluid(1000))
                .inputFluids(Hydrogen.getFluid(1000))
                .outputFluids(Octanol.getFluid(1000))
                .duration(100)
                .EUt(VH[2])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("octanol_magnesium")
                .inputFluids(Octanol.getFluid(1000))
                .inputFluids(DibutylMagnesium.getFluid(500))
                .outputItems(dust, OctanolMagnesium)
                .duration(100)
                .EUt(VH[2])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("phthaloyl_chlorine")
                .inputFluids(PhosphorusTrichloride.getFluid(1000))
                .inputFluids(PhthalicAcid.getFluid(1000))
                .outputFluids(PhthaloylChlorine.getFluid(1000))
                .duration(100)
                .EUt(VH[3])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("PhosphorusTrichloride")
                .inputFluids(Chlorine.getFluid(1000))
                .inputItems(dust, Phosphorus, 2)
                .outputFluids(PhosphorusTrichloride.getFluid(1000))
                .duration(100)
                .EUt(VH[3])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("microcrystal_magnesium_chloride_dust")
                .inputFluids(PhthaloylChlorine.getFluid(1000))
                .inputItems(dust, OctanolMagnesium)
                .outputFluids(DiisooctylPhthalate.getFluid(500))
                .outputItems(dust, MicrocrystalMagnesiumChloride)
                .duration(100)
                .EUt(VH[3])
                .save(provider);
        FORGE_HAMMER_RECIPES.recipeBuilder("magnesium_chloride_dust_from_microcrystal_magnesium_chloride_dust")
                .inputItems(dust, MicrocrystalMagnesiumChloride)
                .outputItems(dust, MagnesiumChloride)
                .duration(20)
                .EUt(VH[1])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("ziegler_natta")
                .inputItems(dust, MicrocrystalMagnesiumChloride)
                .inputFluids(TitaniumTetrachloride.getFluid(1000))
                .inputFluids(DiisooctylPhthalate.getFluid(250))
                .outputItems(dust, ZieglerNattaCatalyst)
                .duration(100)
                .EUt(VH[3])
                .save(provider);
    }
}
