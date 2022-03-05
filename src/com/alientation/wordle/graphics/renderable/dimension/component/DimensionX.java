package com.alientation.wordle.graphics.renderable.dimension.component;

import com.alientation.wordle.graphics.renderable.Renderable;

public class DimensionX extends DimensionComponent {
	public DimensionX(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.x();
	}
}
