package com.gtt.gttcore.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InfiniteHighEnergyLaserContainer extends NotifiableHighEnergyLaserContainer{

    private int maxLaser;

    public InfiniteHighEnergyLaserContainer(int maxLaser){
        super(IO.OUT, true);
        this.maxLaser = maxLaser;
    }

    @Override
    public boolean isTransmitter() {
        return true;
    }

    @Override
    public void outputTick() {
        laserAmount = maxLaser;
        super.outputTick();
    }

    @Override
    public void setLaserAmount(int amount) {

    }
}
