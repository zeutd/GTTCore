package com.gtt.gttcore.util;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.IElectricItem;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.common.machine.electric.BatteryBufferMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.PowerSubstationMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.hepdd.gtmthings.api.misc.EnergyStat;
import com.hepdd.gtmthings.api.misc.ITransferData;
import com.hepdd.gtmthings.api.misc.WirelessEnergyContainer;
import com.hepdd.gtmthings.common.item.IWirelessMonitor;
import com.hepdd.gtmthings.config.ConfigHolder;
import com.hepdd.gtmthings.utils.BigIntegerUtils;
import com.hepdd.gtmthings.utils.FormatUtil;
import com.hepdd.gtmthings.utils.TeamUtil;
import com.lowdragmc.lowdraglib.gui.widget.ComponentPanelWidget;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.util.*;

public class WirelessUtil {
    public static BigInteger getCapacity(BlockGetter level, BlockPos pos) {
        BigInteger capacity = BigInteger.ZERO;
        if (level != null) {
            MetaMachine machine = MetaMachine.getMachine(level, pos);
            if (machine instanceof BatteryBufferMachine batteryBufferMachine) {
                CustomItemStackHandler inv = batteryBufferMachine.getBatteryInventory();

                for(int i = 0; i < inv.getSlots(); ++i) {
                    IElectricItem electricItem = GTCapabilityHelper.getElectricItem(inv.getStackInSlot(i));
                    if (electricItem != null) {
                        capacity = capacity.add(BigInteger.valueOf(electricItem.getMaxCharge()));
                    }
                }
            } else if (machine instanceof PowerSubstationMachine powerSubstationMachine) {
                if (powerSubstationMachine.isFormed()) {
                    capacity = capacity.add(powerSubstationMachine.getEnergyInfo().capacity());
                }
            }
        }

        return capacity;
    }

