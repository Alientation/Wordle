package com.alientation.wordle.frames;

import java.awt.Color;

import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.RenderableComponent;

public class SettingFrame{
	private RenderableComponent renderable;
	public SettingFrame(Renderable container) {
		setRenderable(new RenderableComponent.Builder()
				.container(container)
				.color(Color.LIGHT_GRAY)
				.build());
	}

	public RenderableComponent getRenderable() { return renderable; }
	public void setRenderable(RenderableComponent renderable) { this.renderable = renderable; }
}
