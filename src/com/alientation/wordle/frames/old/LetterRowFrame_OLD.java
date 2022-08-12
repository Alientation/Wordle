package com.alientation.wordle.frames.old;

import java.awt.Color;
import java.awt.Graphics;

import com.alientation.wordle.LetterStatus;
import com.alientation.gui.graphics.renderable.DeprecatedRenderable;

/**
 * Holder for all guess letters
 * 
 */
public class LetterRowFrame_OLD extends DeprecatedRenderable {
	private static int count = 0;
	private GuessesFrame_OLD round;
	private int id;
	private int wordLength;
	private LetterFrame_OLD[] letters;
	private int nextLetterIndex = 0;
	
	public LetterRowFrame_OLD(GuessesFrame_OLD round,int length, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		super(round,relX,minX,maxX, relY, minY, maxY, relWidth, minWidth, maxWidth, relHeight, minHeight, maxHeight, relMarginX, minMarginX, maxMarginX, relMarginY, minMarginY, maxMarginY, color, symmetricalDimensions, symmetricalMargins);
		this.round = round;
		id = count++;
		wordLength = length;
		letters = new LetterFrame_OLD[wordLength];
		for (int i = 0; i < wordLength; i++)
			letters[i] = new LetterFrame_OLD(this,i * (1f / wordLength),0,Integer.MAX_VALUE,0f,0,Integer.MAX_VALUE,1f / wordLength,0,Integer.MAX_VALUE,1f,0,Integer.MAX_VALUE,0.005f,2,Integer.MAX_VALUE,0.008f,2,Integer.MAX_VALUE,Color.blue,true,true);
	}
	
	/**
	 * Adds a letter to the guess if possible
	 * 
	 * @param letter
	 * @return whether the letter was added or not
	 */
	public boolean addLetter(char letter) {
		if (nextLetterIndex >= wordLength)
			return false;
		letters[nextLetterIndex].setCharacter(letter);
		nextLetterIndex++;
		return true;
	}
	
	/**
	 * Validates all guess letters to determine their correct statuses and whether the player has won
	 * 
	 * @return	whether the guess is correct or not
	 */
	public boolean validate() {
		if (getWord().equals(round.getMysteryWord())) {
			for (int i = 0; i < letters.length; i++)
				letters[i].setStatus(LetterStatus.CORRECT);
			return true;
		}
		char[] correctLetters = round.getMysteryCharacters();
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].getCharacter() == correctLetters[i]) {
				letters[i].setStatus(LetterStatus.CORRECT);
				correctLetters[i] = '-';
				continue;
			}
			for (int ii = 0; ii < round.getMysteryCharacters().length; ii++) {
				if (correctLetters[ii] == letters[i].getCharacter()) {
					letters[i].setStatus(LetterStatus.CLOSE);
					correctLetters[ii] = '-';
					break;
				} else
					letters[i].setStatus(LetterStatus.INCORRECT);
			}
		}
		return false;
	}
	
	public String getWord() {
		StringBuilder word = new StringBuilder();
		for (int i = 0; i < letters.length; i++)
			word.append(letters[i].getCharacter());
		return word.toString();
	}
	
	public int length() {
		return nextLetterIndex;
	}
	
	public boolean isFull() { 
		return nextLetterIndex == wordLength;
	}
	
	public int id() {
		return id;
	}
	
	public void render(Graphics g) {
		for (int xLevel = 0; xLevel < wordLength; xLevel++) {
			letters[xLevel].render(g);
		}
	}
	
	public void resize() {
		super.resized();
		for (int i = 0; i < letters.length; i++)
			letters[i].resize();
	}
	
	/**
	 * Resets the static row counter so that more rounds can be played without breaking
	 */
	public static void roundEnd() {
		count = 0;
	}
	
	@Override
	public String toString() {
		return "Row " + id + " " + super.toString();
	}
}
