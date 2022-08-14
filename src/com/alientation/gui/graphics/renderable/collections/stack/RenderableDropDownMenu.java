package com.alientation.gui.graphics.renderable.collections.stack;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.RenderableComponent;
import com.alientation.gui.graphics.renderable.RenderableImage;
import com.alientation.gui.graphics.renderable.Sizing;
import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionComponent;

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

		}
	}
}
