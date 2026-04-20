package com.gtt.gttcore.client.models;

import com.gregtechceu.gtceu.api.block.PipeBlock;
import com.gregtechceu.gtceu.api.registry.registrate.provider.GTBlockstateProvider;
import com.gregtechceu.gtceu.client.model.pipe.PipeModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import org.jetbrains.annotations.NotNull;

public class ParticlePipeModel extends PipeModel {

    private BlockModelBuilder connectionElement;

    public ParticlePipeModel(PipeBlock<?, ?, ?> block, float thickness, ResourceLocation side, ResourceLocation end,
                             GTBlockstateProvider provider) {
        super(block, provider, thickness, side, end);
    }

    @Override
    protected @NotNull BlockModelBuilder getOrCreateConnectionElement() {
        if (this.connectionElement != null) {
            return this.connectionElement;
        }
        return this.connectionElement = makeElementModel(
                this.blockId.withPath(path -> "block/pipe/" + path + "/connection"),
                Direction.DOWN, 0, 0, 0, 16, minCoord, 16);
    }
}
