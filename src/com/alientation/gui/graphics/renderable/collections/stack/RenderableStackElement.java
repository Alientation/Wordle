package com.alientation.gui.graphics.renderable.collections.stack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.alientation.gui.graphics.renderable.RenderableComponent;
import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.DimensionContainer;
import com.alientation.gui.graphics.renderable.dimension.RelativeDimension;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeHeight;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeWidth;

public class RenderableStackElement extends RenderableComponent {
	protected RenderableComponent child;
	
	public static class Builder extends RenderableComponent.Builder {
		private RenderableComponent child;
		
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
		
		public Builder subreferences(ArrayList<RenderableComponent> renderable) {
			super.subreferences(renderable);
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
		
		public Builder color(Color color) {
			super.color(color);
			return this;
		}
		
		public Builder visible(boolean visible) {
			super.visible(visible);
			return this;
		}
		
		public Builder child(RenderableComponent child) {
			this.child = child;
			return this;
		}
		
		public RenderableStackElement build() throws IllegalStateException {
			validate();
			return new RenderableStackElement(this);
		}
		
		public void validate() throws IllegalStateException {
			super.validate();
		}
	}
	
	public RenderableStackElement(Builder builder) {
		super(builder);
		this.child = builder.child;
		joinChild();
	}
	
	public void joinChild() {
		child.setContainer(this);
		child.setX(new StaticDimension(0));
		child.setY(new StaticDimension(0));
		child.setWidth(new RelativeDimension(new DimensionSafeWidth(this),1f));
		child.setHeight(new RelativeDimension(new DimensionSafeHeight(this),1f));
	}
	
	public RenderableComponent getChild() {
		return this.child;
	}
	
	public void setChild(RenderableComponent child) {
		this.child = child;
	}
	
	public boolean hasChild(RenderableComponent child) {
		return this.child == child;
	}
	
	public void render(Graphics g) {
		super.render(g);
		child.render(g);
	}
}
