package com.gtt.gttcore.integration.emi;

import com.google.common.collect.Lists;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gtt.gttcore.api.gui.ParticleWidget;
import com.gtt.gttcore.api.particle.ParticleStack;
import com.gtt.gttcore.api.particle.ParticleType;
import dev.emi.emi.EmiPort;
import dev.emi.emi.api.render.EmiTooltipComponents;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.screen.tooltip.EmiTextTooltipWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.inventory.InventoryMenu;

import java.util.ArrayList;
import java.util.List;

public class ParticleEmiStack extends EmiStack {

    private final ParticleType particleType;

    public ParticleEmiStack(ParticleStack stack){
        this(stack.getType(), stack.getAmount());
    }

    public ParticleEmiStack(ParticleType particleType, long amount){
        this.particleType = particleType;
        this.amount = amount;
    }

    @Override
    public EmiStack copy() {
        var e = new ParticleEmiStack(particleType, amount);
        e.setChance(chance);
        e.setRemainder(getRemainder().copy());
        e.comparison = comparison;
        return e;
    }

    @Override
    public void render(GuiGraphics draw, int x, int y, float delta, int flags) {
        int colorARGB = GTUtil.convertRGBtoARGB(particleType.color);
        draw.blit(x, y, 0, 16, 16, Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(ParticleWidget.TEXTURE_LOCATION), FastColor.ARGB32.red(colorARGB), FastColor.ARGB32.green(colorARGB), FastColor.ARGB32.blue(colorARGB), 1);}

    @Override
    public boolean isEmpty() {
        return amount == 0 || particleType == null;
    }

    @Override
    public CompoundTag getNbt() {
        return null;
    }

    @Override
    public Object getKey() {
        return particleType;
    }

    @Override
    public ResourceLocation getId() {
        return particleType.getId();
    }

    @Override
    public List<Component> getTooltipText() {
        var list = new ArrayList<Component>();
        list.add(particleType.getDisplayName());
        return list;
    }

    @Override
    public List<ClientTooltipComponent> getTooltip() {
        List<ClientTooltipComponent> list = Lists.newArrayList();
        List<Component> text = getTooltipText();
        if (!text.isEmpty()) {
            list.add(new EmiTextTooltipWrapper(this, EmiPort.ordered(text.get(0))));
        }
        list.addAll(text.stream().skip(1).map(EmiTooltipComponents::of).toList());
        if (amount > 1) {
            list.add(EmiTooltipComponents.getAmount(this));
        }
        String namespace = particleType.getId().getNamespace();
        EmiTooltipComponents.appendModName(list, namespace);
        list.addAll(super.getTooltip());
        return list;
    }

    @Override
    public Component getName() {
        return particleType.getDisplayName();
    }

    public static EmiStack of(ParticleStack stack) {
        return stack.isEmpty() ? EMPTY : new ParticleEmiStack(stack.getType(), stack.getAmount());
    }

    public static EmiStack of(ParticleType type) {
        return new ParticleEmiStack(type, 0);
    }
}
