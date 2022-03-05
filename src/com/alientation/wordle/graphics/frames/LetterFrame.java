package com.alientation.wordle.graphics.frames;

import java.awt.Color;

import com.alientation.wordle.graphics.renderable.Renderable;
import com.alientation.wordle.graphics.renderable.RenderableComponent;
import com.alientation.wordle.graphics.renderable.dimension.StaticDimension;

public class LetterFrame {
	
	private RenderableComponent renderable;
	
	public LetterFrame(Renderable container) {
		setRenderable(new RenderableComponent.Builder()
				.container(container)
				.marginX(new StaticDimension(5))
				.marginY(new StaticDimension(5))
				.color(Color.GRAY)
				.build());
	}

	public RenderableComponent getRenderable() {
		return renderable;
	}

	public void setRenderable(RenderableComponent renderable) {
		this.renderable = renderable;
	}
}
