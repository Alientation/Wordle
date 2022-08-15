package com.alientation.gui.graphics.renderable.collections.stack;

import java.awt.Graphics;

import com.alientation.gui.graphics.renderable.RenderableComponent;
import com.alientation.gui.graphics.renderable.dimension.RelativeDimension;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeHeight;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeWidth;

public class RenderableStackElement extends RenderableComponent {
	protected RenderableComponent child;
	
	public RenderableStackElement(Builder<?> builder) {
		super(builder);
		this.child = builder.child;
		joinChild();
	}
	
	public void joinChild() {
		this.requireZIndexUpdate = true;
		child.setContainer(this);
		child.setX(new StaticDimension(0));
		child.setY(new StaticDimension(0));
		child.setWidth(new RelativeDimension(new DimensionSafeWidth(this),1f));
		child.setHeight(new RelativeDimension(new DimensionSafeHeight(this),1f));
	}
	
	public RenderableComponent getChild() { return this.child; }
	public void setChild(RenderableComponent child) { this.child = child; }
	public boolean hasChild(RenderableComponent child) { return this.child == child; }
	
	public void render(Graphics g) {
		super.render(g);
		//child.render(g);
	}

	public static class Builder<T extends Builder<T>> extends RenderableComponent.Builder<T> {
		private RenderableComponent child;

		public Builder() {

		}

		public T child(RenderableComponent child) {
			this.child = child;
			return (T) this;
		}

		public RenderableStackElement build() throws IllegalStateException {
			validate();
			return new RenderableStackElement(this);
		}

		public void validate() throws IllegalStateException {
			super.validate();
		}
	}
}
