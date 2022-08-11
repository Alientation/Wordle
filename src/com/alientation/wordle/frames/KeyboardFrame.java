package com.alientation.wordle.frames;

import java.awt.Color;

import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.Sizing;
import com.alientation.gui.graphics.renderable.collections.stack.RenderableStack;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;

public class KeyboardFrame {
	private RenderableStack renderable;
	public KeyboardFrame(Renderable container) {
		setRenderable(new RenderableStack.Builder()
				.container(container)
				.marginX(new StaticDimension(5))
				.marginY(new StaticDimension(5))
				.spacing(new StaticDimension(5))
				.color(Color.MAGENTA)
				.sizing(Sizing.VERTICAL_EQUAL)
				.build());
	}

	public RenderableStack getRenderable() { return renderable; }
	public void setRenderable(RenderableStack renderable) { this.renderable = renderable; }
}
