package com.gtt.gttcore.mixin.gtm;

import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.pattern.MultiblockState;
import com.gregtechceu.gtceu.api.pattern.MultiblockWorldSavedData;
import com.gtt.gttcore.api.ICheckPattern;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;

import java.util.concurrent.locks.Lock;

@Mixin(value = MultiblockControllerMachine.class, remap = false)
public abstract class MultiblockControllerMachineMixin implements ICheckPattern {
    @Shadow @NotNull public abstract MultiblockState getMultiblockState();
    @Shadow protected boolean isFormed;
    @Shadow @Final private Lock patternLock;

    @Unique
    private boolean gTTCore$checkStructureButtonToggled = true;


    /**
     * @author zeutd
     * @reason checkPattern
     */
    @Overwrite
    public void asyncCheckPattern(long periodID) {
        MultiblockControllerMachine self = (MultiblockControllerMachine)(Object) this;
        if (gTTCore$isCheckStructureButtonToggled()) {
            if ((getMultiblockState().hasError() || !isFormed) && (self.getHolder().getOffset() + periodID) % 4 == 0 &&
                    self.checkPatternWithTryLock()) { // per second
                if (self.getLevel() instanceof ServerLevel serverLevel) {
                    serverLevel.getServer().execute(() -> {
                        patternLock.lock();
                        if (self.checkPatternWithLock()) { // formed
                            self.setFlipped(getMultiblockState().isNeededFlip());
                            self.onStructureFormed();
                            var mwsd = MultiblockWorldSavedData.getOrCreate(serverLevel);
                            mwsd.addMapping(getMultiblockState());
                            mwsd.removeAsyncLogic(self);
                            gTTCore$setCheckStructureButtonToggled(false);
                        }
                        patternLock.unlock();
                    });
                }
            }
        }
    }

    @Override
    public boolean gTTCore$isCheckStructureButtonToggled() {
        return gTTCore$checkStructureButtonToggled;
    }

    @Override
    public void gTTCore$setCheckStructureButtonToggled(boolean newValue) {
        gTTCore$checkStructureButtonToggled = newValue;
    }
}
