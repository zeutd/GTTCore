package com.gtt.gttcore.common.data.recipes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gtt.gttcore.GTTCore;
import com.simibubi.create.AllFluids;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MIXER_RECIPES;

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
                .inputItems(TagPrefix.dust, Platinum, 4)
                .inputItems(TagPrefix.dust, Palladium, 4)
                .inputItems(TagPrefix.dust, Caesium, 3)
                .inputItems(TagPrefix.dust, Iodine, 1)
                .outputItems(TagPrefix.dust, NetherStar, 12)
                .inputFluids(Helium3.getFluid(250))
                .save(provider);
    }
}
