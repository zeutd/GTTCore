package com.gtt.gttcore.client.renderers;

import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.client.renderer.block.FluidBlockRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.List;
import java.util.Optional;

public class GTTDynamicRenderHelper {
    public static DynamicRender<?, ?> makeWaterBoxRender() {
        return makeFluidBoxRender(FluidBlockRenderer.Builder.create()
                .setOffset(0, -0.125f, 0)
                .setForcedLight(LightTexture.FULL_BRIGHT)
                .getRenderer(), Optional.of(Fluids.WATER.getSource()), List.of(RelativeDirection.UP, RelativeDirection.BACK,RelativeDirection.LEFT, RelativeDirection.FRONT, RelativeDirection.RIGHT));
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static DynamicRender<?, ?> makeFluidBoxRender(FluidBlockRenderer fluidBlockRenderer,
                                                          Optional<Fluid> fixedFluid,
                                                          List<RelativeDirection> drawFaces) {
        return new FluidBoxRender(fluidBlockRenderer, fixedFluid, drawFaces);
    }
}
