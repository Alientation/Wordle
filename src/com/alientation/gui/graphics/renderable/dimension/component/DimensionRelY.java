package com.alientation.gui.graphics.renderable.dimension.component;


import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionRelY extends DimensionRetriever {
	public DimensionRelY(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.relY();
	}

}
