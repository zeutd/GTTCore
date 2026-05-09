package com.gtt.gttcore.api.particle;

import lombok.Getter;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;


public class ParticleType {
    @Getter
    private final ResourceLocation id;
    public int color;

    public String englishName;
    public String chineseName;
    public String descriptionId;

    public ParticleType(ResourceLocation id){
        this.id = id;
    }

    public Component getDisplayName(){
        return Component.translatable(this.getDescriptionId());
    }

    public String getDescriptionId(){
        return getOrCreateDescriptionId();
    }

    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("particleType", id);
        }

        return this.descriptionId;
    }
}
