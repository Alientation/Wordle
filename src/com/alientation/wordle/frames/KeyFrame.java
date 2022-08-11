package com.alientation.wordle.frames;

import java.awt.Graphics;

import com.alientation.wordle.LetterStatus;
import com.alientation.wordle.frames.old.KeyRowFrame_OLD;

public class KeyFrame {
	private KeyRowFrame_OLD container;
	private char character;
	private LetterStatus status;
	
	public KeyFrame(KeyRowFrame_OLD container) {
		this(container,' ');
	}
	
	public KeyFrame(KeyRowFrame_OLD container, char character) {
		this.status = LetterStatus.EMPTY;
		this.container = container;
		this.character = character;
	}
	
	public void setCharacter(char guess) { this.character = guess; }
	public char getCharacter() { return this.character; }
	public void setStatus(LetterStatus status) { this.status = status; }
	public void render(Graphics g) {
		
	}
}
