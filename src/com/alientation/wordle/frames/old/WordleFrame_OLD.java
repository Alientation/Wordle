package com.alientation.wordle.frames.old;

import java.awt.Color;
import java.awt.Graphics;

import com.alientation.gui.graphics.renderable.DeprecatedRenderable;

public class WordleFrame_OLD extends DeprecatedRenderable {
	
	private GuessesFrame_OLD currentRound;
	
	public WordleFrame_OLD(MainFrame_OLD mainFrame, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		super(mainFrame,relX,minX,maxX, relY, minY, maxY, relWidth, minWidth, maxWidth, relHeight, minHeight, maxHeight, relMarginX, minMarginX, maxMarginX, relMarginY, minMarginY, maxMarginY, color, symmetricalDimensions, symmetricalMargins);
	}
	
	public boolean newRound(String validWordList, String wordleWords, int numberOfGuesses, int wordLength) {
		if (currentRound != null && !currentRound.hasEnded())
			return false;
		currentRound = new GuessesFrame_OLD(this, validWordList,wordleWords,numberOfGuesses,wordLength,0f,0,Integer.MAX_VALUE,0f,0,Integer.MAX_VALUE,1f,0,Integer.MAX_VALUE,1f,0,Integer.MAX_VALUE,0.003f,0,Integer.MAX_VALUE,0.005f,0,Integer.MAX_VALUE, Color.gray,false,false);
		return true;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		if (currentRound != null)
			currentRound.render(g);
	}
	
	public void resize() {
		super.resized();
		currentRound.resize();
	}
	
	public GuessesFrame_OLD getCurrentRound() {
		return currentRound;
	}
	
	@Override
	public String toString() {
		return "WordleFrame " + super.toString();
	}
}
