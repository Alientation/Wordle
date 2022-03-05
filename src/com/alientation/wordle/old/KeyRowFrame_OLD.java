package com.alientation.wordle.old;

import java.awt.Color;

import com.alientation.wordle.LetterStatus;
import com.alientation.wordle.graphics.frames.KeyFrame;

public class KeyRowFrame_OLD {
	public static int count;
	
	private GuessesFrame_OLD container;
	private int id;
	private KeyFrame[] keys;
	private int index;
	
	public KeyRowFrame_OLD(GuessesFrame_OLD container, int length) {
		id = count;
		count++;
		this.container = container;
		keys = new KeyFrame[length];
	}
	
	public void addKey(char character) {
		addKey(new KeyFrame(this,character));
	}
	
	public void addKey(KeyFrame key) {
		keys[index] = key;
		index++;
	}
	
	public int id() {
		return id;
	}
	
	public void render(int x, int y, int width, int height, Color background) {
		
	}
	
	public void roundEnd() {
		for (int i = 0; i < keys.length; i++)
			keys[i].setStatus(LetterStatus.EMPTY);
	}
}
