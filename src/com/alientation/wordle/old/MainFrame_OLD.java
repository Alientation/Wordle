package com.alientation.wordle.old;

import java.awt.Color;
import java.awt.Graphics;

import com.alientation.wordle.Game;
import com.alientation.wordle.graphics.renderable.Renderable_OLD;

/**
 * Container Window that has the wordle window and other necessary windows like data visualization
 * 
 * TODO: Store location, width, height, color information in each frame and component to make rendering easier
 */
public class MainFrame_OLD extends Renderable_OLD{
	
	private WordleFrame_OLD wordleFrame;
	private DataFrame_OLD dataFrame;
	private KeyboardFrame_OLD keyboardFrame;
	
	
	public MainFrame_OLD(Game game, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		super(null,relX,minX,maxX, relY, minY, maxY, relWidth, minWidth, maxWidth, relHeight, minHeight, maxHeight, relMarginX, minMarginX, maxMarginX, relMarginY, minMarginY, maxMarginY, color, symmetricalDimensions, symmetricalMargins);
		wordleFrame = new WordleFrame_OLD(this,0.25f,0,Integer.MAX_VALUE, 0f,0,Integer.MAX_VALUE, 0.5f,0,Integer.MAX_VALUE, 0.7f,0,Integer.MAX_VALUE, 0.01f,0,Integer.MAX_VALUE, 0.01f,0,Integer.MAX_VALUE, Color.white,false,false);
		dataFrame = new DataFrame_OLD(this,0.75f,0,Integer.MAX_VALUE,0f,0,Integer.MAX_VALUE,0.25f,0,Integer.MAX_VALUE,1f,0,Integer.MAX_VALUE,0.01f,0,Integer.MAX_VALUE,0.01f,0,Integer.MAX_VALUE,Color.DARK_GRAY,false,false);
		keyboardFrame = new KeyboardFrame_OLD(this,0.25f,0,Integer.MAX_VALUE,0.7f,0,Integer.MAX_VALUE,0.5f,0,Integer.MAX_VALUE,0.3f,0,Integer.MAX_VALUE,0.01f,0,Integer.MAX_VALUE,0.01f,0,Integer.MAX_VALUE,Color.MAGENTA,false,false);
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x,y,width,height);
		wordleFrame.render(g);
		dataFrame.render(g);
		keyboardFrame.render(g);
	}
	
	public void resize() {
		super.resized();
		wordleFrame.resize();
		dataFrame.resize();
		keyboardFrame.resize();
	}
	
	public WordleFrame_OLD getWordleFrame() {
		return wordleFrame;
	}
	public DataFrame_OLD getDataFrame() {
		return dataFrame;
	}
	public KeyboardFrame_OLD getKeyboardFrame() {
		return keyboardFrame;
	}
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public String toString() {
		return "MainFrame " + super.toString();
	}
}
