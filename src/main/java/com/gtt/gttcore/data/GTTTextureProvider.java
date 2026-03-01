package com.gtt.gttcore.data;

import com.google.common.hash.HashCode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gtt.gttcore.GTTCore;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("removal")
public class GTTTextureProvider implements DataProvider{
    private final PackOutput packOutput;
    private final ExistingFileHelper existingFileHelper;
    public GTTTextureProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper){
        //pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures");
        this.packOutput = packOutput;
        this.existingFileHelper = existingFileHelper;
    }
    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        existingFileHelper.getManager(PackType.CLIENT_RESOURCES).listResources("textures", r -> true)
                .forEach(((rl, resource) -> {
                    try {
                        var pair = loadTexture(rl);
                        var image = pair.getFirst();
                        var metadata = pair.getSecond();
                        if (!metadata.isEmpty()) {

                            var newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                            var newImageGraphics = newImage.createGraphics();

                            if (metadata.base != null) {
                                var basePair = loadTexture(metadata.base.withSuffix(".png").withPrefix("textures/"));
                                var baseImage = basePair.getFirst();
                                for (int y = 0; y < image.getHeight(); y += baseImage.getHeight()) {
                                    for (int x = 0; x < image.getHeight(); x += baseImage.getWidth()) {
                                        newImageGraphics.drawImage(baseImage, x, y, baseImage.getWidth(), baseImage.getHeight(), null);
                                    }
                                }
                            }

                            newImageGraphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                            if (metadata.overlay != null) {
                                var overlayPair = loadTexture(metadata.overlay.withSuffix(".png").withPrefix("textures/"));
                                var overlayImage = overlayPair.getFirst();

                                for (int y = 0; y < image.getHeight(); y += overlayImage.getHeight()) {
                                    for (int x = 0; x < image.getHeight(); x += overlayImage.getWidth()) {
                                        newImageGraphics.drawImage(overlayImage, x, y, overlayImage.getWidth(), overlayImage.getHeight(), null);
                                    }
                                }
                            }
                            Path path = metadata.output == null ? packOutput.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(rl.getNamespace()).resolve(rl.getPath())
                                    : packOutput.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(metadata.output.getNamespace()).resolve(metadata.output.withSuffix(".png").withPrefix("textures/").getPath());
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            ImageIO.write(newImage, "png", stream);
                            output.writeIfNeeded(path, stream.toByteArray(), HashCode.fromInt(newImage.hashCode()));
                            GTTCore.LOGGER.info("Written image {}", path.toAbsolutePath());
                            newImageGraphics.dispose();
                        }
                    } catch (IOException e) {
                        GTTCore.LOGGER.error(e);
                    }
                }));
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public @NotNull String getName() {
        return "GTT Texture Provider";
    }

    private Pair<BufferedImage, GTTMetadataSection> loadTexture(ResourceLocation location) throws IOException {
        var resource = existingFileHelper.getResource(location, PackType.CLIENT_RESOURCES);
        //GTTCore.LOGGER.info("Loaded texture: {}", location);
        InputStream stream = resource.open();
        BufferedImage image = ImageIO.read(stream);
        stream.close();
        GTTMetadataSection metadata = GTTMetadataSection.EMPTY;
        try {
            metadata = resource.metadata().getSection(new MetadataSectionSerializer<GTTMetadataSection>() {
                @Override
                public @NotNull String getMetadataSectionName() {
                    return GTTCore.MOD_ID;
                }

                @Override
                public @NotNull GTTMetadataSection fromJson(com.google.gson.@NotNull JsonObject json) {
                    ResourceLocation base = null;
                    ResourceLocation overlay = null;
                    ResourceLocation output = null;
                    if (json.isJsonObject()) {
                        JsonObject obj = json.getAsJsonObject();
                        if (obj.has("base")) {
                            JsonElement element = obj.get("base");
                            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
                                base = new ResourceLocation(element.getAsString());
                            }
                        }
                        if (obj.has("overlay")) {
                            JsonElement element = obj.get("overlay");
                            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
                                overlay = new ResourceLocation(element.getAsString());
                            }
                        }
                        if (obj.has("output")) {
                            JsonElement element = obj.get("output");
                            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
                                output = new ResourceLocation(element.getAsString());
                            }
                        }
                    }
                    return new GTTMetadataSection(base, overlay, output);
                }
            }).orElse(GTTMetadataSection.EMPTY);
        } catch (IOException ignored) {}
        return Pair.of(image, metadata);
    }

    public record GTTMetadataSection(ResourceLocation base, ResourceLocation overlay, ResourceLocation output){
        public static final GTTMetadataSection EMPTY = new GTTMetadataSection(null, null, null);

        public boolean isEmpty() {
            return base == null && overlay == null && output == null;
        }
    }
}
