package com.alientation.gui.graphics.renderable.dimension.component;

import com.alientation.gui.graphics.renderable.Renderable;

public class DimensionSafeWidth extends DimensionComponent{
	public DimensionSafeWidth(Renderable container) {
		super(container);
	}
	
	@Override
	public int val() {
		return container.safeWidth();
	}
}
