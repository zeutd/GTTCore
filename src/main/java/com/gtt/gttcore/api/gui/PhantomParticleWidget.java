package com.gtt.gttcore.api.gui;

import com.gregtechceu.gtceu.GTCEu;
import com.gtt.gttcore.api.capability.IParticleContainer;
import com.gtt.gttcore.api.particle.ParticleStack;
import com.gtt.gttcore.api.particle.ParticleType;
import com.gtt.gttcore.api.registry.GTTRegistries;
import com.lowdragmc.lowdraglib.gui.editor.annotation.LDLRegister;
import com.lowdragmc.lowdraglib.gui.ingredient.IGhostIngredientTarget;
import com.lowdragmc.lowdraglib.gui.ingredient.Target;
import dev.emi.emi.api.stack.EmiStack;
import lombok.Getter;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("removal")
@LDLRegister(name = "gtt_phantom_particle_widget", group = "widget.gtt_container", priority = 50)
public class PhantomParticleWidget extends ParticleWidget implements IGhostIngredientTarget {

    private final Supplier<ParticleStack> phantomParticleGetter;
    private final Consumer<ParticleStack> phantomParticleSetter;

    @Getter
    private ParticleStack lastPhantomStack;

    public PhantomParticleWidget(IParticleContainer container, int x, int y, Supplier<ParticleStack> phantomParticleGetter, Consumer<ParticleStack> phantomParticleSetter) {
        super(container, x, y);
        this.phantomParticleGetter = phantomParticleGetter;
        this.phantomParticleSetter = phantomParticleSetter;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public List<Target> getPhantomTargets(Object ingredient) {
        if (GTCEu.Mods.isEMILoaded() && ingredient instanceof EmiStack emiStack){
            //GTTCore.LOGGER.info(emiStack.getKey());
            if (emiStack.getKey() instanceof ParticleStack stack){
                ingredient = stack.withAmount(stack.getAmount() == 0 ? 1000 : stack.getAmount());
            }
            if (emiStack.getKey() instanceof ParticleType type){
                ingredient = new ParticleStack(type, 1000);
            }
            if (emiStack.getKey() instanceof String name){
                ingredient = new ParticleStack(GTTRegistries.PARTICLE_TYPES.get(new ResourceLocation(name)), 1000);
            }
            if (emiStack.getKey() instanceof ResourceLocation location){
                ingredient = new ParticleStack(GTTRegistries.PARTICLE_TYPES.get(location), 1000);
            }
        }

        if (!(ingredient instanceof ParticleStack)) return Collections.emptyList();

        var rectangle = toRectangleBox();
        return List.of(new Target() {
            @NotNull
            @Override
            public Rect2i getArea() {
                return rectangle;
            }

            @Override
            public void accept(Object ingredient) {
                if (GTCEu.Mods.isEMILoaded() && ingredient instanceof EmiStack emiStack){
                    if (emiStack.getKey() instanceof ParticleStack stack){
                        ingredient = stack.withAmount(stack.getAmount() == 0 ? 1000 : stack.getAmount());
                    }
                    if (emiStack.getKey() instanceof ParticleType type){
                        ingredient = new ParticleStack(type, 1000);
                    }
                    if (emiStack.getKey() instanceof String name){
                        ingredient = new ParticleStack(GTTRegistries.PARTICLE_TYPES.get(new ResourceLocation(name)), 1000);
                    }
                    if (emiStack.getKey() instanceof ResourceLocation location){
                        ingredient = new ParticleStack(GTTRegistries.PARTICLE_TYPES.get(location), 1000);
                    }
                }
                if (!(ingredient instanceof ParticleStack stack)) return;

                if (!stack.isEmpty()) {
                    writeClientAction(2, stack::toNetwork);
                }

                if (isClientSideWidget) {
                    if (phantomParticleSetter != null) {
                        phantomParticleSetter.accept(stack);
                    }
                }
            }
        });
    }

    @Override
    public void handleClientAction(int id, FriendlyByteBuf buffer) {
        if (id == 1) {
            handlePhantomClick();
        } else if (id == 2) {
            if (phantomParticleSetter != null) {
                phantomParticleSetter.accept(ParticleStack.fromNetwork(buffer));
            }
        } else if (id == 4) {
            phantomParticleSetter.accept(ParticleStack.EMPTY);
        } else if (id == 5) {
            phantomParticleSetter.accept(ParticleStack.fromNetwork(buffer));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        ParticleStack stack = phantomParticleGetter.get();
        if (stack == null || stack.isEmpty()) {
            if (lastPhantomStack != null) {
                setLastPhantomStack(null);
                writeUpdateInfo(4, buf -> {});
            }
        } else if (lastPhantomStack == null || !stack.is(lastPhantomStack.getType())) {
            setLastPhantomStack(stack);
            writeUpdateInfo(5, stack::toNetwork);
        }
    }

    protected void setLastPhantomStack(ParticleStack stack) {
        if (stack != null) {
            this.lastPhantomStack = stack.copy();
            this.lastPhantomStack.setAmount(1);
        } else {
            this.lastPhantomStack = null;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOverElement(mouseX, mouseY)) {
            if (isClientSideWidget) {
                handlePhantomClick();
            } else {
                writeClientAction(1, buffer -> {});
            }
            return true;
        }
        return false;
    }

    private void handlePhantomClick() {
        if (phantomParticleSetter != null) phantomParticleSetter.accept(ParticleStack.EMPTY);
    }
}
