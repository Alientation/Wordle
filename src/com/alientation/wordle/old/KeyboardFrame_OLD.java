package com.alientation.wordle.old;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import com.alientation.wordle.graphics.frames.KeyFrame;
import com.alientation.wordle.graphics.renderable.Renderable_OLD;

public class KeyboardFrame_OLD extends Renderable_OLD{
	
	private HashMap<Character,KeyFrame> keymap;
	
	public KeyboardFrame_OLD(MainFrame_OLD frame, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
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
