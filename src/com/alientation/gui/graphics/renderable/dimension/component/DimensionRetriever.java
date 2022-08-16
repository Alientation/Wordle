package com.alientation.gui.graphics.renderable.dimension.component;

import java.util.ArrayList;

import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.dimension.Dimension;

public class DimensionRetriever {
	protected Renderable container;
	protected ArrayList<Dimension> subreferences; //propagate changes to avoid constant checking - might still wanna do a refresh update every half or so seconds
	
	public DimensionRetriever(Renderable container) {
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
