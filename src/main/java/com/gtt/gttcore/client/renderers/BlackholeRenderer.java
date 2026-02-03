package com.gtt.gttcore.client.renderers;

import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.entity.BlackholeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.renderable.BakedModelRenderable;
import net.minecraftforge.client.model.renderable.IRenderable;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector4f;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class BlackholeRenderer extends EntityRenderer<BlackholeEntity> {
    private static final RandomSource random = RandomSource.create();
    private static final Direction[] ALL_FACES_AND_NULL = Arrays.copyOf(Direction.values(), Direction.values().length + 1);
    public static final ResourceLocation SPHERE_MODEL_LOCATION = GTTCore.id("sphere/sphere");
    private static BakedModelRenderable SPHERE_MODEL;
    private static IRenderable<Unit> SPHERE_BLACK;

    public BlackholeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        if (SPHERE_MODEL == null) {
            SPHERE_MODEL = BakedModelRenderable.of(SPHERE_MODEL_LOCATION);
        }
        if (SPHERE_BLACK == null) {
            SPHERE_BLACK = SPHERE_MODEL.withContext(new BakedModelRenderable.Context(null, ALL_FACES_AND_NULL, RandomSource.create(), 42, ModelData.EMPTY, new Vector4f(0,0,0,1)));
        }
    }

    @Override
    public void render(@NotNull BlackholeEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        float lifeFraction = (entity.getLife() + partialTick) / 160;
        poseStack.pushPose();
        float scalar = (float) (1 - Math.pow(1 - Math.min(lifeFraction * 2, 1), 4) - Math.pow(Math.max(0, 8 * lifeFraction - 7), 4)) * 10;
        poseStack.scale(scalar, scalar, scalar);
        SPHERE_BLACK.render(poseStack, bufferSource, r -> RenderType.debugQuads(), packedLight, 0, partialTick, Unit.INSTANCE);
        poseStack.popPose();
        poseStack.pushPose();
        scalar = (float) (1 - Math.pow(Math.max(lifeFraction * 3 - 2, 0), 8)) * 80 + 10;
        poseStack.scale(scalar, scalar, scalar);
        SPHERE_MODEL.render(poseStack, bufferSource, r -> RenderType.debugQuads(), packedLight, 0, partialTick, new BakedModelRenderable.Context(null, ALL_FACES_AND_NULL, random, 42, ModelData.EMPTY, new Vector4f(1, 0, 1, Math.max(2 * lifeFraction - 1, 0) / 2)));
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(BlackholeEntity pEntity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
