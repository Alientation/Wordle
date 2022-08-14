package com.alientation.gui.graphics.renderable;


import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionComponent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

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

		}

	}
}
