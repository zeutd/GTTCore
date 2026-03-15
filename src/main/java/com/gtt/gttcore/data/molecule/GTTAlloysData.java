package com.gtt.gttcore.data.molecule;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gtt.gttcore.common.data.GTTMaterials;
import net.minecraft.resources.ResourceLocation;
import oshi.util.tuples.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GTTAlloysData {
    public static Map<ResourceLocation, Optional<List<Pair<ResourceLocation, Long>>>> alloys() {
        Map<ResourceLocation, Optional<List<Pair<ResourceLocation, Long>>>> alloys = new HashMap<>();
        for (Material alloy : GTTMaterials.alloysGeneratesData) {
            alloys.put(alloy.getResourceLocation(), Optional.empty());
        }
        return alloys;
    }
}
