package com.alientation.wordle.graphics.renderable.dimension.component;

import java.util.ArrayList;

import com.alientation.wordle.graphics.renderable.collections.stack.RenderableStack;
import com.alientation.wordle.graphics.renderable.dimension.Dimension;

public class DimensionSpacing extends DimensionComponent{
	private RenderableStack container;
	private ArrayList<Dimension> subreferences;
	
	public DimensionSpacing(RenderableStack container) {
		this.container = container;
		this.subreferences = new ArrayList<>();
		valueChanged();
	}
	
	public void setContainer(RenderableStack container) {
		this.container = container;
		valueChanged();
	}
	@Override
	public int val() {
		return container.spacing();
	}
	
	@Override
	public void valueChanged() {
		for (Dimension d : subreferences)
			d.valueChanged();
	}
	
	@Override
	public void registerReference(Dimension d) {
		this.subreferences.add(d);
	}

	@Override
	public void unregisterReference(Dimension d) {
		this.subreferences.remove(d);
	}
}
