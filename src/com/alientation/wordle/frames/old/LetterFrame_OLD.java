package com.alientation.wordle.frames.old;

import java.awt.Color;
import java.awt.Graphics;

import com.alientation.wordle.LetterStatus;
import com.alientation.gui.graphics.renderable.Renderable_OLD;

/**
 * Holder class for a letter in a guess row. Used to make rendering easier.
 *
 */
public class LetterFrame_OLD extends Renderable_OLD{
	private LetterRowFrame_OLD letterRow;
	private char character;
	private LetterStatus status;
	private TextDisplayFrame_OLD td;
	
	/**
	 * 
	 * @param container The guess row that holds this guess letter
	 */
	public LetterFrame_OLD(LetterRowFrame_OLD container, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		this(container,' ',relX,minX,maxX, relY, minY, maxY, relWidth, minWidth, maxWidth, relHeight, minHeight, maxHeight, relMarginX, minMarginX, maxMarginX, relMarginY, minMarginY, maxMarginY, color, symmetricalDimensions, symmetricalMargins);
	}
	
	public LetterFrame_OLD(LetterRowFrame_OLD letterRow, char character, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		super(letterRow,relX,minX,maxX, relY, minY, maxY, relWidth, minWidth, maxWidth, relHeight, minHeight, maxHeight, relMarginX, minMarginX, maxMarginX, relMarginY, minMarginY, maxMarginY, color, symmetricalDimensions, symmetricalMargins);
		this.status = LetterStatus.EMPTY;
		this.container = letterRow;
		this.character = character;
		
		this.td = new TextDisplayFrame_OLD(this,"" + character,0f,0,Integer.MAX_VALUE,0f,0,Integer.MAX_VALUE,1f,0,Integer.MAX_VALUE,1f,0,Integer.MAX_VALUE,0f,0,Integer.MAX_VALUE,0f,0,Integer.MAX_VALUE,Color.CYAN,0.05f,0.05f,true,true);
	}
	
	public void setCharacter(char character) {
		this.character = character;
	}
	
	public void setStatus(LetterStatus status) {
		this.status = status;
	}
	
	public char getCharacter() {
		return character;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(x, y, width, height);
	}
	
	public void resize() {
		super.resized();
		//td.resize();
	}
	
	@Override
	public String toString() {
		return "Letter (" + character + ") " + super.toString();
	}
}
