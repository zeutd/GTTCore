package com.gtt.gttcore.mixin;

import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ExistingFileHelper.class, remap = false)
public interface ExistingFileHelperAccessor {
    @Invoker
    ResourceManager callGetManager(PackType packType);
}
