package com.gtt.gttcore.common.registry;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTCreativeModeTabs;

public class GTTRegistration {
    public static final GTRegistrate REGISTRATE = GTRegistrate.create(GTTCore.MOD_ID);
    static {
        REGISTRATE.creativeModeTab(() -> GTTCreativeModeTabs.GTT_TAB);
    }
}
