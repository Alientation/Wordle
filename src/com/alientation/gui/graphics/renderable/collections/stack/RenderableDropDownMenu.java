package com.alientation.gui.graphics.renderable.collections.stack;

import java.awt.Color;

import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.Sizing;
import com.alientation.gui.graphics.renderable.dimension.DimensionContainer;

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
}
