package com.noxcrew.noxesium.feature.skull;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * A custom chat component that renders a player's face at its location. The
 * input is directly received as a texture but can optionally be a unique id.
 */
public class SkullContents implements ComponentContents {

    @Nullable
    private final UUID uuid;
    private final CompletableFuture<String> texture;
    private final boolean grayscale;
    private final int advance;
    private final int ascent;
    private final float scale;
    private final SkullConfig config;

    public SkullContents(@Nullable UUID uuid, CompletableFuture<String> texture, boolean grayscale, int advance, int ascent, float scale) {
        this.uuid = uuid;
        this.texture = texture;
        this.grayscale = grayscale;
        this.advance = advance;
        this.ascent = ascent;
        this.scale = scale;
        this.config = new SkullConfig(texture, new SkullProperties(this));
    }

    @Nullable
    public UUID getUuid() {
        return uuid;
    }

    @Nullable
    public String getTexture() {
        // We simply use whatever texture we currently have, otherwise we lose the data.
        return texture.getNow(null);
    }

    public boolean isGrayscale() {
        return grayscale;
    }

    public int getAdvance() {
        return advance;
    }

    public int getAscent() {
        return ascent;
    }

    public float getScale() {
        return scale;
    }

    /**
     * Returns the plain-text representation of this skull in the skull font.
     */
    public String getText() {
        return Character.toString(CustomSkullFont.claim(config));
    }

    public SkullConfig getConfig() {
        return config;
    }

    public SkullProperties getProperties() {
        return config.properties();
    }

    @Override
    public <T> Optional<T> visit(FormattedText.ContentConsumer<T> contentConsumer) {
        return contentConsumer.accept(getText());
    }

    @Override
    public <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> styledContentConsumer, Style style) {
        return styledContentConsumer.accept(style.withFont(CustomSkullFont.RESOURCE_LOCATION), getText());
    }

    @Override
    public MutableComponent resolve(@Nullable CommandSourceStack commandSourceStack, @Nullable Entity entity, int i) {
        return Component.literal(getText()).setStyle(Style.EMPTY.withFont(CustomSkullFont.RESOURCE_LOCATION));
    }

    @Override
    public String toString() {
        return "skull{texture='" + texture + "', grayscale='" + grayscale + "', advance='" + advance + "', ascent='" + ascent + "', scale='" + scale + "'}";
    }
}
