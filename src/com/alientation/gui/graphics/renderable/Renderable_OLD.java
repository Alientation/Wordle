package com.alientation.gui.graphics.renderable;

import java.awt.Color;

import com.alientation.wordle.Game;
import com.alientation.gui.util.Util;

public class Renderable_OLD {
	protected Renderable_OLD container;
	protected Game game;
	protected float relX, relY, relWidth, relHeight, relMarginX, relMarginY;
	protected int x, y, width, height, marginX, marginY;
	protected int minX, minY, minWidth, minHeight, minMarginX, minMarginY;
	protected int maxX, maxY, maxWidth, maxHeight, maxMarginX, maxMarginY;
	protected boolean symmetricalDimensions, symmetricalMargins;
	protected Color color;
	
	public Renderable_OLD(Renderable_OLD container, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		this.symmetricalDimensions = symmetricalDimensions;
		this.symmetricalMargins = symmetricalMargins;
		this.container = container;
		
		this.relX = relX;
		this.relY = relY;
		this.relWidth = relWidth;
		this.relHeight = relHeight;
		this.relMarginX = relMarginX;
		this.relMarginY = relMarginY;
		
		this.minX = minX;
		this.minY = minY;
		this.minWidth = minWidth;
		this.minHeight = minHeight;
		this.minMarginX = minMarginX;
		this.minMarginY = minMarginY;
		
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.maxMarginX = maxMarginX;
		this.maxMarginY = maxMarginY;
		
		this.resized();
		
		this.color = color;
	}
	
	public Renderable_OLD(Renderable_OLD container, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions) {
		this(container,relX,minX,maxX,relY,minY,maxY,relWidth,minWidth,maxWidth,relHeight,minHeight,maxHeight,relMarginX,minMarginX,maxMarginX,relMarginY,minMarginY,maxMarginY,color,symmetricalDimensions,false);
	}
	/*
	public Renderable(Renderable container, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color) {
		this(container,relX,minX,maxX,relY,minY,maxY,relWidth,minWidth,maxWidth,relHeight,minHeight,maxHeight,relMarginX,minMarginX,maxMarginX,relMarginY,minMarginY,maxMarginY,color,false,false);
	}
	
	public Renderable(Renderable container, float relX, float relY, float relWidth, float relHeight, float relMarginX, float relMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		this(container,relX,0,Integer.MAX_VALUE,relY,0,Integer.MAX_VALUE,relWidth,0,Integer.MAX_VALUE,relHeight,0,Integer.MAX_VALUE,relMarginX,0,Integer.MAX_VALUE,relMarginY,0,Integer.MAX_VALUE,color,symmetricalDimensions,symmetricalMargins);
	}
	
	public Renderable(Renderable container, float relX, float relY, float relWidth, float relHeight, float relMarginX, float relMarginY, Color color, boolean symmetricalDimensions) {
		this(container,relX,0,Integer.MAX_VALUE,relY,0,Integer.MAX_VALUE,relWidth,0,Integer.MAX_VALUE,relHeight,0,Integer.MAX_VALUE,relMarginX,0,Integer.MAX_VALUE,relMarginY,0,Integer.MAX_VALUE,color,symmetricalDimensions,false);
	}
	
	public Renderable(Renderable container, float relX, float relY, float relWidth, float relHeight, float relMarginX, float relMarginY, Color color) {
		this(container,relX,0,Integer.MAX_VALUE,relY,0,Integer.MAX_VALUE,relWidth,0,Integer.MAX_VALUE,relHeight,0,Integer.MAX_VALUE,relMarginX,0,Integer.MAX_VALUE,relMarginY,0,Integer.MAX_VALUE,color,false,false);
	}
	
	public Renderable(float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color) {
		this(null,relX,minX,maxX,relY,minY,maxY,relWidth,minWidth,maxWidth,relHeight,minHeight,maxHeight,relMarginX,minMarginX,maxMarginX,relMarginY,minMarginY,maxMarginY,color,false,false);
	}
	
	public Renderable(float relX, float relY, float relWidth, float relHeight, float relMarginX, float relMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		this(null,relX,0,Integer.MAX_VALUE,relY,0,Integer.MAX_VALUE,relWidth,0,Integer.MAX_VALUE,relHeight,0,Integer.MAX_VALUE,relMarginX,0,Integer.MAX_VALUE,relMarginY,0,Integer.MAX_VALUE,color,symmetricalDimensions,symmetricalMargins);
	}
	
	public Renderable(float relX, float relY, float relWidth, float relHeight, float relMarginX, float relMarginY, Color color, boolean symmetricalDimensions) {
		this(null,relX,0,Integer.MAX_VALUE,relY,0,Integer.MAX_VALUE,relWidth,0,Integer.MAX_VALUE,relHeight,0,Integer.MAX_VALUE,relMarginX,0,Integer.MAX_VALUE,relMarginY,0,Integer.MAX_VALUE,color,symmetricalDimensions,false);
	}
	
	public Renderable(float relX, float relY, float relWidth, float relHeight, float relMarginX, float relMarginY, Color color) {
		this(null,relX,0,Integer.MAX_VALUE,relY,0,Integer.MAX_VALUE,relWidth,0,Integer.MAX_VALUE,relHeight,0,Integer.MAX_VALUE,relMarginX,0,Integer.MAX_VALUE,relMarginY,0,Integer.MAX_VALUE,color,false,false);
	}
	*/
	
	public void resized() {
		int containerWidth, containerHeight, containerX, containerY;
		if (container == null) {
			containerWidth = Game.game.getWidth();
			containerHeight = Game.game.getHeight();
			containerX = 0;
			containerY = 0;
		} else {
			containerWidth = container.width;
			containerHeight = container.height;
			containerX = container.x;
			containerY = container.y;
		}
		marginX = Math.min(Math.max(Util.convertFromRel(containerWidth,relMarginX), minMarginX),maxMarginX);
		marginY = Math.min(Math.max(Util.convertFromRel(containerHeight,relMarginY),minMarginY),maxMarginY);
		if (symmetricalMargins) {
			marginX = Math.min(Math.max(Math.min(marginX, marginY),minMarginX),maxMarginX);
			marginY = Math.min(Math.max(marginX,minMarginY),maxMarginY);
		}
		
		x = Math.min(Math.max(Util.convertFromRel(containerWidth,relX) + marginX + containerX,minX),maxX);
		y = Math.min(Math.max(Util.convertFromRel(containerHeight,relY) + marginY + containerY,minY),maxY);
		width = Math.min(Math.max(Util.convertFromRel(containerWidth,relWidth) - marginX * 2,minWidth),maxWidth);
		height = Math.min(Math.max(Util.convertFromRel(containerHeight,relHeight) - marginY * 2,minHeight),maxHeight);
		
		if (symmetricalDimensions) {
			int loss = Math.abs(width - height);
			if (width < height)
				y = Math.min(Math.max(y + loss/2,minY),maxX);
			else
				x = Math.min(Math.max(x + loss/2,minX),maxY);
			width = Math.min(Math.max(Math.min(width, height),minWidth),maxWidth);
			height = Math.min(Math.max(width,minHeight),maxHeight);
		}
	}
	
	@Override
	public String toString() {
		return "{\n"+"(x,y): " + x + "," + y + "\n(w,h): " + width + "," + height + "\n(mX,mY): " + marginX + "," + marginY+"\n}";
	}
}
