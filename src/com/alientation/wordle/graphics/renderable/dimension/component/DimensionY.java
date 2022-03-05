package com.alientation.wordle.graphics.renderable.dimension.component;


import com.alientation.wordle.graphics.renderable.Renderable;

public class DimensionY extends DimensionComponent {
	public DimensionY(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.y();
	}
}
