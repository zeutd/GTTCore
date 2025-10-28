package com.gtt.gttcore.common.machine;

import com.gtt.gttcore.api.machine.trait.NotifiableHighEnergyLaserContainer;

public interface IHighEnergyLaserProvider extends IHighEnergyLaserHatch{
    int getLaserAmount();

    void setLaserAmount(int amount);

    default void addLaserAmount(int amount){
        setLaserAmount(getLaserAmount() + amount);
    }
}
