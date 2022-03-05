package com.alientation.wordle.words;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WordList {
	
	public static HashMap<String,WordList> wordLists = new HashMap<>();
	
	private ArrayList<Word> words;
	private HashSet<Word> set;
	private String fileName, id;
	private int wordLength;
	
	
	public WordList(String id, String fileName) {
		this.fileName = fileName;
		this.id = id;
		this.words = new ArrayList<>();
		this.set = new HashSet<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			for (String word = in.readLine(); word != null; word = in.readLine()) {
				this.words.add(new Word(word));
				this.set.add(words.get(words.size()-1));
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.wordLength = this.words.get(0).length();
		wordLists.put(id, this);
	}
	
	public ArrayList<Word> getWords() {
		return this.words;
	}
	
	public Word getRandomWord() {
		return this.words.get((int)(Math.random() * words.size()));
	}
	
	public boolean containsWord(Word word) {
		return this.set.contains(word);
	}
	
	public HashSet<Word> getSet() {
		return this.set;
	}
	
	public int wordLength() {
		return this.wordLength;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String getID() {
		return this.id;
	}
}
