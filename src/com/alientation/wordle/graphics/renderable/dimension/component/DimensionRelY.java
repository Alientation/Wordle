package com.alientation.wordle.graphics.renderable.dimension.component;


import com.alientation.wordle.graphics.renderable.Renderable;

public class DimensionRelY extends DimensionComponent {
	public DimensionRelY(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.relY();
	}

}
