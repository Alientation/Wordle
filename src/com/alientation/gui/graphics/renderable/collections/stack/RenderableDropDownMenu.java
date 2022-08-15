package com.alientation.gui.graphics.renderable.collections.stack;


public class RenderableDropDownMenu extends RenderableStack {

	
	public RenderableDropDownMenu(Builder<?> builder) {
		super(builder);
	}

	public static class Builder<T extends Builder<T>> extends RenderableStack.Builder<T> {

		public Builder() {

		}

		public RenderableDropDownMenu build() throws IllegalStateException {
			validate();
			return new RenderableDropDownMenu(this);
		}

		public void validate() throws IllegalStateException {
			super.validate();
		}
	}
}
