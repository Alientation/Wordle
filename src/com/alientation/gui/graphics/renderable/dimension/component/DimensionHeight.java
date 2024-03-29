package com.alientation.gui.graphics.renderable.dimension.component;

import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionHeight extends DimensionRetriever {
	
	public DimensionHeight(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.height();
	}
}