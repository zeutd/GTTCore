package com.gtt.gttcore.common.data.recipes.lines;

import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.crafting.CraftingHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;

public class PlatinumGroupLine {
    public static void init(Consumer<FinishedRecipe> provider) {
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("ammonium_chloride"))
                .inputFluids(Ammonia.getFluid(1000))
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputItems(dust, AmmoniumChloride, 6)
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("iron_ii_chloride"))
                .inputItems(dust, Iron, 7)
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(Iron2Chloride.getFluid(1000))
                .EUt(VHA[LV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("sodium_nitrite"))
                .inputItems(dust, Sodium, 7)
                .inputFluids(NitricOxide.getFluid(1000))
                .inputFluids(NitrogenDioxide.getFluid(1000))
                .outputItems(dust, SodiumNitrite, 7)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_solution"))
                .inputItems(dust, PlatinumGroupSludge, 6)
                .inputFluids(AquaRegia.getFluid(1200))
                .outputFluids(PlatinumGroupSolution.getFluid(1200))
                .outputItems(dust, InertMetalMixture, 1)
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_gold_dust_from_solution"))
                .inputFluids(PlatinumGroupSolution.getFluid(1200))
                .inputFluids(Iron2Chloride.getFluid(100))
                .outputFluids(PlatinumPalladiumSolution.getFluid(1200))
                .outputItems(dust, Gold, 2)
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ammonium_hexachloroplatinate_dust_from_solution"))
                .inputFluids(PlatinumPalladiumSolution.getFluid(1200))
                .inputItems(dust, AmmoniumChloride, 1)
                .outputItems(dust, AmmoniumHexachloroplatinate, 2)
                .outputFluids(PalladiumSolution.getFluid(1200))
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ammonium_diamminedichloropalladium_dust_from_solution"))
                .inputFluids(PalladiumSolution.getFluid(1200))
                .inputFluids(Ammonia.getFluid(100))
                .inputFluids(HydrochloricAcid.getFluid(100))
                .outputItems(dust, Diamminedichloropalladium, 2)
                .outputFluids(AquaRegia.getFluid(1200))
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        PYROLYSE_RECIPES.recipeBuilder(GTTCore.id("platinum_group_palladium_dust_pyrolysis"))
                .inputItems(dust, Diamminedichloropalladium, 3)
                .outputItems(dust, Palladium, 3)
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        PYROLYSE_RECIPES.recipeBuilder(GTTCore.id("platinum_group_platinum_dust_pyrolysis"))
                .inputItems(dust, AmmoniumHexachloroplatinate, 3)
                .outputItems(dust, Platinum, 3)
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        BLAST_RECIPES.recipeBuilder(GTTCore.id("platinum_group_inert_metal_sodium_bisulfate_dust_blasting"))
                .inputItems(dust, InertMetalMixture, 1)
                .inputItems(dust, SodiumBisulfate, 1)
                .outputItems(dust, InertMetalSodiumBisulfate, 2)
                .blastFurnaceTemp(1000)
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        CHEMICAL_BATH_RECIPES.recipeBuilder(GTTCore.id("platinum_group_rhodium_sulfate_solution_bath"))
                .inputItems(dust, InertMetalSodiumBisulfate, 1)
                .inputFluids(Water.getFluid(1000))
                .outputItems(dust, RutheniumOsmiumIridiumMixture, 1)
                .outputFluids(RhodiumSulfateSolution.getFluid(1000))
                .EUt(VHA[LV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_rhodium_hydroxide"))
                .inputItems(dust, SodiumHydroxide, 1)
                .inputFluids(RhodiumSulfateSolution.getFluid(1000))
                .outputItems(dust, RhodiumHydroxide, 1)
                .EUt(VHA[LV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ammonium_hexanitrorhodium"))
                .inputItems(dust, RhodiumHydroxide, 1)
                .inputItems(dust, SodiumNitrite, 1)
                .inputItems(dust, AmmoniumChloride, 1)
                .outputItems(dust, AmmoniumHexanitrorhodium, 3)
                .EUt(VHA[LV])
                .duration(100)
                .save(provider);
        CHEMICAL_BATH_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ammonium_hexachlororhodate"))
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .inputItems(dust, AmmoniumHexanitrorhodium, 1)
                .outputItems(dust, AmmoniumHexachlororhodate, 1)
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        BLAST_RECIPES.recipeBuilder(GTTCore.id("platinum_group_rhodium_dust_blasting"))
                .inputItems(dust, AmmoniumHexachlororhodate, 3)
                .inputFluids(Hydrogen.getFluid(1000))
                .outputItems(dust, Rhodium, 3)
                .blastFurnaceTemp(900)
                .EUt(VHA[HV])
                .duration(100)
                .save(provider);
        BLAST_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ruthenium_osmium_iridium_mixture_sodium_peroxide_dust_blasting"))
                .inputItems(dust, RutheniumOsmiumIridiumMixture, 1)
                .inputItems(dust, SodiumPeroxide, 1)
                .outputItems(dust, RutheniumOsmiumIridiumMixtureSodiumPeroxide, 2)
                .blastFurnaceTemp(1000)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        BLAST_RECIPES.recipeBuilder(GTTCore.id("platinum_group_iridium_dust_blasting"))
                .inputItems(dust, AmmoniumHexachloroiridate, 3)
                .inputFluids(Hydrogen.getFluid(1000))
                .outputItems(dust, Iridium, 3)
                .blastFurnaceTemp(1000)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        CHEMICAL_BATH_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ruthenium_osmium_iridium_mixture_sodium_peroxide"))
                .inputItems(dust, RutheniumOsmiumIridiumMixtureSodiumPeroxide, 1)
                .inputFluids(Water.getFluid(1000))
                .outputItems(dust, IridiumOxide, 1)
                .outputFluids(RutheniumOsmiumSaltSolution.getFluid(1000))
                .EUt(VHA[LV])
                .duration(100)
                .save(provider);
        CHEMICAL_BATH_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ruthenium_osmium_salt_solution_bath"))
                .inputItems(dust, IridiumOxide, 1)
                .inputFluids(AquaRegia.getFluid(1000))
                .outputItems(dust, AmmoniumHexachloroiridate, 1)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ruthenium_osmium_oxide"))
                .inputFluids(Chlorine.getFluid(500))
                .inputFluids(RutheniumOsmiumSaltSolution.getFluid(1000))
                .outputItems(dust, RutheniumOsmiumOxide, 1)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_osmium_solution"))
                .inputFluids(Ethanol.getFluid(500))
                .inputItems(dust, RutheniumOsmiumOxide, 1)
                .inputItems(dust, SodiumHydroxide, 1)
                .outputFluids(OsmiumSolution.getFluid(500))
                .outputItems(dust, RutheniumOxide, 1)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ruthenium_solution"))
                .inputFluids(HydrochloricAcid.getFluid(500))
                .inputItems(dust, RutheniumOxide, 1)
                .outputFluids(RutheniumSolution.getFluid(500))
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ammonium_hexachlororuthenate"))
                .inputFluids(RutheniumSolution.getFluid(500))
                .inputItems(dust, AmmoniumChloride, 1)
                .outputItems(dust, AmmoniumHexachlororuthenate)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder(GTTCore.id("platinum_group_tetraamminedioxidoosmiumdichloride"))
                .inputFluids(OsmiumSolution.getFluid(500))
                .inputItems(dust, AmmoniumChloride, 1)
                .outputItems(dust, Tetraamminedioxidoosmiumdichloride)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        BLAST_RECIPES.recipeBuilder(GTTCore.id("platinum_group_ruthenium_dust_blasting"))
                .inputItems(dust, AmmoniumHexachlororuthenate, 3)
                .inputFluids(Hydrogen.getFluid(1000))
                .outputItems(dust, Ruthenium, 3)
                .blastFurnaceTemp(1000)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
        BLAST_RECIPES.recipeBuilder(GTTCore.id("platinum_group_osmium_dust_blasting"))
                .inputItems(dust, Tetraamminedioxidoosmiumdichloride, 3)
                .inputFluids(Hydrogen.getFluid(1000))
                .outputItems(dust, Osmium, 3)
                .blastFurnaceTemp(1000)
                .EUt(VHA[MV])
                .duration(100)
                .save(provider);
    }
}
