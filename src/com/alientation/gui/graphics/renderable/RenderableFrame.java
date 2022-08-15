package com.alientation.gui.graphics.renderable;

import com.alientation.gui.graphics.renderable.dimension.Dimension;

import java.awt.*;

public class RenderableFrame {
    private RenderableComponent container;
    private Dimension x,y,width,height,radiusWidth, radiusHeight,thickness;
    private Color color;
    private float transparency;

    public RenderableFrame(RenderableComponent container,
                           Dimension x, Dimension y, Dimension width, Dimension height,
                           Dimension radiusWidth, Dimension radiusHeight, Dimension thickness,
                           Color color, float transparency) {
        this.container = container;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusWidth = radiusWidth;
        this.radiusHeight = radiusHeight;
        this.thickness = thickness;
        this.color = color;
        this.transparency = transparency;
    }

    public RenderableComponent getContainer() { return container; }
    public void setContainer(RenderableComponent container) { this.container = container; }
    public Dimension getX() { return x; }
    public void setX(Dimension x) { this.x = x; }
    public Dimension getY() { return y; }
    public void setY(Dimension y) { this.y = y; }
    public Dimension getWidth() { return width; }
    public void setWidth(Dimension width) { this.width = width; }
    public Dimension getHeight() { return height; }
    public void setHeight(Dimension height) { this.height = height; }
    public Dimension getRadiusWidth() { return radiusWidth; }
    public void setRadiusWidth(Dimension radiusWidth) { this.radiusWidth = radiusWidth; }
    public Dimension getRadiusHeight() { return radiusHeight; }
    public void setRadiusHeight(Dimension radiusHeight) { this.radiusHeight = radiusHeight; }
    public Dimension getThickness() { return thickness; }
    public void setThickness(Dimension thickness) { this.thickness = thickness; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public float getTransparency() { return transparency; }
    public void setTransparency(float transparency) { this.transparency = transparency; }
}
