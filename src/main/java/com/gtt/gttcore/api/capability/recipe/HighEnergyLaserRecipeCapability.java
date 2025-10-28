package com.gtt.gttcore.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.feature.IOverclockMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.content.SerializerInteger;
import com.gregtechceu.gtceu.utils.GTMath;
import com.gtt.gttcore.api.GTTRecipeHelper;
import com.gtt.gttcore.common.machine.IHighEnergyLaserProvider;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;

public class HighEnergyLaserRecipeCapability extends RecipeCapability<Integer> {

    public final static HighEnergyLaserRecipeCapability CAP = new HighEnergyLaserRecipeCapability();

    protected HighEnergyLaserRecipeCapability() {
        super("high_energy_laser", 0xFF000000, false, 3, SerializerInteger.INSTANCE);
    }

    @Override
    public Integer copyInner(Integer content) {
        return content;
    }

    @Override
    public Integer copyWithModifier(Integer content, ContentModifier modifier) {
        return modifier.apply(content);
    }

    @Override
    public void addXEIInfo(WidgetGroup group, int xOffset, GTRecipe recipe, List<Content> contents, boolean perTick,
                           boolean isInput, MutableInt yOffset) {
        int laserAmount = contents.stream().map(Content::getContent).mapToInt(CAP::of).sum();
        group.addWidget(new LabelWidget(3 - xOffset, yOffset.addAndGet(10),
                LocalizationUtils.format(isInput ? "gttcore.machine.high_energy_laser_amount_input" : "gttcore.machine.high_energy_laser_amount_output", laserAmount)));
    }

    @Override
    public int getMaxParallelByInput(IRecipeCapabilityHolder holder, GTRecipe recipe, int limit, boolean tick) {
        int recipeLaserAmount = GTTRecipeHelper.getInputHighEnergyLaser(recipe);
        int maxLaser = Integer.MAX_VALUE;
        if (holder instanceof IHighEnergyLaserProvider highEnergyLaserProvider) {
            maxLaser = highEnergyLaserProvider.getLaserAmount();
        }
        return Math.min(limit, Math.abs(GTMath.saturatedCast(maxLaser / recipeLaserAmount)));
    }
}
