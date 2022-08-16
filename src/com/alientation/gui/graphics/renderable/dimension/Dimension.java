package com.alientation.gui.graphics.renderable.dimension;

import com.alientation.gui.graphics.renderable.RenderableComponent;

public interface Dimension {
	int val();
	void valueChanged();
	void register(RenderableComponent r);
	void unregister(RenderableComponent r);
}
