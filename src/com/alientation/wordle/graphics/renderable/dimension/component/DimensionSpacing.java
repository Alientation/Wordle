package com.alientation.wordle.graphics.renderable.dimension.component;


import com.alientation.wordle.graphics.renderable.Renderable;
import com.alientation.wordle.graphics.renderable.collections.stack.RenderableStack;

public class DimensionSpacing extends DimensionComponent{
	public DimensionSpacing(RenderableStack container) {
		super(container);
	}
	
	@Override
	public void setContainer(Renderable container) {
		if (!(container instanceof RenderableStack))
			throw new IllegalStateException("DimensionSpacing : container is not an instance of RenderableStack");
		this.container = container;
	}
	
	@Override
	public int val() {
		return ((RenderableStack) container).spacing();
	}
}
