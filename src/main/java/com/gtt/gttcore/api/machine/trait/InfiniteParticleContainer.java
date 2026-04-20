package com.gtt.gttcore.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import net.minecraft.core.Direction;

public class InfiniteParticleContainer extends NotifiableParticleContainer {

    public InfiniteParticleContainer(int maxAmount) {
        super(IO.OUT, maxAmount);
    }

    @Override
    public void autoIO() {
        stored.setAmount(capacity);
        super.autoIO();
    }

    @Override
    public void importFromNearby(Direction... facings) {

    }

    @Override
    public int extractParticle(int amountToExtract) {
        return 0;
    }
}
