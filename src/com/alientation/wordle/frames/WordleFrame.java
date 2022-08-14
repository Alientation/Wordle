package com.alientation.wordle.frames;

import java.awt.Color;

import com.alientation.wordle.Game;
import com.alientation.wordle.WordleRound;
import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.Sizing;
import com.alientation.gui.graphics.renderable.collections.stack.RenderableStack;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;

public class WordleFrame{
	private RenderableStack renderable;
	private WordleRound currentRound;
	private GuessRowFrame[] rows;
	
	public WordleFrame(Renderable container) {
		renderable = new RenderableStack.Builder<>()
				.container(container)
				.spacing(new StaticDimension(5))
				.backgroundColor(Color.WHITE)
				.sizing(Sizing.VERTICAL_EQUAL)
				.marginX(new StaticDimension(5))
				.marginY(new StaticDimension(5))
				.build();
		
		rows = new GuessRowFrame[Game.NUMBER_GUESSES];
		
		for (int i = 0; i < Game.NUMBER_GUESSES; i++) {
			rows[i] = new GuessRowFrame(renderable);
			renderable.addRenderable(rows[i].getRenderable());
		}
	}
	
	public boolean setRound(WordleRound round) {
		if (currentRound != null && !currentRound.hasEnded())
			return false;
		this.currentRound = round;
		return true;
	}

	public RenderableStack getRenderable() { return renderable; }
	public void setRenderable(RenderableStack renderable) { this.renderable = renderable; }
}
