package com.gtt.gttcore.api.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gtt.gttcore.api.registry.GTTRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import net.minecraft.network.FriendlyByteBuf;

@Getter
@With
@AllArgsConstructor
public class ParticleStack {
    public static final ParticleStack EMPTY = new ParticleStack(null, 0);
    public static final Codec<ParticleStack> CODEC = RecordCodecBuilder.create(
            (instance) -> instance.group(
                    GTTRegistries.PARTICLE_TYPES.codec().fieldOf("id").forGetter(ParticleStack::getType),
                    Codec.INT.fieldOf("Amount").forGetter(ParticleStack::getAmount)
            ).apply(instance, ParticleStack::new));

    private final ParticleType type;

    @Setter
    private int amount;

    public static ParticleStack of(Object o){
        if (o instanceof ParticleStack stack) {
            return stack;
        } else if (o instanceof ParticleType type) {
            return new ParticleStack(type, 1);
        } else if (o instanceof JsonElement) {

        }
        return EMPTY;
    }

    public ParticleStack copy(){
        return new ParticleStack(type, amount);
    }

    public ParticleStack copyWithAmount(int amount){
        return new ParticleStack(type, amount);
    }

    public void addAmount(int amount){
        this.amount += amount;
    }

    public boolean isEmpty(){
        return type == null || amount <= 0;
    }

    public boolean is(ParticleType type) {
        return this.getType() == type;
    }

    public void toNetwork(FriendlyByteBuf buf) {
        GTTRegistries.PARTICLE_TYPES.writeBuf(type, buf);
        buf.writeVarInt(amount);
    }

    public static ParticleStack fromNetwork(FriendlyByteBuf buf) {
        return new ParticleStack(GTTRegistries.PARTICLE_TYPES.readBuf(buf), buf.readVarInt());
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("amount", this.amount);
        jsonObject.addProperty("type", GTTRegistries.PARTICLE_TYPES.getKey(type).toString());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "ParticleStack: " + amount + " " + type.toString();
    }
}
