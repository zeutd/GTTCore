package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.GTCEu;
import com.gtt.gttcore.GTTCore;
import com.simibubi.create.AllFluids;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MIXER_RECIPES;
import static com.gtt.gttcore.common.data.GTTMaterials.AluminiumAlloy6061;

public class MixerRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        if (GTCEu.Mods.isCreateLoaded()) {
            MIXER_RECIPES.recipeBuilder(GTTCore.id("tea"))
                    .duration(100)
                    .EUt(VA[ULV])
                    .inputItems(ItemTags.LEAVES)
                    .inputFluids(Water.getFluid(250))
                    .inputFluids(Milk.getFluid(250))
                    .outputFluids(new FluidStack(AllFluids.TEA.getSource(), 500))
                    .save(provider);
        }
        MIXER_RECIPES.recipeBuilder(GTTCore.id("nether_star_dust"))
                .duration(100)
                .EUt(VA[HV])
                .inputItems(dust, Platinum, 4)
                .inputItems(dust, Palladium, 4)
                .inputItems(dust, Caesium, 3)
                .inputItems(dust, Iodine, 1)
                .outputItems(dust, NetherStar, 12)
                .inputFluids(Helium3.getFluid(250))
                .save(provider);
        MIXER_RECIPES.recipeBuilder(GTTCore.id(""))
                .EUt(VA[MV])
                .duration(100)
                .outputItems(dust, AluminiumAlloy6061, 14)
                .inputItems(dust, Magnesium, 2)
                .inputItems(dust, Silicon, 1)
                .inputItems(dust, Copper, 1)
                .inputItems(dust, Aluminium, 10)
                .save(provider);
    }
}
