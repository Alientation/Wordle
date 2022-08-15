package com.alientation.gui.graphics.renderable;

public class RenderableLabel extends RenderableComponent {
	
	protected String[] text;
	protected int lines;
	
	public RenderableLabel(Builder<?> builder) {
		super(builder);
	}


	public static class Builder<T extends  Builder<T>> extends RenderableComponent.Builder<T> {

		public Builder() {

		}

		public RenderableLabel build() throws IllegalStateException {
			validate();
			return new RenderableLabel(this);
		}

		public void validate() throws IllegalStateException {
			super.validate();
		}

	}
}
