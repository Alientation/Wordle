package com.alientation.wordle.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.alientation.wordle.graphics.renderable.RenderableComponent;


public class KeyInput extends KeyAdapter {
	
	private RenderableComponent container;
	
	public KeyInput(RenderableComponent container) {
		this.container = container;
	}
	
	public void keyPressed(KeyEvent e) {
		container.keyPressed(e);
	}
}
