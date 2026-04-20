package com.gtt.gttcore.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gtt.gttcore.api.content.SerializerParticleStack;
import com.gtt.gttcore.api.machine.trait.NotifiableParticleContainer;
import com.gtt.gttcore.api.particle.ParticleStack;
import com.gtt.gttcore.api.particle.ParticleType;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;

public class ParticleRecipeCapability extends RecipeCapability<ParticleStack> {

    public final static ParticleRecipeCapability CAP = new ParticleRecipeCapability();

    protected ParticleRecipeCapability() {
        super("particle", 0xFFFF00FF, false, 3, SerializerParticleStack.INSTANCE);
    }

    @Override
    public ParticleStack copyInner(ParticleStack content) {
        return content.copy();
    }

    @Override
    public ParticleStack copyWithModifier(ParticleStack content, ContentModifier modifier) {
        return content.withAmount(modifier.apply(content.getAmount()));
    }

    @Override
    public void addXEIInfo(WidgetGroup group, int xOffset, GTRecipe recipe, List<Content> contents, boolean perTick,
                           boolean isInput, MutableInt yOffset) {
        int particleAmount = contents.stream().map(Content::getContent).map(CAP::of).mapToInt(ParticleStack::getAmount).sum();
        group.addWidget(new LabelWidget(3 - xOffset, yOffset.addAndGet(10),
                LocalizationUtils.format(isInput ? "gttcore.machine.particle_amount_input" : "gttcore.machine.particle_amount_output", particleAmount)));
    }

    @Override
    public int getMaxParallelByInput(IRecipeCapabilityHolder holder, GTRecipe recipe, int limit, boolean tick) {
        if (!holder.hasCapabilityProxies()) return 0;
        Object2IntMap<ParticleType> map = new Object2IntOpenHashMap<>();
        for (IRecipeHandler<?> container : holder.getCapabilitiesFlat(IO.IN, CAP)) {
            if (container instanceof NotifiableParticleContainer particleContainer && !particleContainer.isEmpty()) {
                map.putIfAbsent(particleContainer.stored.getType(), 0);
                map.computeIntIfPresent(particleContainer.stored.getType(), (p, l) -> l + particleContainer.stored.getAmount());
            }
        }
        int maxParallel = limit;
        var input = tick ? recipe.tickInputs : recipe.inputs;
        for (Content content : input.get(CAP)) {
            ParticleStack stack = of(content);
            maxParallel = Math.min(maxParallel, map.getInt(stack.getType()) / stack.getAmount());
        }

        return maxParallel;
    }


}
