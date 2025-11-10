package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gtt.gttcore.GTTCore;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTItems.STICKY_RESIN;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CHEMICAL_RECIPES;
import static com.gtt.gttcore.common.data.GTTMaterials.*;

public class CircuitRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        componentRecipes(provider);
        boardRecipes(provider);
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
    }
}
