package com.gtt.gttcore.data;

import com.gtt.gttcore.GTTCore;
import com.mojang.serialization.JsonOps;
import earth.terrarium.adastra.AdAstra;
import earth.terrarium.adastra.api.planets.Planet;
import earth.terrarium.adastra.common.constants.PlanetConstants;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class GTTPlanetProvider implements DataProvider {
    public static final ResourceKey<Registry<Planet>> PLANET_REGISTRY = ResourceKey.createRegistryKey(new ResourceLocation(AdAstra.MOD_ID, "planets"));

    private final PackOutput.PathProvider pathProvider;
    public GTTPlanetProvider(PackOutput packOutput){
        pathProvider = packOutput.createPathProvider(PackOutput.Target.DATA_PACK, PLANET_REGISTRY.location().getPath());
    }
    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        build((key, value) ->
                futures.add(DataProvider.saveStable(
                        output,
                        Planet.CODEC.encodeStart(JsonOps.INSTANCE, value).getOrThrow(false, GTTCore.LOGGER::error),
                        pathProvider.json(key)
                ))
        );

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    public void build(BiConsumer<ResourceLocation, Planet> consumer) {
        consumer.accept(
                GTTCore.id("end"),
                new Planet(
                        Level.END,
                        false,
                        PlanetConstants.SPACE_TEMPERATURE,
                        0.98f,
                        0,
                        PlanetConstants.SOLAR_SYSTEM,
                        Optional.of(Level.END),
                        4,
                        List.of()
                )
        );
    }

    @Override
    public String getName() {
        return "GTT Planets";
    }
}
