package com.alientation.wordle.graphics.renderable.dimension;

import com.alientation.wordle.graphics.renderable.RenderableComponent;

public abstract class Dimension {
	public abstract int val();
	public abstract void valueChanged();
	public abstract void register(RenderableComponent r);
	public abstract void unregister(RenderableComponent r);
}
