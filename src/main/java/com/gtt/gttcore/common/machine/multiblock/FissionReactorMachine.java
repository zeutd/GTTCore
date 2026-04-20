package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.api.sync_system.annotations.RerenderOnChanged;
import com.gregtechceu.gtceu.api.sync_system.annotations.SyncToClient;
import com.gtt.gttcore.api.machine.trait.multiblock.MultiblockFluidBoxRendererTrait;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.jetbrains.annotations.NotNull;

public class FissionReactorMachine extends WorkableElectricMultiblockMachine {

    @Getter
    @SyncToClient
    @RerenderOnChanged
    private final MultiblockFluidBoxRendererTrait boxTrait;

    public FissionReactorMachine(BlockEntityCreationInfo info) {
        super(info);
        boxTrait = attachTrait(new MultiblockFluidBoxRendererTrait(this::saveBox));
    }

    @NotNull
    public BoundingBox saveBox() {
        Direction up = RelativeDirection.UP.getRelative(getFrontFacing(), getUpwardsFacing(), isFlipped());
        Direction back = getFrontFacing().getOpposite();
        Direction clockWise = RelativeDirection.RIGHT.getRelative(getFrontFacing(), getUpwardsFacing(), isFlipped());

        BlockPos a = BlockPos.ZERO.relative(back).relative(up, -1).relative(clockWise, -4);
        BlockPos b = BlockPos.ZERO.relative(back, 9).relative(up, 10).relative(clockWise, 4);

        int minX = Math.min(a.getX(), b.getX());
        int minY = Math.min(a.getY(), b.getY());
        int minZ = Math.min(a.getZ(), b.getZ());
        int maxX = Math.max(a.getX(), b.getX());
        int maxY = Math.max(a.getY(), b.getY());
        int maxZ = Math.max(a.getZ(), b.getZ());

        return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }
}
