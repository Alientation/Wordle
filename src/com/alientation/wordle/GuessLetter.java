package com.alientation.wordle;

public class GuessLetter {
	private char character;
	private LetterStatus status;
	
	public GuessLetter() {
		this(' ');
	}
	
	public GuessLetter(char c) {
		this.character = c;
		
		this.status = LetterStatus.EMPTY;
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
	
	public LetterStatus getStatus() {
		return this.status;
	}
	
	@Override
	public String toString() {
		return "Letter (" + character + ") " + super.toString();
	}
}
