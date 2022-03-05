package com.alientation.wordle.graphics.renderable.dimension;

import com.alientation.wordle.graphics.renderable.RenderableComponent;

public interface Dimension {
	public int val();
	public void valueChanged();
	public void register(RenderableComponent r);
	public void unregister(RenderableComponent r);
}
