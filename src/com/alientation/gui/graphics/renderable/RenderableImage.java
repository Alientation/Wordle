package com.alientation.gui.graphics.renderable;

import com.twelvemonkeys.image.ResampleOp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

public class RenderableImage extends RenderableComponent {
    private final String imagePath;
    private BufferedImage bufferedImage, originalImage;

    public RenderableImage(Builder builder) {
        super(builder);
        this.imagePath = builder.imagePath;
        this.bufferedImage = builder.bufferedImage;
        this.originalImage = builder.originalImage;


        if (this.bufferedImage == null) {
            try {
                BufferedImage image = ImageIO.read(new File(imagePath));
                originalImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = originalImage.createGraphics();
                g.drawImage(image, 0, 0, null);
                g.dispose();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        updateImage();
    }

    @Override
    public void resized() {
        super.resized();
        updateImage();
    }

    /**
     * Perform Lanczos Resizing
     * <a href="https://stackoverflow.com/questions/24745147/java-resize-image-without-losing-quality">...</a>
     *
     */
    public void updateImage() {
        BufferedImageOp sampler = new ResampleOp(safeWidth(), safeHeight(), ResampleOp.FILTER_LANCZOS);
        bufferedImage = sampler.filter(originalImage,null);
        this.requireRenderUpdate = true;
    }

    public String getImagePath() {
        return imagePath;
    }
    public BufferedImage getImage() {
        return bufferedImage;
    }

    public static class Builder<T extends Builder<T>> extends RenderableComponent.Builder<T> {
        protected String imagePath;
        protected BufferedImage bufferedImage;
        protected BufferedImage originalImage;

        public Builder() {

        }
        public T imagePath(String imagePath) {
            this.imagePath = imagePath;
            return (T) this;
        }

        public T bufferedImage(BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
            return (T) this;
        }

        public T originalImage(BufferedImage originalImage) {
            this.originalImage = originalImage;
            return (T) this;
        }

        public RenderableImage build() throws IllegalStateException {
            validate();
            return new RenderableImage(this);
        }

        public void validate() throws IllegalStateException {
            super.validate();
        }
    }
}
