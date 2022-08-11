package com.alientation.gui.graphics.renderable;


public class RenderableLabel extends RenderableComponent {
	
	protected String[] text;
	protected int lines;
	
	public static class Builder {
		private RenderableComponent.Builder builder;
		
		public Builder builder(RenderableComponent.Builder builder) {
			this.builder = builder;
			return this;
		}
		
		public RenderableLabel build() throws IllegalStateException {
			validate();
			return new RenderableLabel(this);
		}
		
		public void validate() throws IllegalStateException {
			
		}
		
	}
	
	public RenderableLabel(Builder builder) {
		super(builder.builder);
	}
	
	
	
	
	
	
}
