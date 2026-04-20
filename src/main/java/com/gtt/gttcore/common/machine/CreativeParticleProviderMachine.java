package com.gtt.gttcore.common.machine;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IDropSaveMachine;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gtt.gttcore.api.capability.IParticleInfoProvider;
import com.gtt.gttcore.api.gui.PhantomParticleWidget;
import com.gtt.gttcore.api.machine.trait.InfiniteParticleContainer;
import com.gtt.gttcore.api.particle.ParticleStack;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;
import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.widget.*;
import net.minecraft.nbt.CompoundTag;

public class CreativeParticleProviderMachine extends MetaMachine implements IFancyUIMachine, IParticleInfoProvider, IDropSaveMachine {
    private final InfiniteParticleContainer particleContainer;

    public CreativeParticleProviderMachine(BlockEntityCreationInfo info) {
        super(info);
        particleContainer = attachTrait(new InfiniteParticleContainer(1));
    }

    public void setActive(boolean active) {
        particleContainer.enabled = active;
    }

    @Override
    public ParticleStack getStored() {
        return particleContainer.getStored();
    }

    @Override
    public int getCapacity() {
        return particleContainer.capacity;
    }

    @Override
    public void saveToItem(CompoundTag tag) {
        tag.putInt("amountPerCycle", particleContainer.capacity);
        tag.putInt("ticksPerCycle", particleContainer.getTicksPerCycle());
    }

    @Override
    public void loadFromItem(CompoundTag tag) {
        particleContainer.capacity = tag.getInt("amountPerCycle");
        particleContainer.setTicksPerCycle(tag.getInt("ticksPerCycle"));
    }

    @Override
    public WidgetGroup createUIWidget() {
        var group = new WidgetGroup(0, 0, 176, 131);
        group.addWidget(new PhantomParticleWidget(particleContainer, 36, 6, particleContainer::getStored, particleContainer::setStored)
                .setBackground(GuiTextures.FLUID_SLOT));
        group.addWidget(new LabelWidget(7, 9, "gttcore.creative.particle"));
        group.addWidget(new ImageWidget(7, 45, 154, 14, GuiTextures.DISPLAY));
        group.addWidget(new TextFieldWidget(9, 47, 152, 10, () -> String.valueOf(particleContainer.capacity), this::setAmountPerCycle)
                .setMaxStringLength(11)
                .setNumbersOnly(1, Integer.MAX_VALUE));
        group.addWidget(new LabelWidget(7, 28, "gttcore.creative.particle.amountpc"));
        group.addWidget(new ImageWidget(7, 82, 154, 14, GuiTextures.DISPLAY));
        group.addWidget(new TextFieldWidget(9, 84, 152, 10, () -> String.valueOf(particleContainer.getTicksPerCycle()), this::setTicksPerCycle)
                .setMaxStringLength(11)
                .setNumbersOnly(1, Integer.MAX_VALUE));
        group.addWidget(new LabelWidget(7, 65, "gtceu.creative.tank.tpc"));
        group.addWidget(new SwitchWidget(7, 101, 162, 20, (clickData, value) -> setActive(value))
                .setTexture(
                        new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON,
                                new TextTexture("gtceu.creative.activity.off")),
                        new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON,
                                new TextTexture("gtceu.creative.activity.on")))
                .setPressed(isActive()));

        return group;
    }

    private void setTicksPerCycle(String value) {
        if (value.isEmpty()) return;
        particleContainer.setTicksPerCycle(Integer.parseInt(value));
    }

    private void setAmountPerCycle(String value) {
        if (value.isEmpty()) return;
        particleContainer.capacity = Integer.parseInt(value);
    }

    public boolean isActive() {
        return particleContainer.enabled;
    }
}
