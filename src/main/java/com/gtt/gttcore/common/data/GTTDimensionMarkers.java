package com.gtt.gttcore.common.data;

import com.gregtechceu.gtceu.api.data.DimensionMarker;
import earth.terrarium.adastra.api.planets.Planet;
import earth.terrarium.adastra.common.registry.ModItems;

import static com.gregtechceu.gtceu.common.data.GTDimensionMarkers.createAndRegister;

public class GTTDimensionMarkers {
    public static void init(){

    }
    public static final DimensionMarker MOON = createAndRegister(Planet.MOON.location(), 1,
            ModItems.MOON_STONE::get, null);
    public static final DimensionMarker MARS = createAndRegister(Planet.MARS.location(), 2,
            ModItems.MARS_STONE::get, null);
    public static final DimensionMarker VENUS = createAndRegister(Planet.VENUS.location(), 3,
            ModItems.VENUS_STONE::get, null);
    public static final DimensionMarker MERCURY = createAndRegister(Planet.MERCURY.location(), 3,
            ModItems.MERCURY_STONE::get, null);
    public static final DimensionMarker GLACIO = createAndRegister(Planet.GLACIO.location(), 4,
            ModItems.GLACIO_STONE::get, null);
}
