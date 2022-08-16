package com.alientation.gui.graphics.renderable.dimension.component;

import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionMarginX extends DimensionRetriever {
	
	public DimensionMarginX(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.marginX();
	}
}