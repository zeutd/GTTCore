package com.gtt.gttcore.mixin.appeng;

import appeng.items.storage.BasicStorageCell;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BasicStorageCell.class)
public class BasicStorageCellMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 2, argsOnly = true)
    private static int modifyTotalTypes(int value){
        return Integer.MAX_VALUE;
    }
}
