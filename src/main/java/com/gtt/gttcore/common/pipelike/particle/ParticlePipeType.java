package com.gtt.gttcore.common.pipelike.particle;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.pipenet.IPipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum ParticlePipeType implements IPipeType<ParticlePipeProperties>, StringRepresentable {

    NORMAL;

    public static final ResourceLocation TYPE_ID = GTCEu.id("particle");

    @Override
    public float getThickness() {
        return 0.75f;
    }

    @Override
    public ParticlePipeProperties modifyProperties(ParticlePipeProperties baseProperties) {
        return baseProperties;
    }

    @Override
    public boolean isPaintable() {
        return true;
    }

    @Override
    public ResourceLocation type() {
        return TYPE_ID;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
