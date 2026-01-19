package com.gtt.gttcore.common.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.gtt.gttcore.GTTCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class GTTPoiTypes {
    private static final Set<BlockState> NETHER_TRAVELLER_BLOCK_STATES = ImmutableList.of(GTTMultiMachines.NETHER_CAPSULE.getBlock(), GTTMultiMachines.BEDROCK_DRILL.getBlock()).stream().flatMap((p_218097_) -> p_218097_.getStateDefinition().getPossibleStates().stream()).collect(ImmutableSet.toImmutableSet());

    public static PoiType NETHER_TRAVELLER = register(GTTCore.id("nether_traveller"), NETHER_TRAVELLER_BLOCK_STATES, 0, 1);

    private static PoiType register(ResourceLocation pValue, Set<BlockState> pMatchingStates, int pMaxTickets, int pValidRange) {
        PoiType poitype = new PoiType(pMatchingStates, pMaxTickets, pValidRange);
        ForgeRegistries.POI_TYPES.register(pValue, poitype);
        return poitype;
    }

    public static void init() {

    }
}
