package com.alientation.gui.graphics.renderable.dimension;

import java.util.HashSet;
import java.util.Set;

import com.alientation.gui.graphics.renderable.RenderableComponent;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionRetriever;

public class RelativeDimension implements Dimension {
	private Set<RenderableComponent> subreferences; //renderablecomponents that have this as a dimension
	
	private DimensionRetriever relTo; //which dimension is this relative to
	private float relVal;
	private int val;
	
	private Dimension min, max;
	
	public RelativeDimension(DimensionRetriever relTo, float relVal) {
		this(relTo,relVal,StaticDimension.MIN,StaticDimension.MAX);
	}
	
	public RelativeDimension(DimensionRetriever relTo, float relVal, Dimension min, Dimension max) {
		this.relTo = relTo;
		this.relVal = relVal;
		this.relTo.getContainer().addDimensionRelativeToThis(this);
		this.min = min;
		this.max = max;
		this.subreferences = new HashSet<>();
		valueChanged();
	}
	
	public void setMin(Dimension min) {
		this.min = min;
		valueChanged();
	}
	
	public void setMax(Dimension max) {
		this.max = max;
		valueChanged();
	}
	
	public void setRel(float relVal) {
		this.relVal = relVal;
		valueChanged();
	}
	public void setRelTo(DimensionRetriever relTo) {
		this.relTo.getContainer().removeDimensionRelativeToThis(this);
		this.relTo = relTo;
		this.relTo.getContainer().addDimensionRelativeToThis(this);
		valueChanged();
	}
	
	public DimensionRetriever getRelTo() {
		return this.relTo;
	}
	
	public void valueChanged() {
		val = Math.min(max.val(),Math.max(min.val(), convertFromRel(relTo.val(), relVal)));
		for (RenderableComponent r : subreferences)
			r.reqUpdate();
	}

	/**
	 *
	 * @param r	RenderableComponent that has this as a dimension
	 */
	public void register(RenderableComponent r) {
		this.subreferences.add(r);
	}
	
	public void unregister(RenderableComponent r) {
		this.subreferences.remove(r);
	}
	
	@Override
	public int val() {
		return Math.min(max.val(),Math.max(min.val(), convertFromRel(relTo.val(), relVal)));
		//return val;
	}

	public static int convertFromRel(int n, float rel) {
		return (int) (n * rel);
	}
}
