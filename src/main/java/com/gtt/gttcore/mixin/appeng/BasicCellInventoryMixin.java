package com.gtt.gttcore.mixin.appeng;

import appeng.me.cells.BasicCellInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BasicCellInventory.class)
public class BasicCellInventoryMixin {
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 63))
    private int modifyMaxItemTypes(int value) {
        return Integer.MAX_VALUE;
    }
}
