package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.api.registry.GTRegistries;

public class GTTElements {
    public static Element
            De = createAndRegister(27, 32, -1, null, "Desh", "De", false),
            Om = createAndRegister(82, 125, -1, null, "Ostrum", "Om", false),
            Ct = createAndRegister(79, 118, -1, null, "Calorite", "Ct", false)
                    ;
    public static void init(){

    }
    public static Element createAndRegister(long protons, long neutrons, long halfLifeSeconds, String decayTo, String name, String symbol, boolean isIsotope) {
        Element element = new Element(protons, neutrons, halfLifeSeconds, decayTo, name, symbol, isIsotope);
        GTRegistries.ELEMENTS.register(name, element);
        return element;
    }
}
