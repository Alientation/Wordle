package com.alientation.wordle.graphics.renderable.dimension.component;

import com.alientation.wordle.graphics.renderable.Renderable;

public class DimensionHeight extends DimensionComponent {
	
	public DimensionHeight(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.height();
	}
}