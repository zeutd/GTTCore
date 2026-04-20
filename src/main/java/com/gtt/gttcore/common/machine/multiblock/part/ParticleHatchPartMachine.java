package com.gtt.gttcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.api.sync_system.annotations.SyncToClient;
import com.gtt.gttcore.api.capability.IParticleContainer;
import com.gtt.gttcore.api.capability.IParticleInfoProvider;
import com.gtt.gttcore.api.gui.ParticleWidget;
import com.gtt.gttcore.api.machine.trait.NotifiableParticleContainer;
import com.gtt.gttcore.api.particle.ParticleStack;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import lombok.Getter;
import net.minecraft.core.Direction;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public class ParticleHatchPartMachine extends TieredIOPartMachine implements IParticleContainer, IParticleInfoProvider {

    @Getter
    @SaveField
    @SyncToClient
    protected NotifiableParticleContainer particleContainer;

    public ParticleHatchPartMachine(BlockEntityCreationInfo info, int tier, IO io) {
        super(info, tier, io);
        this.particleContainer = attachTrait(new NotifiableParticleContainer(io, 2000 * tier));
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }



    @Override
    public Widget createUIWidget() {
        return new WidgetGroup(0, 0, 18 * 2 + 18, 18 * 2 + 18).addWidget(new ParticleWidget(particleContainer, 18, 18));
    }

    @Override
    public int acceptParticle(ParticleStack stack) {
        return particleContainer.acceptParticle(stack);
    }

    @Override
    public int extractParticle(int amount) {
        return particleContainer.extractParticle(amount);
    }

    @Override
    public ParticleStack getStored() {
        return particleContainer.stored;
    }

    @Override
    public int getCapacity() {
        return particleContainer.capacity;
    }

    @Override
    public boolean canInput(Direction face) {
        return particleContainer.handlerIO.support(IO.IN);
    }

    @Override
    public boolean canOutput(Direction face) {
        return particleContainer.handlerIO.support(IO.OUT);
    }
}
