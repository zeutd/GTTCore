package com.gtt.gttcore.common.registry;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.data.GTTCreativeModeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.DeferredRegister;

public class GTTRegistration {
    public static final GTRegistrate REGISTRATE = GTRegistrate.create(GTTCore.MOD_ID);
    static {
        REGISTRATE.creativeModeTab(() -> GTTCreativeModeTabs.GTT_TAB);
    }

    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, GTTCore.MOD_ID);
}
