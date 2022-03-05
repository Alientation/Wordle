package com.alientation.wordle.graphics.renderable.dimension;

import java.util.ArrayList;

import com.alientation.wordle.graphics.renderable.RenderableComponent;
import com.alientation.wordle.graphics.renderable.dimension.component.DimensionComponent;
import com.alientation.wordle.util.Util;

public class RelativeDimension implements Dimension {
	private ArrayList<RenderableComponent> subreferences; //renderablecomponents that have this as a dimension
	
	private DimensionComponent relTo;
	private float relVal;
	private int val;
	
	private Dimension min, max;
	
	public RelativeDimension(DimensionComponent relTo, float relVal) {
		this(relTo,relVal,StaticDimension.MIN,StaticDimension.MAX);
	}
	
	public RelativeDimension(DimensionComponent relTo, float relVal, Dimension min,Dimension max) {
		this.relTo = relTo;
		this.relVal = relVal;
		this.min = min;
		this.max = max;
		this.subreferences = new ArrayList<>();
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
	public void setRelTo(DimensionComponent relTo) {
		this.relTo = relTo;
		valueChanged();
	}
	
	public DimensionComponent getRelTo() {
		return this.relTo;
	}
	
	public void valueChanged() {
		val = Math.min(max.val(),Math.max(min.val(), Util.convertFromRel(relTo.val(), relVal)));
		for (RenderableComponent r : subreferences)
			r.reqUpdate();
	}
	
	public void register(RenderableComponent r) {
		this.subreferences.add(r);
	}
	
	public void unregister(RenderableComponent r) {
		this.subreferences.remove(r);
	}
	
	@Override
	public int val() {
		return Math.min(max.val(),Math.max(min.val(), Util.convertFromRel(relTo.val(), relVal)));
		//return val;
	}
}
