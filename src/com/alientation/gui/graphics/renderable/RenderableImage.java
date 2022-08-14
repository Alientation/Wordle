package com.alientation.gui.graphics.renderable;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionComponent;
import com.twelvemonkeys.image.ResampleOp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class RenderableImage extends RenderableComponent {
    private String imagePath;
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
     * https://stackoverflow.com/questions/24745147/java-resize-image-without-losing-quality
     *
     */
    public void updateImage() {
        BufferedImageOp resampler = new ResampleOp(safeWidth(), safeHeight(), ResampleOp.FILTER_LANCZOS);
        bufferedImage = resampler.filter(originalImage,null);
    }

    public String getImagePath() {
        return imagePath;
    }
    public BufferedImage getImage() {
        return bufferedImage;
    }

    public static class Builder extends RenderableComponent.Builder {
        protected String imagePath;
        protected BufferedImage bufferedImage;
        protected BufferedImage originalImage;

        public Builder() {

        }

        public Builder window(Window window) {
            super.window(window);
            return this;
        }

        public Builder subreference(RenderableComponent renderable) {
            super.subreference(renderable);
            return this;
        }

        public Builder subreferences(Collection<RenderableComponent> renderables) {
            super.subreferences(renderables);
            return this;
        }

        public Builder render(BufferedImage render) {
            super.render(render);
            return this;
        }

        public Builder id(String id) {
            super.id(id);
            return this;
        }

        public Builder dimensionReference(DimensionComponent dc) {
            super.dimensionReference(dc);
            return this;
        }

        public Builder dimensionReferences(Collection<DimensionComponent> dimensions) {
            super.dimensionReferences(dimensions);
            return this;
        }

        public Builder container(Renderable container) {
            super.container(container);
            return this;
        }

        public Builder x(com.alientation.gui.graphics.renderable.dimension.Dimension x) {
            super.x(x);
            return this;
        }

        public Builder y(com.alientation.gui.graphics.renderable.dimension.Dimension y) {
            super.y(y);
            return this;
        }

        public Builder width(com.alientation.gui.graphics.renderable.dimension.Dimension width) {
            super.width(width);
            return this;
        }

        public Builder height(com.alientation.gui.graphics.renderable.dimension.Dimension height) {
            super.height(height);
            return this;
        }

        public Builder marginX(com.alientation.gui.graphics.renderable.dimension.Dimension marginX) {
            super.marginX(marginX);
            return this;
        }

        public Builder marginY(com.alientation.gui.graphics.renderable.dimension.Dimension marginY) {
            super.marginY(marginY);
            return this;
        }

        public Builder radius(com.alientation.gui.graphics.renderable.dimension.Dimension radius) {
            super.radius(radius);
            return this;
        }

        public Builder thickness(Dimension thickness) {
            super.thickness(thickness);
            return this;
        }

        public Builder backgroundTransparency(float backgroundTransparency) {
            super.backgroundTransparency(backgroundTransparency);
            return this;
        }

        public Builder frameTransparency(float frameTransparency) {
            super.frameTransparency(frameTransparency);
            return this;
        }

        public Builder backgroundImage(RenderableImage backgroundImage) {
            super.backgroundImage(backgroundImage);
            return this;
        }

        public Builder backgroundColor(Color backgroundColor) {
            super.backgroundColor(backgroundColor);
            return this;
        }

        public Builder frameColor(Color frameColor) {
            super.frameColor(frameColor);
            return this;
        }

        public Builder visible(boolean visible) {
            super.visible(visible);
            return this;
        }

        public Builder zIndex(int zIndex) {
            super.zIndex(zIndex);
            return this;
        }
        public Builder imagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Builder bufferedImage(BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
            return this;
        }

        public Builder originalImage(BufferedImage originalImage) {
            this.originalImage = originalImage;
            return this;
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
