package com.gtt.gttcore.common.data.recipes.lines;

import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dustTiny;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.EXTRACTION_TANK_RECIPE;

public class LanthanideGroupLine {
    public static void init(Consumer<FinishedRecipe> provider) {
        EXTRACTOR_RECIPES.recipeBuilder(GTTCore.id("xenotime_extraction"))
                .inputItems(dust, Xenotime)
                .outputItems(dust, RareEarth)
                .outputFluids(Helium.getFluid(200))
                .duration(64).EUt(64).save(provider);
        EXTRACTOR_RECIPES.recipeBuilder(GTTCore.id("bastnasite_extraction"))
                .inputItems(dust, Bastnasite)
                .outputItems(dust, RareEarth)
                .outputFluids(Helium.getFluid(200))
                .duration(64).EUt(64).save(provider);
        EXTRACTOR_RECIPES.recipeBuilder(GTTCore.id("bastnasite_extraction"))
                .inputItems(dust, Monazite)
                .outputItems(dust, RareEarth)
                .outputFluids(Helium.getFluid(200))
                .duration(64).EUt(64).save(provider);


        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("magnesia"))
                .EUt(VA[LV])
                .duration(20)
                .inputFluids(Oxygen.getFluid(1000))
                .inputItems(dust, Magnesium)
                .outputItems(dust, Magnesia, 2)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("oxalic_acid"))
                .duration(20)
                .EUt(VA[LV])
                .inputFluids(EthyleneGlycol.getFluid(1000))
                .inputFluids(Oxygen.getFluid(1000))
                .outputFluids(OxalicAcid.getFluid(1000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("ethylene_glycol"))
                .duration(20)
                .EUt(VA[LV])
                .inputFluids(EthyleneOxide.getFluid(2000))
                .inputFluids(Water.getFluid(1000))
                .outputFluids(EthyleneGlycol.getFluid(2000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("ethylene_oxide"))
                .duration(20)
                .EUt(VA[LV])
                .inputFluids(Ethylene.getFluid(2000))
                .inputFluids(Oxygen.getFluid(1000))
                .outputFluids(EthyleneOxide.getFluid(2000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("sodium_sulfite"))
                .duration(20)
                .EUt(VA[LV])
                .inputItems(dust, SodiumHydroxide, 2)
                .inputFluids(SulfurDioxide.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .outputItems(dust, SodiumSulfite, 2)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("aluminum_chloride"))
                .duration(20)
                .EUt(VA[LV])
                .inputItems(dust, Aluminium)
                .inputFluids(Chlorine.getFluid(3000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("pyrolusite"))
                .duration(20)
                .EUt(VA[LV])
                .inputItems(dust, Manganese)
                .inputFluids(Oxygen.getFluid(2000))
                .outputItems(dust, Pyrolusite, 3)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("potassium_manganate"))
                .duration(20)
                .EUt(VA[LV])
                .inputItems(dust, Pyrolusite, 2)
                .inputItems(dust, PotassiumHydroxide, 4)
                .inputFluids(Oxygen.getFluid(1000))
                .outputItems(dust, PotassiumManganate, 7)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("potassium_permanganate"))
                .duration(20)
                .EUt(VA[LV])
                .inputItems(dust, PotassiumManganate, 2)
                .inputFluids(Water.getFluid(2000))
                .outputItems(dust, PotassiumPermanganate, 1)
                .outputItems(dust, PotassiumHydroxide, 2)
                .outputFluids(Hydrogen.getFluid(1000))
                .save(provider);








        BLAST_RECIPES.recipeBuilder(GTTCore.id("rare_earth_roasting"))
                .duration(100)
                .EUt(VA[HV])
                .inputItems(dust, RareEarth)
                .outputItems(dust, RoastedRareEarth)
                .save(provider);
        CHEMICAL_BATH_RECIPES.recipeBuilder(GTTCore.id("rare_earth_bathing"))
                .duration(100)
                .EUt(VA[HV])
                .inputItems(dust, RoastedRareEarth)
                .inputFluids(HydrochloricAcid.getFluid(10000))
                .outputFluids(LowCeriumContentRareEarthChlorideSolution.getFluid(10000))
                .outputItems(dust, CeriumContainingResidue)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("rare_earth_solution_purification"))
                .duration(500)
                .EUt(VA[HV])
                .inputItems(dust, Magnesia)
                .inputFluids(LowCeriumContentRareEarthChlorideSolution.getFluid(5000))
                .outputFluids(PurifiedRareEarthChlorideSolution.getFluid(5000))
                .outputItems(dustTiny, IronHydroxide)
                .outputItems(dustTiny, LeadHydroxide)
                .outputItems(dustTiny, ThoriumHydroxide)
                .outputItems(dustTiny, AluminiumHydroxide)
                .outputItems(dust, MagnesiumChloride)
                .save(provider);
        LARGE_CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("rare_earth_solution_heavy_metals_removal"))
                .duration(300)
                .EUt(VA[HV])
                .inputItems(dust, SodiumSulfide)
                .inputFluids(PurifiedRareEarthChlorideSolution.getFluid(5000))
                .outputFluids(HeavyMetalsRemovedRareEarthChlorideSolution.getFluid(5000))
                .outputItems(dustTiny, LeadSulfide, 2)
                .outputItems(dustTiny, CopperSulfide, 2)
                .outputItems(dustTiny, ZincSulfide, 2)
                .outputItems(dust, Salt)
                .save(provider);
        EXTRACTION_TANK_RECIPE.recipeBuilder(GTTCore.id("rare_earth_solution_extraction"))
                .duration(500)
                .EUt(VA[IV])
                .inputFluids(HeavyMetalsRemovedRareEarthChlorideSolution.getFluid(3000))
                .outputFluids(HeavyRareEarthSolution.getFluid(1000))
                .outputFluids(MediumRareEarthSolution.getFluid(1000))
                .outputFluids(LightRareEarthSolution.getFluid(1000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("cerium_residue_dissolving"))
                .duration(100)
                .EUt(VA[HV])
                .inputItems(dust, CeriumContainingResidue)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .inputItems(dust, SodiumSulfite)
                .outputFluids(CeriumSolution.getFluid(1000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("cerium_solution_defluorination"))
                .duration(100)
                .EUt(VA[HV])
                .inputFluids(CeriumSolution.getFluid(1000))
                .inputItems(dust, AluminiumChloride)
                .outputFluids(DefluorinatedCeriumSolution.getFluid(1000))
                .outputItems(dust, AluminiumFluoride)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("cerium_solution_oxidation"))
                .duration(100)
                .EUt(VA[HV])
                .inputFluids(DefluorinatedCeriumSolution.getFluid(1000))
                .inputItems(dust, PotassiumPermanganate)
                .inputItems(dust, SodiumChlorate)
                .outputFluids(OxidizedCeriumSolution.getFluid(1000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("cerium_solution_precipitation"))
                .duration(100)
                .EUt(VA[HV])
                .inputFluids(OxidizedCeriumSolution.getFluid(1000))
                .inputItems(dust, SodiumHydroxide)
                .outputItems(dust, RawCeriumOxide)
                .outputFluids(CeriumWaste.getFluid(1000))
                .save(provider);
        BLAST_RECIPES.recipeBuilder(GTTCore.id("cerium_oxide_calcination"))
                .duration(200)
                .EUt(VA[HV])
                .inputItems(dust, RawCeriumOxide)
                .outputItems(dust, CeriumOxide)
                .save(provider);





        LARGE_CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("bis_2_ethylhexyl_phosphite"))
                .duration(200)
                .EUt(VA[HV])
                .inputFluids(PhosphorusTrichloride.getFluid(1000))
                .inputFluids(Octanol.getFluid(3000))
                .outputFluids(Bis2EthylhexylPhosphite.getFluid(1000))
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .outputFluids(Chlorooctane.getFluid(1000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("raw_p_507"))
                .duration(200)
                .EUt(VA[HV])
                .inputFluids(Bis2EthylhexylPhosphite.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .inputFluids(HydrochloricAcid.getFluid(250))
                .outputFluids(RawP507.getFluid(1000))
                .save(provider);
        DISTILLATION_RECIPES.recipeBuilder(GTTCore.id("p_507"))
                .duration(200)
                .EUt(VA[HV])
                .inputFluids(RawP507.getFluid(10000))
                .outputFluids(Water.getFluid(500))
                .outputFluids(HydrochloricAcid.getFluid(250))
                .outputFluids(Octanol.getFluid(250))
                .outputFluids(P507.getFluid(9000))
                .save(provider);




        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("phosphorus_oxychloride"))
                .duration(200)
                .EUt(VA[HV])
                .inputFluids(PhosphorusTrichloride.getFluid(1000))
                .inputFluids(Oxygen.getFluid(1000))
                .outputFluids(PhosphorusOxychloride.getFluid(2000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("di_2_ethylhexyl_phosphoryl_chloride"))
                .duration(200)
                .EUt(VA[HV])
                .inputFluids(PhosphorusOxychloride.getFluid(1000))
                .inputFluids(Octanol.getFluid(2000))
                .outputFluids(Di2EthylhexylPhosphorylChloride.getFluid(1000))
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("p_204"))
                .duration(200)
                .EUt(VA[HV])
                .inputFluids(Di2EthylhexylPhosphorylChloride.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .outputFluids(HydrochloricAcid.getFluid(250))
                .outputFluids(P204.getFluid(1000))
                .save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder(GTTCore.id("cerium_waste"))
                .duration(200)
                .EUt(VA[HV])
                .inputFluids(CeriumWaste.getFluid(1000))
                .outputFluids(SulfuricAcid.getFluid(1000))
                .outputItems(dustTiny, Iron, 2)
                .outputItems(dustTiny, Aluminium, 2)
                .outputItems(dustTiny, Lead, 2)
                .outputItems(dustTiny, Calcium, 2)
                .outputItems(dustTiny, Magnesium, 2)
                .outputItems(dustTiny, Barium, 2)
                .outputItems(dustTiny, Zinc, 2)
                .outputItems(dustTiny, Manganese, 2)
                .save(provider);







        EXTRACTION_TANK_RECIPE.recipeBuilder(GTTCore.id("heavy_rare_earth_solution_separation"))
                .duration(500)
                .EUt(VA[IV])
                .inputFluids(HeavyRareEarthSolution.getFluid(2000))
                .outputFluids(LutetiumSolution.getFluid(1000))
                .outputFluids(YttriumSolution.getFluid(1000))
                .save(provider);
        EXTRACTION_TANK_RECIPE.recipeBuilder(GTTCore.id("medium_rare_earth_solution_separation"))
                .duration(500)
                .EUt(VA[IV])
                .inputFluids(MediumRareEarthSolution.getFluid(2000))
                .outputFluids(SamariumSolution.getFluid(1000))
                .outputFluids(EuropiumSolution.getFluid(1000))
                .save(provider);
        EXTRACTION_TANK_RECIPE.recipeBuilder(GTTCore.id("light_rare_earth_solution_separation"))
                .duration(500)
                .EUt(VA[IV])
                .inputFluids(LightRareEarthSolution.getFluid(2000))
                .outputFluids(LanthanumSolution.getFluid(1000))
                .outputFluids(NeodymiumSolution.getFluid(1000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("lanthanum_oxalate_hydrate"))
                .duration(100)
                .EUt(VA[HV])
                .inputFluids(LanthanumSolution.getFluid(2000))
                .inputFluids(OxalicAcid.getFluid(3000))
                .outputItems(dust, LanthanumOxalateHydrate, 2)
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("neodymium_oxalate_hydrate"))
                .duration(100)
                .EUt(VA[HV])
                .inputFluids(NeodymiumSolution.getFluid(2000))
                .inputFluids(OxalicAcid.getFluid(3000))
                .outputItems(dust, NeodymiumOxalateHydrate, 2)
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("samarium_oxalate_hydrate"))
                .duration(100)
                .EUt(VA[HV])
                .inputFluids(SamariumSolution.getFluid(2000))
                .inputFluids(OxalicAcid.getFluid(3000))
                .outputItems(dust, SamariumOxalateHydrate, 2)
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("europium_oxalate_hydrate"))
                .duration(100)
                .EUt(VA[HV])
                .inputFluids(EuropiumSolution.getFluid(2000))
                .inputFluids(OxalicAcid.getFluid(3000))
                .outputItems(dust, EuropiumOxalateHydrate, 2)
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("lutetium_oxalate_hydrate"))
                .duration(100)
                .EUt(VA[HV])
                .inputFluids(LutetiumSolution.getFluid(2000))
                .inputFluids(OxalicAcid.getFluid(3000))
                .outputItems(dust, LutetiumOxalateHydrate, 2)
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("yttrium_oxalate_hydrate"))
                .duration(100)
                .EUt(VA[HV])
                .inputFluids(YttriumSolution.getFluid(2000))
                .inputFluids(OxalicAcid.getFluid(3000))
                .outputItems(dust, YttriumOxalateHydrate, 2)
                .outputFluids(HydrochloricAcid.getFluid(2000))
                .save(provider);
    }
}
