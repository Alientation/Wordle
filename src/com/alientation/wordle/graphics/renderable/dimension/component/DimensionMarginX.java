package com.alientation.wordle.graphics.renderable.dimension.component;

import java.util.ArrayList;

import com.alientation.wordle.graphics.renderable.Renderable;
import com.alientation.wordle.graphics.renderable.dimension.Dimension;

public class DimensionMarginX extends DimensionComponent {
	private Renderable container;
	private ArrayList<Dimension> subreferences;
	
	public DimensionMarginX(Renderable container) {
		this.container = container;
		this.subreferences = new ArrayList<>();
		valueChanged();
	}
	
	public void setContainer(Renderable container) {
		this.container = container;
		valueChanged();
	}
	@Override
	public int val() {
		return container.marginX();
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