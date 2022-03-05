package com.alientation.wordle.graphics.renderable.dimension.component;


import com.alientation.wordle.graphics.renderable.Renderable;

public class DimensionRelX extends DimensionComponent {
	
	public DimensionRelX(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.relX();
	}
}
