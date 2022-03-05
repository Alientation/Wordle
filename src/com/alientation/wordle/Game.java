package com.alientation.wordle;

import com.alientation.wordle.graphics.Window;
import com.alientation.wordle.graphics.font.PixelFont;
import com.alientation.wordle.graphics.frames.MainFrame;
import com.alientation.wordle.old.TextDisplayFrame_OLD;
import com.alientation.wordle.words.WordList;


public class Game extends Window {
	private static final long serialVersionUID = 1L;
	public static final int NUMBER_GUESSES = 6, WORD_LENGTH = 5;
	public static final String VALID_WORDS_FILENAME = "allowed-guesses-normal.txt", WORDLE_WORDS_FILENAME = "wordle-words-normal.txt";
	public static Game game;
	
	
	private PixelFont pf;
	
	private MainFrame newMainFrame;
	
	public Game() {
		super(INIT_WIDTH,INIT_HEIGHT,INIT_TITLE);
		Game.game = this;
		pf = new PixelFont();
		TextDisplayFrame_OLD.setPixelFont(pf);
		
		newMainFrame = new MainFrame(this.renderable);
		
		new WordList(VALID_WORDS_FILENAME,VALID_WORDS_FILENAME);
		new WordList(WORDLE_WORDS_FILENAME,WORDLE_WORDS_FILENAME);
		newRound(VALID_WORDS_FILENAME, WORDLE_WORDS_FILENAME, NUMBER_GUESSES, WORD_LENGTH);
		
		this.start();
		resize();
	}
	
	/**
	 * Used whenever there is a need to extract information from other processes
	 */
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public void resize() {
		
	}
	
	@Override
	public void preRender() {
		super.preRender();
	}
	
	@Override
	public void postRender() {
		super.postRender();
	}
	
	@Override
	public void render() {
		preRender();
		this.renderable.render(g);
		postRender();
	}
	
	@Override
	public synchronized void start() {
		super.start();
	}
	
	@Override
	public synchronized void stop() {
		super.stop();
	}
	
	@Override
	public void run() {
		super.run();
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
