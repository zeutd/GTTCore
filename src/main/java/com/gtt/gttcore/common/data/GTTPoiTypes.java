package com.gtt.gttcore.common.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class GTTPoiTypes {
    public static final ResourceKey<PoiType> NETHER_TRAVELLER = createKey("nether_traveller");

    private static final Set<BlockState> NETHER_TRAVELLER_BLOCK_STATES = ImmutableList.of(GTTMultiMachines.NETHER_CAPSULE.getBlock(), GTTMultiMachines.BEDROCK_DRILL.getBlock()).stream().flatMap((p_218097_) -> {
        return p_218097_.getStateDefinition().getPossibleStates().stream();
    }).collect(ImmutableSet.toImmutableSet());

    private static Set<BlockState> getBlockStates(Block pBlock) {
        return ImmutableSet.copyOf(pBlock.getStateDefinition().getPossibleStates());
    }

    private static ResourceKey<PoiType> createKey(String pName) {
        return ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation(pName));
    }

    private static PoiType register(Registry<PoiType> pKey, ResourceKey<PoiType> pValue, Set<BlockState> pMatchingStates, int pMaxTickets, int pValidRange) {
        PoiType poitype = new PoiType(pMatchingStates, pMaxTickets, pValidRange);
        Registry.register(pKey, pValue, poitype);
        registerBlockStates(pKey.getHolderOrThrow(pValue), pMatchingStates);
        return poitype;
    }

    private static void registerBlockStates(Holder<PoiType> pPoi, Set<BlockState> pStates) {
    }

    public static PoiType bootstrap(Registry<PoiType> pRegistry) {
        return register(pRegistry, NETHER_TRAVELLER, NETHER_TRAVELLER_BLOCK_STATES, 0, 1);
    }
}
