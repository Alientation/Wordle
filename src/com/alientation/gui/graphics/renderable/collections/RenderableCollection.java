package com.alientation.gui.graphics.renderable.collections;

import com.alientation.gui.graphics.renderable.RenderableComponent;

import java.awt.*;
import java.util.ArrayList;

public class RenderableCollection extends RenderableComponent {
	
	protected ArrayList<RenderableComponent> renderableElements;
	
	public RenderableCollection(Builder<?> builder) {
		super(builder);
		this.renderableElements = builder.renderableElements;
	}

	public RenderableCollection addRenderable(RenderableComponent renderable) {
		this.renderableElements.add(renderable);
		this.addSubreference(renderable);
		this.requireZIndexUpdate = true;
		this.requireDimensionUpdate = true;
		return this;
	}
	
	public RenderableCollection removeRenderable(RenderableComponent renderable) {
		this.renderableElements.remove(renderable);
		this.removeSubreference(renderable);
		this.requireZIndexUpdate = true;
		this.requireDimensionUpdate = true;
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