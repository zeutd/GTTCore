package com.gtt.gttcore.data.molecule;

import com.google.common.hash.HashCode;
import com.rubenverg.moldraw.molecule.Molecule;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import static com.rubenverg.moldraw.MolDraw.gson;

public class GTTMoleculeProvider {
    public void gatherData(GatherDataEvent event) {
        final var gen = event.getGenerator();

        gen.addProvider(event.includeClient(), new DataProvider.Factory<>() {

            @Override
            public @NotNull DataProvider create(@NotNull PackOutput output) {
                final var moleculesPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK,
                        "molecules");
                final var alloysPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "alloys");
                return new DataProvider() {

                    @Override
                    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cachedOutput) {
                        MolfileToMolecule.run(event.getExistingFileHelper(), (resourceLocation, molecule) -> {
                            final var json = gson.toJson(molecule, Molecule.class);
                            try {
                                cachedOutput.writeIfNeeded(moleculesPathProvider.json(resourceLocation),
                                        json.getBytes(StandardCharsets.UTF_8), HashCode.fromInt(json.hashCode()));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        for (final var entry : GTTMoleculesData.molecules().entrySet()) {
                            final var json = gson.toJson(entry.getValue(), Molecule.class);
                            try {
                                cachedOutput.writeIfNeeded(moleculesPathProvider.json(entry.getKey()),
                                        json.getBytes(StandardCharsets.UTF_8), HashCode.fromInt(json.hashCode()));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        return CompletableFuture.completedFuture(null);
                    }

                    @Override
                    public @NotNull String getName() {
                        return "GTT Molecules provider";
                    }
                };
            }
        });
    }
}
