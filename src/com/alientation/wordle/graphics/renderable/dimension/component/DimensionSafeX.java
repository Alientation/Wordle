package com.alientation.wordle.graphics.renderable.dimension.component;

import com.alientation.wordle.graphics.renderable.Renderable;

public class DimensionSafeX extends DimensionComponent{
	public DimensionSafeX(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.safeX();
	}
}
