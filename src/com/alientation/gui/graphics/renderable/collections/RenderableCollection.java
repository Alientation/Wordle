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
	
	public RenderableCollection(Builder builder) {
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


	public static class Builder extends RenderableComponent.Builder {
		protected ArrayList<RenderableComponent> renderableElements;

		public Builder() {
			this.renderableElements = new ArrayList<RenderableComponent>();
		}

		public Builder window(Window window) {
			super.window(window);
			return this;
		}

		public Builder subreference(RenderableComponent renderable) {
			super.subreference(renderable);
			return this;
		}

		public Builder subreferences(Collection<RenderableComponent> renderables) {
			super.subreferences(renderables);
			return this;
		}

		public Builder render(BufferedImage render) {
			super.render(render);
			return this;
		}

		public Builder id(String id) {
			super.id(id);
			return this;
		}

		public Builder dimensionReference(DimensionComponent dc) {
			this.dimensionReferences.add(dc);
			return this;
		}

		public Builder dimensionReferences(Collection<DimensionComponent> dimensions) {
			this.dimensionReferences.addAll(dimensions);
			return this;
		}

		public Builder container(Renderable container) {
			this.container = container;
			this.window = container.getWindow();
			return this;
		}

		public Builder x(Dimension x) {
			this.x = x;
			return this;
		}

		public Builder y(Dimension y) {
			this.y = y;
			return this;
		}

		public Builder width(Dimension width) {
			this.width = width;
			return this;
		}

		public Builder height(Dimension height) {
			this.height = height;
			return this;
		}

		public Builder marginX(Dimension marginX) {
			this.marginX = marginX;
			return this;
		}

		public Builder marginY(Dimension marginY) {
			this.marginY = marginY;
			return this;
		}

		public Builder radius(Dimension radius) {
			this.radius = radius;
			return this;
		}

		public Builder thickness(Dimension thickness) {
			this.thickness = thickness;
			return this;
		}

		public Builder backgroundTransparency(float backgroundTransparency) {
			this.backgroundTransparency = backgroundTransparency;
			return this;
		}

		public Builder frameTransparency(float frameTransparency) {
			this.frameTransparency = frameTransparency;
			return this;
		}

		public Builder backgroundImage(RenderableImage backgroundImage) {
			this.backgroundImage = backgroundImage;
			return this;
		}

		public Builder backgroundColor(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
			return this;
		}

		public Builder frameColor(Color frameColor) {
			this.frameColor = frameColor;
			return this;
		}

		public Builder visible(boolean visible) {
			this.visible = visible;
			return this;
		}

		public Builder zIndex(int zIndex) {
			this.zIndex = zIndex;
			return this;
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