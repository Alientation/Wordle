package com.alientation.wordle.frames.old;

import java.awt.Color;
import java.awt.Graphics;

import com.alientation.gui.graphics.renderable.DeprecatedRenderable;

public class DataFrame_OLD extends DeprecatedRenderable {
	
	public DataFrame_OLD(MainFrame_OLD frame, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		super(frame,relX,minX,maxX, relY, minY, maxY, relWidth, minWidth, maxWidth, relHeight, minHeight, maxHeight, relMarginX, minMarginX, maxMarginX, relMarginY, minMarginY, maxMarginY, color, symmetricalDimensions, symmetricalMargins);
	}
	
	public void resize() {
		super.resized();
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
}
