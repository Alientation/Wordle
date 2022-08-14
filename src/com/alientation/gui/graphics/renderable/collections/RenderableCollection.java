package com.alientation.gui.graphics.renderable.collections;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.RenderableComponent;
import com.alientation.gui.graphics.renderable.RenderableImage;
import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionComponent;

public class RenderableCollection extends RenderableComponent {
	
	protected ArrayList<RenderableComponent> renderableElements;
	
	public RenderableCollection(Builder<?> builder) {
		super(builder);
		this.renderableElements = builder.renderableElements;
	}

	public RenderableCollection addRenderable(RenderableComponent renderable) {
		this.renderableElements.add(renderable);
		this.requireZIndexUpdate = true;
		return this;
	}
	
	public RenderableCollection removeRenderable(RenderableComponent renderable) {
		this.renderableElements.remove(renderable);
		this.requireZIndexUpdate = true;
		return this;
	}
	
	public ArrayList<RenderableComponent> getRenderables() { return this.renderableElements; }
	public void render(Graphics g) {
		super.render(g);
	}


	public static class Builder<T extends Builder<T>> extends RenderableComponent.Builder<T> {
		protected ArrayList<RenderableComponent> renderableElements;

		public Builder() {
			this.renderableElements = new ArrayList<>();
		}

		public T renderableElements(ArrayList<RenderableComponent> renderableElements) {
			this.renderableElements = renderableElements;
			return (T) this;
		}

		public T addRenderable(RenderableComponent component) {
			this.renderableElements.add(component);
			return (T) this;
		}

		public RenderableCollection build() throws IllegalStateException {
			validate();
			return new RenderableCollection(this);
		}

		public void validate() throws IllegalStateException {
			super.validate();
		}

	}
}