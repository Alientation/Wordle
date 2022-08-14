package com.alientation.gui.graphics.renderable;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionComponent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class RenderableButton extends RenderableComponent {
	
	public RenderableButton(Builder builder) {
		super(builder);
	}


	public static class Builder extends RenderableComponent.Builder {

		public Builder() {

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
			super.dimensionReference(dc);
			return this;
		}

		public Builder dimensionReferences(Collection<DimensionComponent> dimensions) {
			super.dimensionReferences(dimensions);
			return this;
		}

		public Builder container(Renderable container) {
			super.container(container);
			return this;
		}

		public Builder x(Dimension x) {
			super.x(x);
			return this;
		}

		public Builder y(Dimension y) {
			super.y(y);
			return this;
		}

		public Builder width(Dimension width) {
			super.width(width);
			return this;
		}

		public Builder height(Dimension height) {
			super.height(height);
			return this;
		}

		public Builder marginX(Dimension marginX) {
			super.marginX(marginX);
			return this;
		}

		public Builder marginY(Dimension marginY) {
			super.marginY(marginY);
			return this;
		}

		public Builder radius(Dimension radius) {
			super.radius(radius);
			return this;
		}

		public Builder thickness(Dimension thickness) {
			super.thickness(thickness);
			return this;
		}

		public Builder backgroundTransparency(float backgroundTransparency) {
			super.backgroundTransparency(backgroundTransparency);
			return this;
		}

		public Builder frameTransparency(float frameTransparency) {
			super.frameTransparency(frameTransparency);
			return this;
		}

		public Builder backgroundImage(RenderableImage backgroundImage) {
			super.backgroundImage(backgroundImage);
			return this;
		}

		public Builder backgroundColor(Color backgroundColor) {
			super.backgroundColor(backgroundColor);
			return this;
		}

		public Builder frameColor(Color frameColor) {
			super.frameColor(frameColor);
			return this;
		}

		public Builder visible(boolean visible) {
			super.visible(visible);
			return this;
		}

		public Builder zIndex(int zIndex) {
			super.zIndex(zIndex);
			return this;
		}

		public RenderableButton build() throws IllegalStateException {
			validate();
			return new RenderableButton(this);
		}

		public void validate() throws IllegalStateException {

		}
	}
}