package com.alientation.wordle.graphics.frames;

import java.awt.Color;

import com.alientation.wordle.graphics.renderable.RenderableComponent;
import com.alientation.wordle.graphics.renderable.Renderable;
import com.alientation.wordle.graphics.renderable.dimension.StaticDimension;

public class DataFrame {
	
	private RenderableComponent renderable;
	
	public DataFrame(Renderable container) {
		setRenderable(new RenderableComponent.Builder()
				.container(container)
				.color(Color.DARK_GRAY)
				.marginX(new StaticDimension(5))
				.marginY(new StaticDimension(5))
				.build());
	}

	public RenderableComponent getRenderable() {
		return renderable;
	}

	public void setRenderable(RenderableComponent renderable) {
		this.renderable = renderable;
	}
}
