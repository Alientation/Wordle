package com.alientation.wordle;

import com.alientation.wordle.words.Word;
import com.alientation.wordle.words.WordList;

public class WordleRound {
	public static int roundCount = 0;
	
	private WordList validWords, wordleWords;
	private int id;
	private Word mysteryWord;
	private GuessRow[] rows;
	private int curRow = 0;
	private boolean hasEnded;
	
	public WordleRound(final String VALID_WORDS, final String WORDLE_WORDS, int numberOfGuesses) {
		id = roundCount++;
		
		validWords = WordList.wordLists.get(VALID_WORDS);
		wordleWords = WordList.wordLists.get(WORDLE_WORDS);
		
		rows = new GuessRow[numberOfGuesses];
		for (int i = 0; i < numberOfGuesses; i++)
			rows[i] = new GuessRow(this,validWords.wordLength());
		
		
		mysteryWord = wordleWords.getRandomWord();
	}
	
	public boolean letterGuessed(char c) {
		return rows[curRow].addLetter(c);
	}
	
	public boolean isValidWord() {
		return validWords.containsWord(new Word(rows[curRow].getWord()));
	}
	
	public boolean validateGuess() {
		if (!rows[curRow].isFull())
			return false;
		if (!isValidWord())
			return false;
		rows[curRow].validate();
		return true;
	}
	
	public void setMysteryWord(Word newWord) {
		mysteryWord = newWord;
	}
	
	public Word getMysteryWord() {
		return mysteryWord;
	}
	
	
	public int id() {
		return id;
	}
	
	public boolean hasEnded() {
		return hasEnded;
	}
	
	@Override
	public String toString() {
		return "Round " + id + " " + super.toString();
	}
}
