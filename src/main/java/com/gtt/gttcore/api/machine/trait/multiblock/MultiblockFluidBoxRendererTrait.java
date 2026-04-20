package com.gtt.gttcore.api.machine.trait.multiblock;

import com.gregtechceu.gtceu.api.machine.trait.MachineTraitType;
import com.gregtechceu.gtceu.api.machine.trait.multiblock.MultiblockMachineTrait;
import com.gregtechceu.gtceu.api.sync_system.annotations.RerenderOnChanged;
import com.gregtechceu.gtceu.api.sync_system.annotations.SyncToClient;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.function.Supplier;

public class MultiblockFluidBoxRendererTrait extends MultiblockMachineTrait {

    public static final MachineTraitType<MultiblockFluidBoxRendererTrait> TYPE = new MachineTraitType<>(
            MultiblockFluidBoxRendererTrait.class, false);

    @SyncToClient
    @RerenderOnChanged
    private BoundingBox fluidBox;

    private final Supplier<BoundingBox> boxGetter;

    public MultiblockFluidBoxRendererTrait(Supplier<BoundingBox> boxGetter) {
        super();
        this.boxGetter = boxGetter;
    }

    public BoundingBox getFluidBox() {
        if (fluidBox == null && getMachine().isFormed()) {
            fluidBox = boxGetter.get();
            syncDataHolder.markClientSyncFieldDirty("fluidBlockOffsets");
        }
        return fluidBox;
    }

    @Override
    public void onStructureInvalid() {
        fluidBox = null;
    }

    @Override
    public MachineTraitType<?> getTraitType() {
        return TYPE;
    }
}
