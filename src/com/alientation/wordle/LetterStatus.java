package com.alientation.wordle;

import java.awt.Color;

/**
 * Determine the color for rendering of each letter 
 */
public enum LetterStatus {
	INCORRECT(LetterStatus.COLOR_GRAY),CLOSE(LetterStatus.COLOR_YELLOW),CORRECT(LetterStatus.COLOR_GREEN),EMPTY(LetterStatus.COLOR_WHITE);
	
	public static final Color COLOR_GRAY = new Color(191, 191, 191);
	public static final Color COLOR_YELLOW = new Color(255, 216, 107);
	public static final Color COLOR_GREEN = new Color(109, 232, 134);
	public static final Color COLOR_WHITE = new Color(255, 255, 255);
	
	
	private final Color COLOR;
	private LetterStatus(Color color) {
		this.COLOR = color;
	}
	public Color color() {
		return this.COLOR;
	}
}
