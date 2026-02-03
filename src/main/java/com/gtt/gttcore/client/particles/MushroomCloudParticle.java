package com.gtt.gttcore.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MushroomCloudParticle extends TextureSheetParticle {
    private final SpriteSet sprites;
    public final float cx;
    public final float cy;
    public final float cz;
    public final float angle;

    protected MushroomCloudParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed,
                                    double zSpeed, MushroomCloudParticleOptions options, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = sprites;
        this.cx = options.cx();
        this.cy = options.cy();
        this.cz = options.cz();
        this.angle = options.angle();
        this.quadSize *= 12F;
        this.lifetime = 100;
        this.setSpriteFromAge(sprites);
        this.hasPhysics = false;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return this.quadSize * Mth.clamp(((float) this.age + scaleFactor) / (float) this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
        double ageFraction = (double) age / lifetime;
        this.setSpriteFromAge(sprites);
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        double x = Math.sin(2 * ageFraction * Math.PI) * 1 / (10 * ageFraction + 1) + ageFraction * 0.35 - 0.5;
        double y = -Math.cos(2 * ageFraction * Math.PI) * 1 / (10 * ageFraction + 1) + 1;
        this.setPos(
                15 * Math.cos(angle) * x + cx,
                15 * y + cy,
                15 * Math.sin(angle) * x + cz
        );
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<MushroomCloudParticleOptions> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(MushroomCloudParticleOptions options, ClientLevel level, double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new MushroomCloudParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, options, this.sprites);
        }
    }
}
