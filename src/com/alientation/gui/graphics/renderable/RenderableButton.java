package com.alientation.gui.graphics.renderable;


public class RenderableButton extends RenderableComponent {
	
	public RenderableButton(Builder<?> builder) {
		super(builder);
	}


	public static class Builder<T extends Builder<T>> extends RenderableComponent.Builder<T> {

		public Builder() {

		}

		public RenderableButton build() throws IllegalStateException {
			validate();
			return new RenderableButton(this);
		}

		public void validate() throws IllegalStateException {
			super.validate();
		}
	}
}