package com.alientation.wordle.graphics.renderable.dimension;

public class DimensionContainer {
	
	private Dimension[] dimensions;
	
	public static class Builder {
		private Dimension[] dimensions;
		
		public Builder() {
			dimensions = new Dimension[DimensionID.UNIQUE.id()];
		}
		
		public Builder x(Dimension x) {
			this.dimensions[DimensionID.X.id()] = x;
			return this;
		}
		
		public Builder y(Dimension y) {
			this.dimensions[DimensionID.Y.id()] = y;
			return this;
		}
		
		public Builder width(Dimension width) {
			this.dimensions[DimensionID.WIDTH.id()] = width;
			return this;
		}
		
		public Builder height(Dimension HEIGHT) {
			this.dimensions[DimensionID.HEIGHT.id()] = HEIGHT;
			return this;
		}
		
		public Builder marginX(Dimension marginX) {
			this.dimensions[DimensionID.MARGINX.id()] = marginX;
			return this;
		}
		
		public Builder marginY(Dimension marginY) {
			this.dimensions[DimensionID.MARGINY.id()] = marginY;
			return this;
		}
		
		public DimensionContainer build() throws IllegalStateException {
			validate();
			return new DimensionContainer(this);
		}
		
		public void validate() throws IllegalStateException {
			
		}
	}
	
	public DimensionContainer(Builder builder) {
		this.dimensions = builder.dimensions;
	}
	
	public DimensionContainer(Dimension...dimensions) {
		this.dimensions = dimensions;
	}
	
	public Dimension getDimension(DimensionID id) {
		if (id.id() >= dimensions.length)
			return StaticDimension.BASE;
		return dimensions[id.id()];
	}
}
