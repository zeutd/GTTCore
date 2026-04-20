package com.gtt.gttcore.common.item;

import com.gregtechceu.gtceu.api.block.PipeBlock;
import com.gregtechceu.gtceu.api.item.PipeBlockItem;
import com.gtt.gttcore.common.block.ParticlePipeBlock;
import net.minecraft.client.color.item.ItemColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticlePipeBlockItem extends PipeBlockItem {

    public ParticlePipeBlockItem(PipeBlock block, Properties properties) {
        super(block, properties);
    }

    @Override
    public ParticlePipeBlock getBlock() {
        return (ParticlePipeBlock) super.getBlock();
    }

    @OnlyIn(Dist.CLIENT)
    public static ItemColor tintColor() {
        return (itemStack, index) -> {
            if (itemStack.getItem() instanceof ParticlePipeBlockItem materialBlockItem) {
                return ParticlePipeBlock.tintedColor().getColor(materialBlockItem.getBlock().defaultBlockState(), null,
                        null, index);
            }
            return -1;
        };
    }
}