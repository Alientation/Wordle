package com.alientation.wordle;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.EventListener;
import com.alientation.gui.graphics.events.types.mouse.MouseClickedEvent;
import com.alientation.gui.graphics.font.PixelFont;
import com.alientation.wordle.frames.MainFrame;
import com.alientation.wordle.words.WordList;

import java.io.Serial;


public class Game extends Window {
	@Serial
	private static final long serialVersionUID = 1L;
	public static final int NUMBER_GUESSES = 6, WORD_LENGTH = 5;
	public static final String VALID_WORDS_FILENAME = RESOURCE + "allowed-guesses-normal.txt";
	public static final String WORDLE_WORDS_FILENAME = RESOURCE + "wordle-words-normal.txt";
	public static Game game;
	
	
	private PixelFont pf;
	
	private MainFrame newMainFrame;
	
	public Game() {
		super(INIT_WIDTH,INIT_HEIGHT,INIT_TITLE);
		Game.game = this;
		pf = new PixelFont();
		
		newMainFrame = new MainFrame(this.renderable);
		
		new WordList(VALID_WORDS_FILENAME,VALID_WORDS_FILENAME); //load files in separate thread
		new WordList(WORDLE_WORDS_FILENAME,WORDLE_WORDS_FILENAME);
		newRound(VALID_WORDS_FILENAME, WORDLE_WORDS_FILENAME, NUMBER_GUESSES, WORD_LENGTH);

		registerEventListener(new EventListener() {
			@com.alientation.gui.annotations.EventListener
			public void click(MouseClickedEvent event) {
				System.out.println("time: " +(event.getInitTime() - System.currentTimeMillis()) + " -> clicked!");
				renderable.requireRenderUpdate();
			}
		});

		resize();
		this.start();
	}
	
	public boolean newRound(String validWordList, String wordleWords, int numberOfGuesses, int wordLength) {
		
		return true;
		//return mainFrame.getWordleFrame().newRound(validWordList,wordleWords, numberOfGuesses, wordLength);
	}
	
	
	public static void main(String[] args) {
		new Game();
	}
	
	public PixelFont getPixelFont() { return pf; }
}
