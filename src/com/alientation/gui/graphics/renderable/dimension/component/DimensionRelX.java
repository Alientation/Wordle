package com.alientation.gui.graphics.renderable.dimension.component;


import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionRelX extends DimensionRetriever {
	
	public DimensionRelX(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.relX();
	}
}
