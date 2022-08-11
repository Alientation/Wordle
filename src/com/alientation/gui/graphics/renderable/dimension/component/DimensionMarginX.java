package com.alientation.gui.graphics.renderable.dimension.component;

import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionMarginX extends DimensionComponent {
	
	public DimensionMarginX(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.marginX();
	}
}