package com.gtt.gttcore.common.machine;

import com.gtt.gttcore.api.capability.IHighEnergyLaserInfoProvider;

public interface IHighEnergyLaserProvider extends IHighEnergyLaserHatch, IHighEnergyLaserInfoProvider {

    void setLaserAmount(int amount);

    default void addLaserAmount(int amount){
        setLaserAmount(getLaserAmount() + amount);
    }
}
