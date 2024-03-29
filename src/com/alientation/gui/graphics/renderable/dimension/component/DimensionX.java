package com.alientation.gui.graphics.renderable.dimension.component;

import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionX extends DimensionRetriever {
	public DimensionX(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.x();
	}
}
