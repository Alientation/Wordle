package com.alientation.wordle.graphics.renderable.dimension.component;

import com.alientation.wordle.graphics.renderable.dimension.Dimension;

public abstract class DimensionComponent {
	public abstract int val();
	public abstract void valueChanged();
	public abstract void registerReference(Dimension d);
	public abstract void unregisterReference(Dimension d);
}
