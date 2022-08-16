package com.alientation.gui.graphics.renderable.dimension.component;


import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionY extends DimensionRetriever {
	public DimensionY(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.y();
	}
}
