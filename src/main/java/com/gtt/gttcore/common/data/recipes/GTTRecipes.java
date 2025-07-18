package com.gtt.gttcore.common.data.recipes;

import com.gtt.gttcore.common.data.recipes.lines.PlatinumGroupLine;
import com.gtt.gttcore.common.data.recipes.lines.ZieglerNattaLine;
import com.gtt.gttcore.common.data.recipes.lines.ZirconiumLine;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class GTTRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        MiscRecipes.init(provider);
        MachineRecipes.init(provider);
        ZieglerNattaLine.init(provider);
        ZirconiumLine.init(provider);
        PlatinumGroupLine.init(provider);
        MixerRecipes.init(provider);
        RocketRecipes.init(provider);
    }
}
