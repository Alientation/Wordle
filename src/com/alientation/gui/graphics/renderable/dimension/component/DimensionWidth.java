package com.alientation.gui.graphics.renderable.dimension.component;


import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionWidth extends DimensionRetriever {
	public DimensionWidth(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.width();
	}
}