package com.alientation.gui.graphics.renderable;

import java.awt.image.BufferedImage;

public class RenderableImage {
    private RenderableComponent container;
    private BufferedImage bi;

    public RenderableImage(RenderableComponent renderableComponent, BufferedImage bi) {
        this.container = renderableComponent;
        this.bi = bi;
    }



    public BufferedImage getImage() {
        return bi;
    }
}
