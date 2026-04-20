package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.gtt.gttcore.GTTCore;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;

import static com.gtt.gttcore.api.registry.GTTRegistration.REGISTRATE;

public class GTTCreativeModeTabs {
    public static RegistryEntry<CreativeModeTab> GTT_TAB = REGISTRATE.defaultCreativeTab("gtt_tab",
                    builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("gtt_tab", REGISTRATE))
                            .icon(() -> GTTItems.PACKAGED_CIRCUIT_MAX.asStack())
                            .title(REGISTRATE.addLang("itemGroup", GTTCore.id("gtt_tab"),
                                    GTTCore.NAME))
                            .build())
            .register();
}
