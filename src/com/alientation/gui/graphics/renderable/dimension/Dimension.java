package com.alientation.gui.graphics.renderable.dimension;

import com.alientation.gui.graphics.renderable.RenderableComponent;

public interface Dimension {
	public int val();
	public void valueChanged();
	public void register(RenderableComponent r);
	public void unregister(RenderableComponent r);
}
