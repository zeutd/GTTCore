package com.gtt.gttcore.common.block;

import com.gregtechceu.gtceu.api.block.PipeBlock;
import com.gregtechceu.gtceu.api.blockentity.PipeBlockEntity;
import com.gregtechceu.gtceu.api.capability.GTCapability;
import com.gregtechceu.gtceu.api.pipenet.IPipeNode;
import com.gregtechceu.gtceu.api.registry.registrate.provider.GTBlockstateProvider;
import com.gregtechceu.gtceu.client.model.pipe.PipeModel;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.client.models.ParticlePipeModel;
import com.gtt.gttcore.common.blockentity.ParticlePipeBlockEntity;
import com.gtt.gttcore.common.data.GTTBlockEntities;
import com.gtt.gttcore.common.pipelike.particle.LevelParticlePipeNet;
import com.gtt.gttcore.common.pipelike.particle.ParticlePipeProperties;
import com.gtt.gttcore.common.pipelike.particle.ParticlePipeType;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ParticlePipeBlock extends PipeBlock<ParticlePipeType, ParticlePipeProperties, LevelParticlePipeNet> {

    private final ParticlePipeProperties properties;

    public ParticlePipeBlock(Properties properties, ParticlePipeType type) {
        super(properties, type);
        this.properties = ParticlePipeProperties.INSTANCE;

        registerDefaultState(defaultBlockState());
    }

    @OnlyIn(Dist.CLIENT)
    public static BlockColor tintedColor() {
        return (state, level, pos, index) -> {
            if (pos != null && level != null &&
                    level.getBlockEntity(pos) instanceof PipeBlockEntity<?, ?> pipe) {
                if (!pipe.getFrameMaterial().isNull()) {
                    if (index == 3) {
                        return pipe.getFrameMaterial().getMaterialRGB();
                    } else if (index == 4) {
                        return pipe.getFrameMaterial().getMaterialSecondaryRGB();
                    }
                }
                if (pipe.isPainted()) {
                    return pipe.getRealColor();
                }
            }
            return -1;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }

    @Override
    public LevelParticlePipeNet getWorldPipeNet(ServerLevel world) {
        return LevelParticlePipeNet.getOrCreate(world);
    }

    @Override
    public BlockEntityType<? extends PipeBlockEntity<ParticlePipeType, ParticlePipeProperties>> getBlockEntityType() {
        return GTTBlockEntities.PARTICLE_PIPE.get();
    }

    @Override
    public ParticlePipeProperties createRawData(BlockState pState, @Nullable ItemStack pStack) {
        return ParticlePipeProperties.INSTANCE;
    }

    @Override
    public ParticlePipeProperties createProperties(IPipeNode<ParticlePipeType, ParticlePipeProperties> pipeTile) {
        ParticlePipeType pipeType = pipeTile.getPipeType();
        if (pipeType == null) return getFallbackType();
        return this.pipeType.modifyProperties(properties);
    }

    @Override
    public ParticlePipeProperties getFallbackType() {
        return ParticlePipeProperties.INSTANCE;
    }

    @Override
    public PipeModel createPipeModel(GTBlockstateProvider provider) {
        return new ParticlePipeModel(this, ParticlePipeType.NORMAL.getThickness(),
                GTTCore.id("block/pipe/pipe_particle_side"), GTTCore.id("block/pipe/pipe_particle_in"),
                provider);
    }

    @Override
    public boolean canPipesConnect(IPipeNode<ParticlePipeType, ParticlePipeProperties> selfTile, Direction side,
                                   IPipeNode<ParticlePipeType, ParticlePipeProperties> sideTile) {
        return selfTile instanceof ParticlePipeBlockEntity && sideTile instanceof ParticlePipeBlockEntity;
    }

    @Override
    public boolean canPipeConnectToBlock(IPipeNode<ParticlePipeType, ParticlePipeProperties> selfTile, Direction side,
                                         @Nullable BlockEntity tile) {
        return tile != null && tile.getCapability(GTCapability.CAPABILITY_LASER, side.getOpposite()).isPresent();
    }
}