    public static List<Component> getDisplayText(IWirelessMonitor instance, boolean all, int displayTextWidth){
        List<Component> textListCache = new ArrayList<>();
        WirelessEnergyContainer container = instance.getWirelessEnergyContainer();
        if (container == null) {
            return List.of();
        } else {
            BigInteger energyTotal = container.getStorage();
            textListCache.add(Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.0", TeamUtil.GetName(instance.getLevel(), instance.getUUID())).withStyle(ChatFormatting.AQUA));
            textListCache.add(FormatUtil.formatWithConstantWidth("gtmthings.machine.wireless_energy_monitor.tooltip.1", Component.literal(FormatUtil.formatBigIntegerNumberOrSic(energyTotal))).withStyle(ChatFormatting.GOLD));
            textListCache.add(FormatUtil.formatWithConstantWidth("gttcore.machine.wireless_energy_monitor.tooltip.0", Component.literal(FormatUtil.formatBigIntegerNumberOrSic(container.getCapacity()))).withStyle(ChatFormatting.LIGHT_PURPLE));
            if (ConfigHolder.INSTANCE.isWirelessRateEnable) {
                long rate = container.getRate();
                textListCache.add(FormatUtil.formatWithConstantWidth("gtmthings.machine.wireless_energy_monitor.tooltip.2", Component.literal(FormatUtil.formatBigIntegerNumberOrSic(BigInteger.valueOf(rate))), Component.literal(String.valueOf(rate / GTValues.VEX[GTUtil.getFloorTierByVoltage(rate)])), Component.literal(GTValues.VNF[GTUtil.getFloorTierByVoltage(rate)])).withStyle(ChatFormatting.GRAY));
            }

            EnergyStat stat = container.getEnergyStat();
            textListCache.add(Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.net_power"));
            BigDecimal avgMinute = stat.getMinuteAvg();
            textListCache.add(FormatUtil.formatWithConstantWidth("gtmthings.machine.wireless_energy_monitor.tooltip.last_minute", Component.literal(FormatUtil.formatBigDecimalNumberOrSic(avgMinute)).withStyle(ChatFormatting.DARK_AQUA), Component.literal(FormatUtil.voltageAmperage(avgMinute).toEngineeringString()), FormatUtil.voltageName(avgMinute)));
            BigDecimal avgHour = stat.getHourAvg();
            textListCache.add(FormatUtil.formatWithConstantWidth("gtmthings.machine.wireless_energy_monitor.tooltip.last_hour", Component.literal(FormatUtil.formatBigDecimalNumberOrSic(avgHour)).withStyle(ChatFormatting.YELLOW), Component.literal(FormatUtil.voltageAmperage(avgHour).toEngineeringString()), FormatUtil.voltageName(avgHour)));
            BigDecimal avgDay = stat.getDayAvg();
            textListCache.add(FormatUtil.formatWithConstantWidth("gtmthings.machine.wireless_energy_monitor.tooltip.last_day", Component.literal(FormatUtil.formatBigDecimalNumberOrSic(avgDay)).withStyle(ChatFormatting.DARK_GREEN), Component.literal(FormatUtil.voltageAmperage(avgDay).toEngineeringString()), FormatUtil.voltageName(avgDay)));
            BigDecimal avgEnergy = stat.getAvgEnergy();
            textListCache.add(FormatUtil.formatWithConstantWidth("gtmthings.machine.wireless_energy_monitor.tooltip.now", Component.literal(FormatUtil.formatBigDecimalNumberOrSic(avgEnergy)).withStyle(ChatFormatting.DARK_PURPLE), Component.literal(FormatUtil.voltageAmperage(avgEnergy).toEngineeringString()), FormatUtil.voltageName(avgEnergy)));
            int compare = avgEnergy.compareTo(BigDecimal.valueOf(0L));
            BigInteger multiply = avgEnergy.abs().toBigInteger().multiply(BigInteger.valueOf(20L));
            if (compare > 0) {
                textListCache.add(Component.translatable("gtceu.multiblock.power_substation.time_to_fill", container.getCapacity() == null ? Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.time_to_fill") : getTimeToFillDrainText(container.getCapacity().subtract(energyTotal).divide(multiply))).withStyle(ChatFormatting.GRAY));
            } else if (compare < 0) {
                textListCache.add(Component.translatable("gtceu.multiblock.power_substation.time_to_drain", getTimeToFillDrainText(energyTotal.divide(multiply))).withStyle(ChatFormatting.GRAY));
            }

            if (ConfigHolder.INSTANCE.isWirelessRateEnable && container.getBindPos() != null) {
                String pos = container.getBindPos().pos().toShortString();
                textListCache.add(Component.translatable("gtmthings.machine.wireless_energy_hatch.tooltip.2", Component.translatable("recipe.condition.dimension.tooltip", container.getBindPos().dimension().location().toString()).append(" [").append(pos).append("] ")).withStyle(ChatFormatting.GRAY));
            }

            textListCache.add(Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.statistics").append(ComponentPanelWidget.withButton(all ? Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.all") : Component.translatable("gtmthings.machine.wireless_energy_monitor.tooltip.team"), "all")));

            for (Map.Entry<MetaMachine, ITransferData> m : WirelessEnergyContainer.TRANSFER_DATA.entrySet().stream().sorted(Comparator.comparingLong(entry -> entry.getValue().Throughput())).toList()) {
                UUID uuid = m.getValue().UUID();
                if (all || uuid.equals(TeamUtil.getTeamUUID(instance.getUUID()))) {
                    textListCache.add(m.getValue().getInfo());
                }
            }

            WirelessEnergyContainer.observed = true;
            WirelessEnergyContainer.TRANSFER_DATA.clear();

            return textListCache;
        }
    }
    public static Component getTimeToFillDrainText(BigInteger timeToFillSeconds) {
        if (timeToFillSeconds.compareTo(BigIntegerUtils.BIG_INTEGER_MAX_LONG) > 0) {
            timeToFillSeconds = BigIntegerUtils.BIG_INTEGER_MAX_LONG;
        }

        Duration duration = Duration.ofSeconds(timeToFillSeconds.longValue());
        String key;
        long fillTime;
        if (duration.getSeconds() <= 180L) {
            fillTime = duration.getSeconds();
            key = "gtceu.multiblock.power_substation.time_seconds";
        } else if (duration.toMinutes() <= 180L) {
            fillTime = duration.toMinutes();
            key = "gtceu.multiblock.power_substation.time_minutes";
        } else if (duration.toHours() <= 72L) {
            fillTime = duration.toHours();
            key = "gtceu.multiblock.power_substation.time_hours";
        } else if (duration.toDays() <= 730L) {
            fillTime = duration.toDays();
            key = "gtceu.multiblock.power_substation.time_days";
        } else {
            if (duration.toDays() / 365L >= 1000000L) {
                return Component.translatable("gtceu.multiblock.power_substation.time_forever");
            }

            fillTime = duration.toDays() / 365L;
            key = "gtceu.multiblock.power_substation.time_years";
        }

        return Component.translatable(key, FormattingUtil.formatNumbers(fillTime));
    }
}
