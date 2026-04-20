package com.gtt.gttcore.api.particle;

import com.gtt.gttcore.api.registry.GTTRegistries;
import net.minecraft.resources.ResourceLocation;

public class ParticleTypeBuilder {

    public ResourceLocation id;

    public int color;

    public String langValue;
    public String chineseLangValue;

    public ParticleTypeBuilder(ResourceLocation id){
        this.id = id;
    }

    public ParticleTypeBuilder color(int color){
        this.color = color;
        return this;
    }

    public ParticleTypeBuilder langValue(String name){
        this.langValue = name;
        return this;
    }

    public ParticleTypeBuilder chineseLangValue(String name){
        this.chineseLangValue = name;
        return this;
    }

    public ParticleType register(){
        var particleType = new ParticleType(id);
        particleType.color = color;
        particleType.englishName = langValue;
        particleType.chineseName = chineseLangValue;
        GTTRegistries.PARTICLE_TYPES.register(id, particleType);
        return particleType;
    }
}
