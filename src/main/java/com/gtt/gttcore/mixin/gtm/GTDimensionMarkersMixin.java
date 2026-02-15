package com.gtt.gttcore.mixin.gtm;

import com.gregtechceu.gtceu.api.data.DimensionMarker;
import com.gregtechceu.gtceu.common.data.GTDimensionMarkers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

import static com.gregtechceu.gtceu.common.data.GTDimensionMarkers.END_MARKER;
import static com.gregtechceu.gtceu.common.data.GTDimensionMarkers.NETHER_MARKER;

@Mixin(GTDimensionMarkers.class)
public class GTDimensionMarkersMixin {

    @Unique
    private static DimensionMarker gTTCore$createAndRegister(ResourceLocation dim, int tier, Supplier<ItemLike> supplier,
                                                             @Nullable String overrideName) {
        DimensionMarker marker = new DimensionMarker(tier, supplier, overrideName);
        marker.register(dim);
        return marker;
    }

    @Inject(method = "createAndRegister(Lnet/minecraft/resources/ResourceLocation;ILjava/util/function/Supplier;Ljava/lang/String;)Lcom/gregtechceu/gtceu/api/data/DimensionMarker;", at = @At("HEAD"), remap = false, cancellable = true)
    private static void createAndRegisterMixin(ResourceLocation dim, int tier, Supplier<ItemLike> supplier, @Nullable String overrideName, CallbackInfoReturnable<DimensionMarker> cir) {
        if (dim == Level.NETHER.location()) {
            cir.setReturnValue(gTTCore$createAndRegister(Level.NETHER.location(), 2,
                    () -> NETHER_MARKER, null));
        }
        if (dim == Level.END.location()) {
            cir.setReturnValue(gTTCore$createAndRegister(Level.END.location(), 4,
                    () -> END_MARKER, null));
        }
    }
}
