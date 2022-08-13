package com.alientation.gui.graphics.renderable;

import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.RelativeDimension;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;

import java.awt.*;

public class RenderableFrame {
    private RenderableComponent container;
    private Dimension x,y,width,height,radius,thickness;
    private Color color;
    private float transparency;

    public RenderableFrame(RenderableComponent container,
                           Dimension x, Dimension y, Dimension width, Dimension height, Dimension radius, Dimension thickness,
                           Color color, float transparency) {
        this.container = container;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.thickness = thickness;
        this.color = color;
        this.transparency = transparency;
    }

    public RenderableComponent getContainer() {
        return container;
    }

    public void setContainer(RenderableComponent container) {
        this.container = container;
    }

    public Dimension getX() {
        return x;
    }

    public void setX(Dimension x) {
        this.x = x;
    }

    public Dimension getY() {
        return y;
    }

    public void setY(Dimension y) {
        this.y = y;
    }

    public Dimension getWidth() {
        return width;
    }

    public void setWidth(Dimension width) {
        this.width = width;
    }

    public Dimension getHeight() {
        return height;
    }

    public void setHeight(Dimension height) {
        this.height = height;
    }

    public Dimension getRadius() {
        return radius;
    }

    public void setRadius(Dimension radius) {
        this.radius = radius;
    }

    public Dimension getThickness() {
        return thickness;
    }

    public void setThickness(Dimension thickness) {
        this.thickness = thickness;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getTransparency() {
        return transparency;
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }

}
