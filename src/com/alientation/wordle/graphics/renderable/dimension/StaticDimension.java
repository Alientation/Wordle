package com.alientation.wordle.graphics.renderable.dimension;

import java.util.ArrayList;

import com.alientation.wordle.graphics.renderable.RenderableComponent;

public class StaticDimension implements Dimension{
	public static StaticDimension MIN = new StaticDimension(0);
	public static StaticDimension MAX = new StaticDimension(Integer.MAX_VALUE);
	public static StaticDimension BASE = new StaticDimension(200);
	
	private ArrayList<RenderableComponent> subreferences;
	
	private int val;
	public StaticDimension(int val) {
		this.val = val;
		subreferences = new ArrayList<>();
		valueChanged();
	}
	
	public void setVal(int val) {
		this.val = val;
	}
	
	public void valueChanged() {
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
		return this.val;
	}
}
