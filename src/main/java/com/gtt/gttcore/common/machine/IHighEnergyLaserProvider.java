package com.gtt.gttcore.common.machine;

import com.gtt.gttcore.api.machine.trait.NotifiableHighEnergyLaserContainer;

public interface IHighEnergyLaserProvider {
    long getLaserAmount();

    void setLaserAmount(long amount);

    boolean isTransmitter();
}
