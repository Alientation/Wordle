package com.alientation.wordle.graphics.renderable.dimension.component;

import java.util.ArrayList;

import com.alientation.wordle.graphics.renderable.Renderable;
import com.alientation.wordle.graphics.renderable.dimension.Dimension;

public class DimensionComponent {
	protected Renderable container;
	protected ArrayList<Dimension> subreferences;
	
	public DimensionComponent(Renderable container) {
		this.container = container;
		this.subreferences = new ArrayList<>();
		valueChanged();
	}
	
	public int val() {
		return -1;
	}
	public void valueChanged() {
		for (Dimension d : subreferences)
			d.valueChanged();
	}
	
	public void setContainer(Renderable container) {
		this.container = container;
		valueChanged();
	}
	
	public Renderable getContainer() {
		return this.container;
	}
	
	public void registerReference(Dimension d) {
		this.subreferences.add(d);
	}
	public void unregisterReference(Dimension d) {
		this.subreferences.remove(d);
	}
}
