package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IWorkableMultiController;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public interface IFluidBoxRenderMulti extends IWorkableMultiController, IMachineFeature {
    @ApiStatus.NonExtendable
    default BoundingBox getFluidBox() {
        BoundingBox box = getFluidBlockBox();
        if (self().isFormed()) {
            box = saveBox();
            setFluidBlockBox(box);
        }
        return box;
    }

    @ApiStatus.OverrideOnly
    @NotNull
    BoundingBox getFluidBlockBox();

    @ApiStatus.Internal
    void setFluidBlockBox(@NotNull BoundingBox box);

    default void onStructureFormed() {
        saveBox();
    }

    @NotNull
    BoundingBox saveBox();
}
