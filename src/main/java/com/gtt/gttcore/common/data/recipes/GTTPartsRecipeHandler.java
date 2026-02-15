package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.common.data.GTItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.L;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.FLUID_SOLIDFICATION_RECIPES;
import static com.gtt.gttcore.common.data.GTTTagPrefix.ball;

public class GTTPartsRecipeHandler {
    public static void run(@NotNull Consumer<FinishedRecipe> provider, @NotNull Material material) {
        processBall(provider, material);
    }

    private static void processBall(@NotNull Consumer<FinishedRecipe> provider, @NotNull Material material) {
        if (!material.shouldGenerateRecipesFor(ball)) {
            return;
        }

        ItemStack stack = ChemicalHelper.get(ball, material);

        if (material.hasFluid()) {
            FluidStack fluidStack = material.getProperty(PropertyKey.FLUID).solidifiesFrom(L);
            if (!fluidStack.isEmpty()) {
                FLUID_SOLIDFICATION_RECIPES.recipeBuilder("solidify_" + material.getName() + "_to_ball")
                        .notConsumable(GTItems.SHAPE_MOLD_BALL)
                        .inputFluids(fluidStack)
                        .outputItems(stack.copy())
                        .duration(120)
                        .EUt(20)
                        .save(provider);
            }
        }
    }
}
