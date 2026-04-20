package com.gtt.gttcore.api.content;

import com.gregtechceu.gtceu.api.recipe.content.IContentSerializer;
import com.gtt.gttcore.api.particle.ParticleStack;
import com.mojang.serialization.Codec;
import net.minecraft.network.FriendlyByteBuf;

public class SerializerParticleStack implements IContentSerializer<ParticleStack> {
    public static SerializerParticleStack INSTANCE = new SerializerParticleStack();

    private SerializerParticleStack() {}

    @Override
    public void toNetwork(FriendlyByteBuf buf, ParticleStack content) {
        content.toNetwork(buf);
    }

    @Override
    public ParticleStack fromNetwork(FriendlyByteBuf buf) {
        return ParticleStack.fromNetwork(buf);
    }

    @Override
    public ParticleStack of(Object o) {
        if (o instanceof ParticleStack stack) {
            return stack;
        } else {
            return ParticleStack.EMPTY;
        }
    }

    @Override
    public ParticleStack defaultValue() {
        return ParticleStack.EMPTY;
    }

    @Override
    public Class<ParticleStack> contentClass() {
        return ParticleStack.class;
    }

    @Override
    public Codec<ParticleStack> codec() {
        return ParticleStack.CODEC;
    }
}
