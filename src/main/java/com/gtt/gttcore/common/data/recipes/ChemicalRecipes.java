package com.gtt.gttcore.common.data.recipes;

import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.GTValues.L;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
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
                .outputItems(dust, ZincSulfate, 18)
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
        LARGE_CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("iodomethane"))
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
                .outputItems(dust, LithiumCarbonate, 6)
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
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("aluminium_chloride"))
                .EUt(VA[LV])
                .duration(100)
                .inputItems(dust, Aluminium, 2)
                .inputFluids(HydrochloricAcid.getFluid(6000))
                .outputFluids(Hydrogen.getFluid(6000))
                .outputItems(dust, AluminiumChloride, 8)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("trichlorosilane"))
                .EUt(VA[LV])
                .duration(100)
                .inputItems(dust, Silicon, 1)
                .inputFluids(HydrochloricAcid.getFluid(3000))
                .outputFluids(Hydrogen.getFluid(2000))
                .outputFluids(Trichlorosilane.getFluid(1000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("silane"))
                .EUt(VA[LV])
                .duration(100)
                .notConsumable(dust, AluminiumChloride, 1)
                .inputFluids(Trichlorosilane.getFluid(4000))
                .outputFluids(Silane.getFluid(1000))
                .outputFluids(SiliconChloride.getFluid(3000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("silicon_dust_from_silicon_chloride"))
                .EUt(VA[LV])
                .duration(100)
                .inputFluids(SiliconChloride.getFluid(1000))
                .inputFluids(Hydrogen.getFluid(4000))
                .outputFluids(HydrochloricAcid.getFluid(4000))
                .outputItems(dust, Silicon)
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("lithium_amalgam"))
                .inputItems(dust, Lithium)
                .inputFluids(Mercury.getFluid(1000))
                .outputFluids(LithiumAmalgam.getFluid(1000))
                .duration(100)
                .EUt(VA[LV])
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("lithium_6_amalgam"))
                .inputFluids(LithiumAmalgam.getFluid(1000))
                .inputItems(dust, LithiumHydroxide)
                .outputFluids(Lithium6Amalgam.getFluid(1000))
                .duration(100)
                .EUt(VA[LV])
                .save(provider);



        CHEMICAL_RECIPES.recipeBuilder("formaldehyde")
                .inputFluids(Methanol.getFluid(1000))
                .inputFluids(Oxygen.getFluid(1000))
                .notConsumable(dust, Silver)
                .outputFluids(Formaldehyde.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .duration(100).EUt(VA[ULV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("methanol_from_dioxide")
                .inputFluids(Hydrogen.getFluid(6000))
                .inputFluids(CarbonDioxide.getFluid(1000))
                .circuitMeta(2)
                .outputFluids(Water.getFluid(1000))
                .outputFluids(Methanol.getFluid(1000))
                .duration(120).EUt(VA[ULV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("methanol_from_monoxide")
                .circuitMeta(1)
                .inputFluids(Hydrogen.getFluid(4000))
                .inputFluids(CarbonMonoxide.getFluid(1000))
                .outputFluids(Methanol.getFluid(1000))
                .duration(120).EUt(VA[ULV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("methanol_from_carbon")
                .circuitMeta(3)
                .inputItems(dust, Carbon)
                .inputFluids(Hydrogen.getFluid(4000))
                .inputFluids(Oxygen.getFluid(1000))
                .outputFluids(Methanol.getFluid(1000))
                .duration(320).EUt(VA[ULV]).save(provider);
    }
}
