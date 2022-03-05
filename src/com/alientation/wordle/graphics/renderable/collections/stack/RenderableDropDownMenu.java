package com.alientation.wordle.graphics.renderable.collections.stack;

import java.awt.Color;

import com.alientation.wordle.graphics.renderable.Renderable;
import com.alientation.wordle.graphics.renderable.Sizing;
import com.alientation.wordle.graphics.renderable.dimension.DimensionContainer;

public class RenderableDropDownMenu extends RenderableStack {
	
	
	public static class Builder {
		private RenderableStack.Builder builder;
		
		public Builder builder(RenderableStack.Builder builder) {
			this.builder = builder;
			return this;
		}
		
		public RenderableDropDownMenu build() throws IllegalStateException {
			validate();
			return new RenderableDropDownMenu(this);
		}
		
		public void validate() throws IllegalStateException {
			
		}
	}
	
	public RenderableDropDownMenu(Builder builder) {
		super(builder.builder);
	}
	
	public RenderableDropDownMenu(Renderable container, DimensionContainer dimensions, Color color, Sizing sizing) {
		super(container, dimensions, color, sizing);
	}
	/*
	public RenderableDropDownMenu(Renderable container, Dimension spacing, Color color, Sizing sizing) {
		super(container, spacing, color, sizing);
	}*/

}
