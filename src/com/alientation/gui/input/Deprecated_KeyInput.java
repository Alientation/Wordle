package com.alientation.gui.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.alientation.gui.graphics.renderable.RenderableComponent;

@Deprecated
public class Deprecated_KeyInput extends KeyAdapter {
	
	private RenderableComponent container;
	@Deprecated
	public Deprecated_KeyInput(RenderableComponent container) {
		this.container = container;
	}
	@Deprecated
	public void keyPressed(KeyEvent e) {
		container.keyPressed(e);
	}
}
