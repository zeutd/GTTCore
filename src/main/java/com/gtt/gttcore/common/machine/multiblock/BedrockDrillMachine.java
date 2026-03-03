package com.gtt.gttcore.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.BedrockOreMinerMachine;
import com.gtt.gttcore.common.CustomNetherTeleporter;
import com.lowdragmc.lowdraglib.gui.util.ClickData;
import com.lowdragmc.lowdraglib.gui.widget.ComponentPanelWidget;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BedrockDrillMachine extends BedrockOreMinerMachine {
    public BedrockDrillMachine(BlockEntityCreationInfo info, int tier) {
        super(info, tier);
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if (!this.isFormed) return;
        textList.add(
                ComponentPanelWidget.withButton(Component.translatable("gttcore.go_to_nether"),
                        "set_out"));
    }

    @Override
    public void handleDisplayClick(String componentData, ClickData clickData) {
        super.handleDisplayClick(componentData, clickData);
        if (getLevel() instanceof ServerLevel serverLevel) {
            if (serverLevel.dimension() != Level.OVERWORLD) return;
            if (componentData.equals("set_out")) {
                final BlockPos pos = getBlockPos();
                List<ServerPlayer> entities = serverLevel.getEntitiesOfClass(ServerPlayer.class, new AABB(pos.getX() - 2,
                        pos.getY() - 2,
                        pos.getZ() - 2,
                        pos.getX() + 2,
                        pos.getY() + 2,
                        pos.getZ() + 2));
                ServerLevel netherLevel = serverLevel.getServer().getLevel(Level.NETHER);
                if (netherLevel != null) {
                    for (ServerPlayer player : entities) {
                        if (player != null) {
                            player.changeDimension(netherLevel, new CustomNetherTeleporter(netherLevel));
                        }
                    }
                }
            }
        }
    }
}
