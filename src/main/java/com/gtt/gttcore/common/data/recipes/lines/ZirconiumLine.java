package com.gtt.gttcore.common.data.recipes.lines;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gtt.gttcore.common.data.GTTMaterials.*;

public class ZirconiumLine {
    public static void init(Consumer<FinishedRecipe> provider) {
        BLAST_RECIPES.recipeBuilder("zirconium_carbide")
                .inputItems(dust, Zircon)
                .inputItems(dust, Carbon)
                .outputItems(dust, ZirconiumCarbide)
                .blastFurnaceTemp(900)
                .EUt(V[HV])
                .duration(60)
                .save(provider);
        CHEMICAL_RECIPES.recipeBuilder("zirconium_tetrachloride")
                .inputItems(dust, ZirconiumCarbide)
                .inputFluids(Chlorine.getFluid(500))
                .outputItems(dust, ZirconiumTetrachloride)
                .EUt(V[HV])
                .duration(40)
                .save(provider);
        BLAST_RECIPES.recipeBuilder("zirconium_from_tetrachloride").duration(800).EUt(VA[HV])
                .inputItems(dust, Magnesium, 2).inputItems(dust, ZirconiumTetrachloride)
                .outputItems(ingotHot, Zirconium).outputItems(dust, MagnesiumChloride, 6)
                .blastFurnaceTemp(1200).save(provider);
    }
}
