package com.gtt.gttcore.api.particle;

import lombok.Getter;
import net.minecraft.resources.ResourceLocation;


public class ParticleType {
    @Getter
    private final ResourceLocation id;
    public int color;

    public String englishName;
    public String chineseName;

    public ParticleType(ResourceLocation id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "ParticleType: " + id.toString();
    }
}
