package com.gtt.gttcore.common.data.recipes;

import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.GTValues.L;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.BLAST_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CHEMICAL_RECIPES;
import static com.gtt.gttcore.common.data.GTTMaterials.*;

public class ChemicalRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        BLAST_RECIPES.recipeBuilder(GTTCore.id("niobium_pentoxide"))
                .duration(400)
                .EUt(VA[EV])
                .inputFluids(Oxygen.getFluid(5000))
                .inputItems(dust, Niobium, 2)
                .outputItems(dust, NiobiumPentoxide, 7)
                .save(provider);
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
                .EUt(VA[MV])
                .circuitMeta(2)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("zinc_diarsenide"))
                .inputItems(dust, Arsenic, 2)
                .inputItems(dust, Zinc, 3)
                .outputItems(dust, ZincDiarsenide, 5)
                .duration(100)
                .EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("arsenic_hydride"))
                .inputItems(dust, ZincDiarsenide, 5)
                .inputFluids(SulfuricAcid.getFluid(3000))
                .outputItems(ZincSulfate, 18)
                .outputFluids(ArsenicHydride.getFluid(2000))
                .duration(100)
                .EUt(VA[MV])
                .save(provider);
//        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("methylmagnesium_iodide"))
//                .inputFluids(Iodomethane.getFluid(1000))
//                .inputItems(dust, Magnesium, 1)
//                .outputItems(dust, MethylmagnesiumIodide, 6)
//                .duration(100)
//                .EUt(VA[MV])
//                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("iodomethane"))
                .inputItems(dust, Iodine, 5)
                .inputItems(dust, Phosphorus, 1)
                .inputFluids(Methanol.getFluid(5000))
                .outputFluids(Iodomethane.getFluid(5000))
                .outputFluids(PhosphoricAcid.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .duration(100)
                .EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("trimethylindium"))
                .inputFluids(Trimethylaluminium.getFluid(1000))
                .inputItems(dust, Indium, 1)
                .outputFluids(Trimethylindium.getFluid(1000))
                .outputItems(dust, Aluminium)
                .duration(100)
                .EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("trimethylgallium"))
                .inputFluids(Trimethylaluminium.getFluid(1000))
                .inputItems(dust, GalliumTrichloride, 1)
                .outputFluids(Trimethylgallium.getFluid(1000))
                .outputItems(dust, AluminiumChloride)
                .duration(100)
                .EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("trimethylaluminium"))
                .inputItems(dust, Aluminium)
                .inputFluids(Iodomethane.getFluid(3000))
                .outputFluids(Trimethylaluminium.getFluid(1000))
                .outputItems(dust, Iodine, 3)
                .duration(100)
                .EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("lithium_carbonate"))
                .inputItems(dust, LithiumHydroxide, 6)
                .inputFluids(CarbonDioxide.getFluid(1000))
                .outputItems(LithiumCarbonate, 6)
                .outputFluids(Water.getFluid(1000))
                .duration(100)
                .EUt(VA[MV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("lithium_hydroxide"))
                .inputItems(dust, Lithium, 2)
                .inputFluids(Water.getFluid(2000))
                .outputItems(dust, LithiumHydroxide, 2)
                .outputFluids(Hydrogen.getFluid(1000))
                .duration(100)
                .EUt(VA[MV])
                .save(provider);
    }
}
