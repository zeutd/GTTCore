package com.gtt.gttcore.client.particles;

import com.gtt.gttcore.common.data.GTTParticleTypes;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Locale;

public record MushroomCloudParticleOptions(float angle, float cx, float cy, float cz) implements ParticleOptions {
    public static final Codec<MushroomCloudParticleOptions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.FLOAT.fieldOf("angle").forGetter(particle -> particle.angle),
                    Codec.FLOAT.fieldOf("cx").forGetter(particle -> particle.cx),
                    Codec.FLOAT.fieldOf("cy").forGetter(particle -> particle.cy),
                    Codec.FLOAT.fieldOf("cz").forGetter(particle -> particle.cz)
            ).apply(instance, MushroomCloudParticleOptions::new));
    @SuppressWarnings("deprecation")
    public static final Deserializer<MushroomCloudParticleOptions> DESERIALIZER = new Deserializer<>() {

        public MushroomCloudParticleOptions fromCommand(ParticleType<MushroomCloudParticleOptions> arg,
                                                 StringReader stringReader) throws CommandSyntaxException {
            stringReader.expect(' ');
            float angle = stringReader.readFloat();
            stringReader.expect(' ');
            float cx = stringReader.readFloat();
            stringReader.expect(' ');
            float cy = stringReader.readFloat();
            stringReader.expect(' ');
            float cz = stringReader.readFloat();
            return new MushroomCloudParticleOptions(angle, cx, cy, cz);
        }

        public MushroomCloudParticleOptions fromNetwork(ParticleType<MushroomCloudParticleOptions> arg, FriendlyByteBuf arg2) {
            return new MushroomCloudParticleOptions(arg2.readFloat(), arg2.readFloat(), arg2.readFloat(), arg2.readFloat());
        }
    };

    @Override
    public ParticleType<?> getType() {
        return GTTParticleTypes.MUSHROOM_CLOUD_SMOKE_PARTICLE.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeFloat(this.angle);
        buffer.writeFloat(this.cx);
        buffer.writeFloat(this.cy);
        buffer.writeFloat(this.cz);
    }

    @Override
    public String writeToString() {
        return String.format(
                Locale.ROOT, "%s %.2f %.2f %.2f %.2f", BuiltInRegistries.PARTICLE_TYPE.getKey(this.getType()), this.angle, this.cx, this.cy, this.cz);
    }
}
