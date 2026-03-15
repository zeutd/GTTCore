package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.MarkerMaterials;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.OPTICAL_PIPES;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTItems.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;
import static com.gtt.gttcore.common.data.recipes.GTTRecipeTypes.CVD_RECIPES;

public class CircuitRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        componentRecipes(provider);
        boardRecipes(provider);
        waferRecipes(provider);
    }
    private static void waferRecipes(Consumer<FinishedRecipe> provider){

        BLAST_RECIPES.recipeBuilder(GTTCore.id("lithium_niobate_boule"))
                .EUt(VA[LuV])
                .duration(500)
                .inputItems(dust, NiobiumPentoxide, 7 * 64)
                .inputItems(dust, LithiumCarbonate, 6 * 64)
                .outputItems(LITHIUM_NIOBATE_BOULE)
                .outputFluids(CarbonDioxide.getFluid(64000))
                .save(provider);
        CUTTER_RECIPES.recipeBuilder(GTTCore.id("photonic_wafer"))
                .EUt(VA[LuV])
                .duration(400)
                .inputItems(LITHIUM_NIOBATE_BOULE)
                .outputItems(PHOTONIC_WAFER, 32)
                .save(provider);

        CVD_RECIPES.recipeBuilder(GTTCore.id("silicon_layered_photonic_wafer"))
                .EUt(VA[EV])
                .duration(400)
                .inputItems(PHOTONIC_WAFER)
                .inputFluids(Silane.getFluid(250))
                .outputItems(SILICON_LAYERED_PHOTONIC_WAFER)
                .save(provider);

        LASER_ENGRAVER_RECIPES.recipeBuilder(GTTCore.id("photonic_cpu_wafer"))
                .EUt(VA[ZPM])
                .duration(400)
                .inputItems(SILICON_LAYERED_PHOTONIC_WAFER)
                .notConsumable(lens, MarkerMaterials.Color.Magenta)
                .outputItems(PHOTONIC_CPU_WAFER)
                .save(provider);
        CUTTER_RECIPES.recipeBuilder(GTTCore.id("photonic_cpu_chip"))
                .EUt(VA[LuV])
                .duration(400)
                .inputItems(PHOTONIC_CPU_WAFER)
                .outputItems(PHOTONIC_CPU_CHIP)
                .save(provider);







        CVD_RECIPES.recipeBuilder(GTTCore.id("qbit_cpu_wafer_quantum_eye"))
                .inputItems(NANO_CENTRAL_PROCESSING_UNIT_WAFER)
                .inputItems(QUANTUM_EYE, 2)
                .inputFluids(Trimethylgallium.getFluid(L * 2))
                .inputFluids(ArsenicHydride.getFluid(L * 2))
                .outputItems(QUBIT_CENTRAL_PROCESSING_UNIT_WAFER)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(900).EUt(VA[EV]).save(provider);

        CVD_RECIPES.recipeBuilder(GTTCore.id("qbit_cpu_wafer_radon"))
                .inputItems(NANO_CENTRAL_PROCESSING_UNIT_WAFER)
                .inputFluids(Trimethylgallium.getFluid(L * 2 / 3))
                .inputFluids(Phosphine.getFluid(L * 2 / 3))
                .inputFluids(Trimethylindium.getFluid(L * 2 / 3))
                .inputFluids(Radon.getFluid(50))
                .outputItems(QUBIT_CENTRAL_PROCESSING_UNIT_WAFER)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(1200).EUt(VA[EV]).save(provider);

        CVD_RECIPES.recipeBuilder(GTTCore.id("hpic_wafer"))
                .inputItems(POWER_INTEGRATED_CIRCUIT_WAFER)
                .inputFluids(Trimethylgallium.getFluid(L * 2 / 3))
                .inputFluids(Phosphine.getFluid(L * 2 / 3))
                .inputFluids(Trimethylindium.getFluid(L * 2 / 3))
                .inputFluids(VanadiumGallium.getFluid(L * 2))
                .outputItems(HIGH_POWER_INTEGRATED_CIRCUIT_WAFER)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(1200).EUt(VA[IV]).save(provider);

        CVD_RECIPES.recipeBuilder(GTTCore.id("uhpic_wafer"))
                .inputItems(HIGH_POWER_INTEGRATED_CIRCUIT_WAFER)
                .inputFluids(Trimethylgallium.getFluid(L * 8 / 3))
                .inputFluids(Phosphine.getFluid(L * 8 / 3))
                .inputFluids(Trimethylindium.getFluid(L * 8 / 3))
                .inputFluids(Naquadah.getFluid(L * 4))
                .outputItems(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(1200).EUt(VA[LuV]).save(provider);

        CVD_RECIPES.recipeBuilder(GTTCore.id("nano_cpu_wafer"))
                .inputItems(CENTRAL_PROCESSING_UNIT_WAFER)
                .inputItems(CARBON_FIBERS, 16)
                .inputFluids(Glowstone.getFluid(L * 4))
                .outputItems(NANO_CENTRAL_PROCESSING_UNIT_WAFER)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(1200).EUt(VA[EV]).save(provider);
    }
    private static void componentRecipes(Consumer<FinishedRecipe> provider){
        // Resistor
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("resistor_coal"))
                .inputItems(dust, Coal)
                .inputItems(wireFine, Copper, 4)
                .outputItems(RESISTOR, 2)
                .inputFluids(PhenolicResin.getFluid(100))
                .duration(160).EUt(6).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("resistor_charcoal"))
                .inputItems(dust, Charcoal)
                .inputItems(wireFine, Copper, 4)
                .outputItems(RESISTOR, 2)
                .inputFluids(PhenolicResin.getFluid(100))
                .duration(160).EUt(6).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("resistor_carbon"))
                .inputItems(dust, Carbon)
                .inputItems(wireFine, Copper, 4)
                .outputItems(RESISTOR, 2)
                .inputFluids(PhenolicResin.getFluid(100))
                .duration(160).EUt(6).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("resistor_coal_annealed"))
                .inputItems(dust, Coal)
                .inputItems(wireFine, AnnealedCopper, 4)
                .outputItems(RESISTOR, 4)
                .inputFluids(PhenolicResin.getFluid(100))
                .duration(160).EUt(6).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("resistor_charcoal_annealed"))
                .inputItems(dust, Charcoal)
                .inputItems(wireFine, AnnealedCopper, 4)
                .outputItems(RESISTOR, 4)
                .inputFluids(PhenolicResin.getFluid(100))
                .duration(160).EUt(6).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("resistor_carbon_annealed"))
                .inputItems(dust, Carbon)
                .inputItems(wireFine, AnnealedCopper, 4)
                .outputItems(RESISTOR, 4)
                .inputFluids(PhenolicResin.getFluid(100))
                .duration(160).EUt(6).save(provider);
    }

    private static void boardRecipes(Consumer<FinishedRecipe> provider){
        // Basic Circuit Board
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("basic_circuit_board"))
                .inputItems(foil, Copper, 4)
                .inputItems(plate, Wood)
                .inputFluids(PhenolicResin.getFluid(100))
                .outputItems(BASIC_CIRCUIT_BOARD)
                .duration(200).EUt(VA[ULV]).save(provider);

        // Phenolic Board
        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("phenolic_board"))
                .inputItems(dust, Mica)
                .circuitMeta(1)
                .inputFluids(PhenolicResin.getFluid(50))
                .outputItems(PHENOLIC_BOARD)
                .duration(150).EUt(VA[LV]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("optical_waveguide_circuit_board"))
                .inputItems(MULTILAYER_FIBER_BOARD)
                .inputItems(foil, TungstenCarbide, 4)
                .inputItems(plate, UltraHighMolecularWeightPolyethylene)
                .inputFluids(Polybenzimidazole.getFluid(100))
                .outputItems(OPTICAL_WAVEGUIDE_BOARD)
                .duration(200).EUt(VA[ZPM]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder(GTTCore.id("optical_waveguide_printed_circuit_board"))
                .inputItems(OPTICAL_WAVEGUIDE_BOARD)
                .inputItems(OPTICAL_PIPES[0], 16)
                .duration(200).EUt(VA[ZPM]).save(provider);
    }
}
