package com.alientation.gui.graphics.renderable.dimension.component;

import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionSafeY extends DimensionRetriever {
	public DimensionSafeY(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.safeY();
	}
}
