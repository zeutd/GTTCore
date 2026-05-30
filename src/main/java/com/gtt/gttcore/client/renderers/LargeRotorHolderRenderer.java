package com.gtt.gttcore.client.renderers;

import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.client.model.machine.IMachineRendererModel;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.common.machine.multiblock.part.RotorHolderPartMachine;
import com.gtt.gttcore.GTTCore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.HAS_ROTOR;
import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.IS_ROTOR_SPINNING;

@OnlyIn(Dist.CLIENT)
public class LargeRotorHolderRenderer extends DynamicRender<RotorHolderPartMachine, LargeRotorHolderRenderer> implements IMachineRendererModel<RotorHolderPartMachine> {
    public static final Codec<LargeRotorHolderRenderer> CODEC = Codec.unit(LargeRotorHolderRenderer::new);
    public static final DynamicRenderType<RotorHolderPartMachine, LargeRotorHolderRenderer> TYPE = new DynamicRenderType<>(CODEC);
    public static final ResourceLocation BASE_BG_LOCATION = GTTCore.id("block/large_rotor_holder/base_bg");
    public static final ResourceLocation BASE_RING_LOCATION = GTTCore.id("block/large_rotor_holder/base_ring");
    public static final ResourceLocation ROTOR_IDLE_LOCATION = GTTCore.id("block/large_rotor_holder/rotor_idle");
    public static final ResourceLocation ROTOR_SPINNING_LOCATION = GTTCore.id("block/large_rotor_holder/rotor_spinning");

    public LargeRotorHolderRenderer(){}
    @Override
    public DynamicRenderType<RotorHolderPartMachine, LargeRotorHolderRenderer> getType() {
        return TYPE;
    }

    @Override
    public void render(RotorHolderPartMachine machine, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if(!machine.isFormed() || !(machine.getControllers().last() instanceof WorkableElectricMultiblockMachine)) return;
        Minecraft instance = Minecraft.getInstance();
        TextureAtlasSprite backgroundSprite = instance.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(BASE_BG_LOCATION);
        TextureAtlasSprite ringSprite = instance.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(BASE_RING_LOCATION);
        TextureAtlasSprite rotorIdleSprite = instance.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(ROTOR_IDLE_LOCATION);
        TextureAtlasSprite rotorSpinningSprite = instance.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(ROTOR_SPINNING_LOCATION);

        Vec3i dir = machine.getFrontFacing().getNormal();
        Vec3i up = (machine.getFrontFacing() != Direction.UP && machine.getFrontFacing() != Direction.DOWN) ? Direction.UP.getNormal() : Direction.NORTH.getNormal();
        VertexConsumer vc = buffer.getBuffer(RenderType.cutoutMipped());

        renderSprite(vc, poseStack, dir, up, 0.012f, backgroundSprite, 0xFFFFFFFF, packedLight, packedOverlay);
        if (machine.getBlockState().getValue(HAS_ROTOR)) {
            if (machine.getBlockState().getValue(IS_ROTOR_SPINNING))
                renderSprite(vc, poseStack, dir, up, 0.008f, rotorSpinningSprite, machine.getRotorMaterial().getMaterialARGB(), packedLight, packedOverlay);
            else
                renderSprite(vc, poseStack, dir, up, 0.008f, rotorIdleSprite, machine.getRotorMaterial().getMaterialARGB(), packedLight, packedOverlay);
        }
        renderSprite(vc, poseStack, dir, up, 0.004f, ringSprite, 0xFFFFFFFF, packedLight, packedOverlay);
    }

    @OnlyIn(Dist.CLIENT)
    public void renderSprite(VertexConsumer vc,
                             PoseStack poseStack,
                             Vec3i dir, Vec3i up,
                             float z, TextureAtlasSprite sprite,
                             int color, int packedLight, int packedOverlay){
        float u0 = sprite.getU0(), u1 = sprite.getU1(), v0 = sprite.getV0(), v1 = sprite.getV1();
        Vec3i left = up.cross(dir);
        Matrix4f mat = poseStack.last().pose();
        vc
                .vertex(mat,
                        2.5f * (left.getX() - up.getX()) + dir.getX() * (z + 0.5f) + 0.5f,
                        2.5f * (left.getY() - up.getY()) + dir.getY() * (z + 0.5f) + 0.5f,
                        2.5f * (left.getZ() - up.getZ()) + dir.getZ() * (z + 0.5f) + 0.5f
                )
                .color(color)
                .uv(u1, v1)
                .overlayCoords(packedOverlay)
                .uv2(packedLight)
                .normal(dir.getX(), dir.getY(), dir.getZ())
                .endVertex();
        vc
                .vertex(mat,
                        2.5f * (left.getX() + up.getX()) + dir.getX() * (z + 0.5f) + 0.5f,
                        2.5f * (left.getY() + up.getY()) + dir.getY() * (z + 0.5f) + 0.5f,
                        2.5f * (left.getZ() + up.getZ()) + dir.getZ() * (z + 0.5f) + 0.5f
                )
                .color(color)
                .uv(u1, v0)
                .overlayCoords(packedOverlay)
                .uv2(packedLight)
                .normal(dir.getX(), dir.getY(), dir.getZ())
                .endVertex();
        vc
                .vertex(mat,
                        -2.5f * (left.getX() - up.getX()) + dir.getX() * (z + 0.5f) + 0.5f,
                        -2.5f * (left.getY() - up.getY()) + dir.getY() * (z + 0.5f) + 0.5f,
                        -2.5f * (left.getZ() - up.getZ()) + dir.getZ() * (z + 0.5f) + 0.5f
                )
                .color(color)
                .uv(u0, v0)
                .overlayCoords(packedOverlay)
                .uv2(packedLight)
                .normal(dir.getX(), dir.getY(), dir.getZ())
                .endVertex();
        vc
                .vertex(mat,
                        -2.5f * (left.getX() + up.getX()) + dir.getX() * (z + 0.5f) + 0.5f,
                        -2.5f * (left.getY() + up.getY()) + dir.getY() * (z + 0.5f) + 0.5f,
                        -2.5f * (left.getZ() + up.getZ()) + dir.getZ() * (z + 0.5f) + 0.5f
                )
                .color(color)
                .uv(u0, v1)
                .overlayCoords(packedOverlay)
                .uv2(packedLight)
                .normal(dir.getX(), dir.getY(), dir.getZ())
                .endVertex();
    }

    @Override
    public boolean shouldRender(RotorHolderPartMachine machine, Vec3 cameraPos) {
        return super.shouldRender(machine, cameraPos) && machine.isFormed();
    }
}
