package com.gtt.gttcore;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = GTTCore.MOD_ID)
public class GTTConfigHolder {

    public static GTTConfigHolder INSTANCE;
    private static final Object LOCK = new Object();

    public static void init() {
        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Configuration.registerConfig(GTTConfigHolder.class, ConfigFormats.yaml()).getConfigInstance();
            }
        }
    }

    @Configurable
    @Configurable.Comment("The duration multiplier of recipes")
    public float recipeDurationMultiplier = 1;
}
