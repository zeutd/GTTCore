package com.gtt.gttcore.common.block;

import com.gregtechceu.gtceu.common.block.explosive.GTExplosiveBlock;
import com.gregtechceu.gtceu.common.entity.GTExplosiveEntity;
import com.gtt.gttcore.common.entity.NuclearBombEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NuclearBombBlock extends GTExplosiveBlock {

    public NuclearBombBlock(Properties properties) {
        super(properties, true, false, 80);
    }

    @Override
    protected GTExplosiveEntity createEntity(@NotNull Level world, @NotNull BlockPos pos, @Nullable LivingEntity exploder) {
        float x = pos.getX() + 0.5F, y = pos.getY(), z = pos.getZ() + 0.5F;
        return new NuclearBombEntity(world, x, y, z, exploder);
    }
}
