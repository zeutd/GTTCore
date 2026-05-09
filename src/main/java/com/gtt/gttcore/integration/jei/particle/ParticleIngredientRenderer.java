package com.gtt.gttcore.integration.jei.particle;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gtt.gttcore.api.particle.ParticleStack;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@SuppressWarnings("removal")
@ParametersAreNonnullByDefault
public class ParticleIngredientRenderer implements IIngredientRenderer<ParticleStack> {

    public static final ResourceLocation TEXTURE_LOCATION = GTCEu.id("block/fluids/fluid.steam");

    @Override
    public void render(GuiGraphics guiGraphics, ParticleStack ingredient) {
        int colorARGB = GTUtil.convertRGBtoARGB(ingredient.getType().color);
        guiGraphics.blit(0, 0, 0, getWidth(), getHeight(), Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(TEXTURE_LOCATION), FastColor.ARGB32.red(colorARGB), FastColor.ARGB32.green(colorARGB), FastColor.ARGB32.blue(colorARGB), 1);
    }

    @Override
    public @NotNull List<Component> getTooltip(ParticleStack ingredient, TooltipFlag tooltipFlag) {
        return List.of(ingredient.getType().getDisplayName());
    }
}
