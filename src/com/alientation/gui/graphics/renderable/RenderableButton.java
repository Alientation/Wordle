package com.alientation.gui.graphics.renderable;

public class RenderableButton extends RenderableComponent {
	
	public static class Builder {
		private RenderableComponent.Builder builder;
		
		public Builder builder(RenderableComponent.Builder builder) {
			this.builder = builder;
			return this;
		}
		
		public RenderableButton build() throws IllegalStateException {
			validate();
			return new RenderableButton(this);
		}
		
		public void validate() throws IllegalStateException {
			
		}
	}
	
	public RenderableButton(Builder builder) {
		super(builder.builder);
	}
	
}