package com.alientation.gui.graphics.renderable.dimension.component;


import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionMarginY extends DimensionRetriever {
	
	public DimensionMarginY(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.marginY();
	}
}