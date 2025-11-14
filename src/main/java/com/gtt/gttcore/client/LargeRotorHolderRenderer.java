package com.gtt.gttcore.client;

import com.gregtechceu.gtceu.client.model.machine.IMachineRendererModel;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.common.machine.multiblock.part.RotorHolderPartMachine;
import com.gtt.gttcore.GTTCore;
import com.gtt.gttcore.common.machine.multiblock.HugeTurbineMachine;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Vec3i;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static com.gtt.gttcore.GTTCore.LOGGER;

public class LargeRotorHolderRenderer extends DynamicRender<RotorHolderPartMachine, LargeRotorHolderRenderer> implements IMachineRendererModel<RotorHolderPartMachine> {
    public static final Codec<LargeRotorHolderRenderer> CODEC = Codec.unit(LargeRotorHolderRenderer::new);
    public static final DynamicRenderType<RotorHolderPartMachine, LargeRotorHolderRenderer> TYPE = new DynamicRenderType<>(CODEC);

    public LargeRotorHolderRenderer(){}
    @Override
    public DynamicRenderType<RotorHolderPartMachine, LargeRotorHolderRenderer> getType() {
        return TYPE;
    }

    @Override
    public void render(RotorHolderPartMachine machine, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if(!machine.isFormed() || !(machine.getControllers().last() instanceof HugeTurbineMachine htm)) return;
        Minecraft instance = Minecraft.getInstance();
        TextureAtlasSprite backgroundSprite = instance.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(GTTCore.id("block/large_rotor_holder/base_bg"));
        TextureAtlasSprite ringSprite = instance.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(GTTCore.id("block/large_rotor_holder/base_ring"));
        TextureAtlasSprite rotorIdleSprite = instance.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(GTTCore.id("block/large_rotor_holder/rotor_idle"));
        TextureAtlasSprite rotorSpinningSprite = instance.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(GTTCore.id("block/large_rotor_holder/rotor_spinning"));

        //Vector3f pos = machine.getPos().getCenter().toVector3f();
        Vec3i dir = machine.getFrontFacing().getNormal();
        Vec3i up = machine.getUpwardsFacing().getNormal();
        VertexConsumer vc = buffer.getBuffer(RenderType.translucent());
        renderSprite(vc, poseStack, dir, up, 0.03f, backgroundSprite);
        if (htm.isWorkingEnabled())renderSprite(vc, poseStack, dir, up, 0.02f, rotorSpinningSprite);
        else renderSprite(vc, poseStack, dir, up, 0.02f, rotorIdleSprite);
        renderSprite(vc, poseStack, dir, up, 0.01f, ringSprite);
    }

    @OnlyIn(Dist.CLIENT)
    public void renderSprite(VertexConsumer vc,
                             PoseStack poseStack,
                             Vec3i dir, Vec3i up,
                             float z, TextureAtlasSprite sprite){
        float u0 = sprite.getU0(), u1 = sprite.getU1(), v0 = sprite.getV0(), v1 = sprite.getV1();
        Vec3i right = dir.cross(up);
        Matrix4f mat = poseStack.last().pose();
        vc
                .vertex(mat,
                        2.5f * (right.getX() + up.getX()) + dir.getX() * (z + 0.5f) + 0.5f,
                        2.5f * (right.getY() + up.getY()) + dir.getY() * (z + 0.5f) + 0.5f,
                        2.5f * (right.getZ() + up.getZ()) + dir.getZ() * (z + 0.5f) + 0.5f
                )
                .color(0xFFFFFFFF)
                .uv(u0, v0)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(LightTexture.FULL_BRIGHT)
                .normal(dir.getX(), dir.getY(), dir.getZ())
                .endVertex();
        vc
                .vertex(mat,
                        2.5f * (right.getX() - up.getX()) + dir.getX() * (z + 0.5f) + 0.5f,
                        2.5f * (right.getY() - up.getY()) + dir.getY() * (z + 0.5f) + 0.5f,
                        2.5f * (right.getZ() - up.getZ()) + dir.getZ() * (z + 0.5f) + 0.5f
                )
                .color(0xFFFFFFFF)
                .uv(u1, v0)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(LightTexture.FULL_BRIGHT)
                .normal(dir.getX(), dir.getY(), dir.getZ())
                .endVertex();
        vc
                .vertex(mat,
                        -2.5f * (right.getX() + up.getX()) + dir.getX() * (z + 0.5f) + 0.5f,
                        -2.5f * (right.getY() + up.getY()) + dir.getY() * (z + 0.5f) + 0.5f,
                        -2.5f * (right.getZ() + up.getZ()) + dir.getZ() * (z + 0.5f) + 0.5f
                )
                .color(0xFFFFFFFF)
                .uv(u1, v1)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(LightTexture.FULL_BRIGHT)
                .normal(dir.getX(), dir.getY(), dir.getZ())
                .endVertex();
        vc
                .vertex(mat,
                        -2.5f * (right.getX() - up.getX()) + dir.getX() * (z + 0.5f) + 0.5f,
                        -2.5f * (right.getY() - up.getY()) + dir.getY() * (z + 0.5f) + 0.5f,
                        -2.5f * (right.getZ() - up.getZ()) + dir.getZ() * (z + 0.5f) + 0.5f



                )
                .color(0xFFFFFFFF)
                .uv(u0, v1)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(LightTexture.FULL_BRIGHT)
                .normal(dir.getX(), dir.getY(), dir.getZ())
                .endVertex();
    }

    @Override
    public boolean shouldRender(RotorHolderPartMachine machine, Vec3 cameraPos) {
        return true;
    }
}
