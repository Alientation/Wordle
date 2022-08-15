package com.alientation.wordle.frames;

import java.awt.Color;

import com.alientation.gui.graphics.renderable.RenderableComponent;
import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;

public class DataFrame {
	private RenderableComponent renderable;
	public DataFrame(Renderable container) {
		setRenderable(new RenderableComponent.Builder<>()
				.container(container)
				.backgroundColor(Color.DARK_GRAY)
				.marginX(new StaticDimension(5))
				.marginY(new StaticDimension(5))
				.id("DATA-FRAME")
				.build());
	}

	public RenderableComponent getRenderable() { return renderable; }
	public void setRenderable(RenderableComponent renderable) { this.renderable = renderable; }
}
