package com.gtt.gttcore.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class GTTLangHandler {
    public static void init(RegistrateLangProvider provider) {
        provider.add("gttcore.machine.high_energy_laser_amount", "Laser Amount: %s");
        provider.add("gttcore.machine.high_energy_laser_amount_input", "Laser Amount Input: %s");
        provider.add("gttcore.machine.high_energy_laser_amount_output", "Laser Amount Output: %s");
    }
}
