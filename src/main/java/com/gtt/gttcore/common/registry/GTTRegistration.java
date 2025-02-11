package com.gtt.gttcore.common.registry;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTCreativeModeTabs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import static com.gregtechceu.gtceu.common.registry.GTRegistration.REGISTRATE;

public class GTTRegistration {
    public static final GTRegistrate REGISTRATE = GTRegistrate.create(GTTCore.MOD_ID);
    static {
        REGISTRATE.creativeModeTab(() -> GTTCreativeModeTabs.GTT_TAB);
    }
}
