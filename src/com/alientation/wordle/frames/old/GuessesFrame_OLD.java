package com.alientation.wordle.frames.old;

import java.awt.Color;
import java.awt.Graphics;

import com.alientation.gui.graphics.renderable.Renderable_OLD;
import com.alientation.wordle.words.Word;
import com.alientation.wordle.words.WordList;

/**
 * Container for all the available guesses and the main processor for the game
 */
public class GuessesFrame_OLD extends Renderable_OLD {
	public static int roundCount = 0;
	private static WordList validWords;
	private static WordList wordleWords;
	
	private int id;
	private String mysteryWord;
	private char[] mysteryCharacters;
	private LetterRowFrame_OLD[] rows;
	private int currentRow = 0;
	private boolean hasEnded;
	
	public GuessesFrame_OLD(WordleFrame_OLD container, final String VALID_WORDS, final String WORDLE_WORDS, int numberOfGuesses, int wordLength, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, boolean symmetricalDimensions, boolean symmetricalMargins) {
		super(container,relX,minX,maxX, relY, minY, maxY, relWidth, minWidth, maxWidth, relHeight, minHeight, maxHeight, relMarginX, minMarginX, maxMarginX, relMarginY, minMarginY, maxMarginY, color, symmetricalDimensions, symmetricalMargins);
		
		id = roundCount++;
		
		rows = new LetterRowFrame_OLD[numberOfGuesses];
		for (int i = 0; i < numberOfGuesses; i++)
			rows[i] = new LetterRowFrame_OLD(this, wordLength,0f,0,Integer.MAX_VALUE,i * (1f / numberOfGuesses),0,Integer.MAX_VALUE,1f,0,Integer.MAX_VALUE,1f / numberOfGuesses,0,Integer.MAX_VALUE,0.002f,0,Integer.MAX_VALUE,0.005f,0,Integer.MAX_VALUE,Color.red,false,true);
		
		validWords = WordList.wordLists.get(VALID_WORDS);
		wordleWords = WordList.wordLists.get(WORDLE_WORDS);
		
		mysteryWord = wordleWords.getRandomWord().getWord();
		mysteryCharacters = mysteryWord.toCharArray();
	}
	
	
	public void letterGuessed(char c) {
		rows[currentRow].addLetter(c);
	}
	
	public boolean isValidWord() {
		return validWords.containsWord(new Word(rows[currentRow].getWord()));
	}
	
	public boolean validateGuess() {
		if (!rows[currentRow].isFull())
			return false;
		if (!isValidWord())
			return false;
		rows[currentRow].validate();
		return true;
	}
	
	public void setMysteryWord(String newWord) {
		mysteryWord = newWord;
	}
	
	public String getMysteryWord() {
		return mysteryWord;
	}
	
	public char[] getMysteryCharacters() { 
		return mysteryCharacters;
	}
	
	public int id() {
		return id;
	}
	
	public void render(Graphics g) {
		for (int yLevel = 0; yLevel < rows.length; yLevel++) {
			rows[yLevel].render(g);
		}
	}
	
	public void resize() {
		super.resized();
		for (int i = 0; i < rows.length; i++)
			rows[i].resize();
	}
	
	public boolean hasEnded() {
		return hasEnded;
	}
	
	@Override
	public String toString() {
		return "Round " + id + " " + super.toString();
	}
}
