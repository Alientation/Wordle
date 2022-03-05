package com.alientation.wordle;


/**
 * Holder for all guess letters
 * 
 */
public class GuessRow {
	private static int count = 0;
	
	private WordleRound round;
	private int id;
	private int wordLength;
	private GuessLetter[] letters;
	private int nextLetterIndex = 0;
	
	public GuessRow(WordleRound round, int wordLength) {
		this.round = round;
		this.wordLength = wordLength;
		this.id = count++;
		this.letters = new GuessLetter[wordLength];
		for (int i = 0; i < wordLength; i++)
			letters[i] = new GuessLetter();
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
		return round.getMysteryWord().validate(this);
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
	
	public GuessLetter[] getLetters() {
		return this.letters;
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
