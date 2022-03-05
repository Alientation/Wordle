package com.alientation.wordle.graphics.renderable.dimension.component;


import com.alientation.wordle.graphics.renderable.Renderable;

public class DimensionMarginY extends DimensionComponent {
	
	public DimensionMarginY(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.marginY();
	}
}