package com.alientation.gui.graphics.renderable;

import java.awt.*;

public class RenderableBackground {
    private RenderableComponent container;
    private RenderableImage image;
    private Color color;
    private float transparency;

    public RenderableBackground(RenderableComponent container, RenderableImage image, Color color, float transparency) {
        this.container = container;
        this.image = image;
        this.color = color;
        this.transparency = transparency;
    }

    public RenderableBackground(RenderableComponent container) {
        this(container, null, Color.WHITE, 1f);
    }


    public RenderableComponent getContainer() { return container; }
    public void setContainer(RenderableComponent container) { this.container = container; }
    public RenderableImage getImage() { return image; }
    public void setImage(RenderableImage image) { this.image = image; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public float getTransparency() { return transparency; }
    public void setTransparency(float transparency) { this.transparency = transparency; }
}
