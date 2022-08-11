package com.alientation.wordle;

import com.alientation.wordle.colors.Colors;

import java.awt.Color;

/**
 * Determine the color for rendering of each letter 
 */
public enum LetterStatus {
	INCORRECT(Colors.GRAY),CLOSE(Colors.YELLOW),CORRECT(Colors.GREEN),EMPTY(Colors.WHITE);
	
	private final Color COLOR;
	private LetterStatus(Color color) {
		this.COLOR = color;
	}
	public Color color() {
		return this.COLOR;
	}
}
