package com.alientation.wordle.graphics.renderable.dimension;

public enum DimensionID {
	X(0),Y(1),WIDTH(2),HEIGHT(3),MARGINX(4),MARGINY(5),SPACING(6),UNIQUE(7);
	private int id;
	private DimensionID(int id) {
		this.id = id;
	}
	
	public int id() {
		return this.id;
	}
}
