package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.api.registry.GTRegistries;

public class GTTElements {
    public static Element
            Ed = createAndRegister(120, 240, -1, null, "ElectronDegeneratium", "Ed", false);

    public static Element createAndRegister(long protons, long neutrons, long halfLifeSeconds, String decayTo, String name, String symbol, boolean isIsotope) {
        GTRegistries.ELEMENTS.unfreeze();
        Element element = new Element(protons, neutrons, halfLifeSeconds, decayTo, name, symbol, isIsotope);
        GTRegistries.ELEMENTS.register(name, element);
        return element;
    }
}
