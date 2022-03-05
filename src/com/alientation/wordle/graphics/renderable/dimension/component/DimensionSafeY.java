package com.alientation.wordle.graphics.renderable.dimension.component;

import com.alientation.wordle.graphics.renderable.Renderable;

public class DimensionSafeY extends DimensionComponent{
	public DimensionSafeY(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.safeY();
	}
}
