package com.gtt.gttcore.client.renderers;

import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.client.renderer.block.FluidBlockRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.client.util.RenderUtil;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gtt.gttcore.api.machine.trait.multiblock.MultiblockFluidBoxRendererTrait;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.Getter;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.RenderTypeHelper;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.gregtechceu.gtceu.client.util.RenderUtil.getVertices;
import static net.minecraft.util.FastColor.ARGB32.*;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FluidBoxRender extends DynamicRender<WorkableElectricMultiblockMachine, FluidBoxRender> {

    public static final List<RelativeDirection> DEFAULT_FACES = Collections.singletonList(RelativeDirection.UP);

    // spotless:off
    @SuppressWarnings("deprecation")
    public static final Codec<FluidBoxRender> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            FluidBlockRenderer.CODEC.forGetter(FluidBoxRender::getFluidBlockRenderer),
            BuiltInRegistries.FLUID.byNameCodec().optionalFieldOf("fixed_fluid").forGetter(FluidBoxRender::getFixedFluid),
            RelativeDirection.CODEC.listOf().optionalFieldOf("drawn_faces", DEFAULT_FACES).forGetter(FluidBoxRender::getDrawFaces)
    ).apply(instance, FluidBoxRender::new));
    public static final DynamicRenderType<WorkableElectricMultiblockMachine, FluidBoxRender> TYPE = new DynamicRenderType<>(FluidBoxRender.CODEC);
    // spotless:on

    @Getter
    private final FluidBlockRenderer fluidBlockRenderer;
    private final boolean fixedFluid;
    @Getter
    private final List<RelativeDirection> drawFaces;

    private @Nullable Fluid cachedFluid;
    private @Nullable ResourceLocation cachedRecipe;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public FluidBoxRender(FluidBlockRenderer fluidBlockRenderer,
                           Optional<Fluid> fixedFluid, List<RelativeDirection> drawFaces) {
        this.fluidBlockRenderer = fluidBlockRenderer;
        if (fixedFluid.isPresent()) {
            this.fixedFluid = true;
            this.cachedFluid = fixedFluid.get();
        } else {
            this.fixedFluid = false;
        }
        this.drawFaces = drawFaces.isEmpty() ? DEFAULT_FACES : drawFaces;
    }

    @Override
    public DynamicRenderType<WorkableElectricMultiblockMachine, FluidBoxRender> getType() {
        return TYPE;
    }

    @Override
    public int getViewDistance() {
        return 32;
    }

    @Override
    public void render(WorkableElectricMultiblockMachine machine, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight, int packedOverlay) {
        if (!ConfigHolder.INSTANCE.client.renderer.renderFluids) return;
        var trait = machine.getTrait(MultiblockFluidBoxRendererTrait.TYPE);
        if (!machine.self().isFormed() || trait.getFluidBox() == null) {
            return;
        }
        if (!fixedFluid) {
            var lastRecipe = machine.getRecipeLogic().getLastRecipe();
            if (lastRecipe == null) {
                cachedRecipe = null;
                cachedFluid = null;
            } else if (machine.self().getOffsetTimer() % 20 == 0 || lastRecipe.id != cachedRecipe) {
                cachedRecipe = lastRecipe.id;
                if (machine.isActive()) {
                    cachedFluid = RenderUtil.getRecipeFluidToRender(lastRecipe);
                } else {
                    cachedFluid = null;
                }
            }
        }
        if (cachedFluid == null) {
            return;
        }

        var fluidRenderType = ItemBlockRenderTypes.getRenderLayer(cachedFluid.defaultFluidState());
        var consumer = buffer.getBuffer(RenderTypeHelper.getEntityRenderType(fluidRenderType, false));

        var fluidClientInfo = IClientFluidTypeExtensions.of(cachedFluid);
        var sprite = RenderUtil.FluidTextureType.STILL.map(fluidClientInfo);
        float u0 = sprite.getU0(), v0 = sprite.getV0(), u1 = sprite.getU1(), v1 = sprite.getV1();
        int color = fluidClientInfo.getTintColor();
        int r = red(color), g = green(color), b = blue(color), a = alpha(color);

        for (RelativeDirection face : this.drawFaces) {


            var dir = face.getRelative(machine.self().getFrontFacing(), machine.self().getUpwardsFacing(),
                    machine.self().isFlipped());
            if (dir.getAxis() != Direction.Axis.Y) dir = dir.getOpposite();
            var normal = dir.step();
            var vertices = fluidBlockRenderer.transformVertices(getVertices(dir), dir);

            switch (dir){
                case UP -> {
                    for (int i = trait.getFluidBox().minX(); i <= trait.getFluidBox().maxX(); i++) {
                        for (int j = trait.getFluidBox().minZ(); j <= trait.getFluidBox().maxZ(); j++) {
                            poseStack.pushPose();
                            poseStack.translate(i, trait.getFluidBox().maxY(), j);
                            var pose = poseStack.last().pose();
                            fluidBlockRenderer.drawFace(pose, consumer, vertices, normal, u0, u1, v0, v1, r, g, b, a, packedOverlay, packedLight);
                            poseStack.popPose();
                        }
                    }
                }
                case DOWN -> {
                    for (int i = trait.getFluidBox().minX(); i <= trait.getFluidBox().maxX(); i++) {
                        for (int j = trait.getFluidBox().minZ(); j <= trait.getFluidBox().maxZ(); j++) {
                            poseStack.pushPose();
                            poseStack.translate(i, trait.getFluidBox().minY(), j);
                            var pose = poseStack.last().pose();
                            fluidBlockRenderer.drawFace(pose, consumer, vertices, normal, u0, u1, v0, v1, r, g, b, a, packedOverlay, packedLight);
                            poseStack.popPose();
                        }
                    }
                }
                case NORTH -> {
                    for (int i = trait.getFluidBox().minX(); i <= trait.getFluidBox().maxX(); i++) {
                        for (int j = trait.getFluidBox().minY(); j <= trait.getFluidBox().maxY(); j++) {
                            poseStack.pushPose();
                            poseStack.translate(i, j, trait.getFluidBox().maxZ());
                            var pose = poseStack.last().pose();
                            fluidBlockRenderer.drawFace(pose, consumer, vertices, normal, u0, u1, v0, v1, r, g, b, a, packedOverlay, packedLight);
                            poseStack.popPose();
                        }
                    }
                }
                case SOUTH -> {
                    for (int i = trait.getFluidBox().minX(); i <= trait.getFluidBox().maxX(); i++) {
                        for (int j = trait.getFluidBox().minY(); j <= trait.getFluidBox().maxY(); j++) {
                            poseStack.pushPose();
                            poseStack.translate(i, j, trait.getFluidBox().minZ());
                            var pose = poseStack.last().pose();
                            fluidBlockRenderer.drawFace(pose, consumer, vertices, normal, u0, u1, v0, v1, r, g, b, a, packedOverlay, packedLight);
                            poseStack.popPose();
                        }
                    }
                }
                case EAST -> {
                    for (int i = trait.getFluidBox().minY(); i <= trait.getFluidBox().maxY(); i++) {
                        for (int j = trait.getFluidBox().minZ(); j <= trait.getFluidBox().maxZ(); j++) {
                            poseStack.pushPose();
                            poseStack.translate(trait.getFluidBox().minX(), i, j);
                            var pose = poseStack.last().pose();
                            fluidBlockRenderer.drawFace(pose, consumer, vertices, normal, u0, u1, v0, v1, r, g, b, a, packedOverlay, packedLight);
                            poseStack.popPose();
                        }
                    }
                }
                case WEST -> {
                    for (int i = trait.getFluidBox().minY(); i <= trait.getFluidBox().maxY(); i++) {
                        for (int j = trait.getFluidBox().minZ(); j <= trait.getFluidBox().maxZ(); j++) {
                            poseStack.pushPose();
                            poseStack.translate(trait.getFluidBox().maxX(), i, j);
                            var pose = poseStack.last().pose();
                            fluidBlockRenderer.drawFace(pose, consumer, vertices, normal, u0, u1, v0, v1, r, g, b, a, packedOverlay, packedLight);
                            poseStack.popPose();
                        }
                    }
                }
            }
        }
    }

    private Optional<Fluid> getFixedFluid() {
        if (fixedFluid) return Optional.ofNullable(cachedFluid);
        else return Optional.empty();
    }

    @Override
    public boolean shouldRenderOffScreen(WorkableElectricMultiblockMachine machine) {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(WorkableElectricMultiblockMachine machine) {
        var box = super.getRenderBoundingBox(machine);
        var trait = machine.getTrait(MultiblockFluidBoxRendererTrait.TYPE);
        if (trait == null) return box;
        return box.minmax(AABB.of(trait.getFluidBox())).inflate(getViewDistance());
    }
}
