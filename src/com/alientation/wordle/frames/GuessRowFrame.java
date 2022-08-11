package com.alientation.wordle.frames;


import java.awt.Color;

import com.alientation.wordle.Game;
import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.Sizing;
import com.alientation.gui.graphics.renderable.collections.stack.RenderableStack;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;

public class GuessRowFrame  {
	private RenderableStack renderable;
	private LetterFrame[] letters;
	
	public GuessRowFrame(Renderable container) {
		renderable = new RenderableStack.Builder()
				.container(container)
				.marginX(new StaticDimension(3))
				.marginY(new StaticDimension(2))
				.spacing(new StaticDimension(5))
				.color(Color.WHITE)
				.sizing(Sizing.HORIZONTAL_EQUAL)
				.build();
		
		letters = new LetterFrame[Game.WORD_LENGTH];
		
		for (int i = 0; i < Game.WORD_LENGTH; i++) {
			letters[i] = new LetterFrame(renderable);
			renderable.addRenderable(letters[i].getRenderable());
		}
	}
	public RenderableStack getRenderable() { return renderable; }
	public void setRenderable(RenderableStack renderable) { this.renderable = renderable; }

}
