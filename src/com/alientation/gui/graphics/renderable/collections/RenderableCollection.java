package com.alientation.gui.graphics.renderable.collections;

import java.awt.Graphics;
import java.util.ArrayList;

import com.alientation.gui.graphics.renderable.RenderableComponent;

public class RenderableCollection extends RenderableComponent {
	
	protected ArrayList<RenderableComponent> renderableElements;
	
	public RenderableCollection(Builder builder) {
		super(builder);
		this.renderableElements = builder.renderableElements;
	}

	public RenderableCollection addRenderable(RenderableComponent renderable) {
		this.renderableElements.add(renderable);
		return this;
	}
	
	public RenderableCollection removeRenderable(RenderableComponent renderable) {
		this.renderableElements.remove(renderable);
		return this;
	}
	
	public ArrayList<RenderableComponent> getRenderables() {
		return this.renderableElements;
	}
	
	public void render(Graphics g) {
		super.render(g);
	}


	public static class Builder extends RenderableComponent.Builder {
		protected ArrayList<RenderableComponent> renderableElements;

		public Builder() {
			this.renderableElements = new ArrayList<RenderableComponent>();
		}

		public Builder renderableElements(ArrayList<RenderableComponent> renderableElements) {
			this.renderableElements = renderableElements;
			return this;
		}

		public Builder addRenderable(RenderableComponent component) {
			this.renderableElements.add(component);
			return this;
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