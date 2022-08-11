package com.alientation.gui.graphics.renderable.dimension.component;


import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionSafeHeight extends DimensionComponent {
	public DimensionSafeHeight(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.safeHeight();
	}
}
