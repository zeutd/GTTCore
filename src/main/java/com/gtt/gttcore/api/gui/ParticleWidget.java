package com.gtt.gttcore.api.gui;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gtt.gttcore.api.capability.IParticleContainer;
import com.gtt.gttcore.integration.emi.ParticleEmiStack;
import com.lowdragmc.lowdraglib.gui.editor.annotation.LDLRegister;
import com.lowdragmc.lowdraglib.gui.editor.configurator.IConfigurableWidget;
import com.lowdragmc.lowdraglib.gui.ingredient.IRecipeIngredientSlot;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;
import com.lowdragmc.lowdraglib.gui.util.DrawerHelper;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.mojang.blaze3d.systems.RenderSystem;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@LDLRegister(name = "gtt_particle_widget", group = "widget.gtt_container", priority = 50)
@Getter
public class ParticleWidget extends Widget implements IRecipeIngredientSlot, IConfigurableWidget {

    protected IParticleContainer container;

    public final static ResourceBorderTexture FLUID_SLOT_TEXTURE = new ResourceBorderTexture(
            "ldlib:textures/gui/fluid_slot.png", 18, 18, 1, 1);
    public static final ResourceLocation TEXTURE_LOCATION = GTCEu.id("block/fluids/fluid.steam");


    @Override
    public void initTemplate() {
        setBackground(FLUID_SLOT_TEXTURE);
    }

    public ParticleWidget(IParticleContainer container, int x, int y) {
        super(x, y, 18, 18);
        setParticleContainer(container);
    }

    public void setParticleContainer(IParticleContainer container) {
        this.container = container;
        if (isClientSideWidget) {
            setClientSideWidget();
        }
    }

    @Override
    public List<Component> getFullTooltipTexts() {
        List<Component> tooltips = new ArrayList<>();
        var particleStack = this.container.getStored();
        if (particleStack != null && !particleStack.isEmpty()) {
            tooltips.add(Component.translatable(particleStack.getType().getId().toLanguageKey("particle_type")));
        } else {
            tooltips.add(Component.translatable("gtceu.fluid.empty"));
        }
        tooltips.addAll(getTooltipTexts());
        return tooltips;
    }


    @Override
    public List<Object> getXEIIngredients() {
        if (container.getStored() == null || container.getStored().isEmpty()) return Collections.emptyList();
        return List.of(ParticleEmiStack.of(container.getStored()));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void drawInBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.drawInBackground(graphics, mouseX, mouseY, partialTicks);
        if (!container.getStored().isEmpty()){
            int colorARGB = GTUtil.convertRGBtoARGB(container.getStored().getType().color);
            graphics.blit(getPositionX() + 1, getPositionY() + 1, 0, 16, 16, Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(TEXTURE_LOCATION), FastColor.ARGB32.red(colorARGB), FastColor.ARGB32.green(colorARGB), FastColor.ARGB32.blue(colorARGB), 1);
        }
        if (isMouseOverElement(mouseX, mouseY) && getHoverElement(mouseX, mouseY) == this) {
            RenderSystem.colorMask(true, true, true, false);
            DrawerHelper.drawSolidRect(graphics, getPosition().x + 1, getPosition().y + 1, 16, 16, 0x80FFFFFF);
            RenderSystem.colorMask(true, true, true, true);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void drawInForeground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        if (isMouseOverElement(mouseX, mouseY) && getHoverElement(mouseX, mouseY) == this) {
            if (gui != null) {
                gui.getModularUIGui().setHoverTooltip(getFullTooltipTexts(), ItemStack.EMPTY, null, null);
            }
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1f);
        } else {
            super.drawInForeground(graphics, mouseX, mouseY, partialTicks);
        }
    }
}
