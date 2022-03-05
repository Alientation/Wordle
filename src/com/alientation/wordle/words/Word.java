package com.alientation.wordle.words;

import com.alientation.wordle.GuessLetter;
import com.alientation.wordle.GuessRow;
import com.alientation.wordle.LetterStatus;

public class Word implements Comparable<Word> {
	
	private String word;
	
	public Word(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int length() {
		return this.word.length();
	}
	
	public boolean validate(GuessRow row) {
		GuessLetter[] letters = row.getLetters();
		if (word.equals(row.getWord())) {
			for (int i = 0; i < letters.length; i++)
				letters[i].setStatus(LetterStatus.CORRECT);
			return true;
		}
		char[] correctLetters = word.toCharArray();
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].getCharacter() == correctLetters[i]) {
				letters[i].setStatus(LetterStatus.CORRECT);
				correctLetters[i] = '-';
				continue;
			}
			for (int ii = 0; ii < word.length(); ii++) {
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

	@Override
	public int compareTo(Word otherObj) {
		return otherObj.getWord().compareTo(word);
	}
}
