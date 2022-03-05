package com.alientation.wordle.graphics.renderable.collections;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.alientation.wordle.graphics.renderable.RenderableComponent;
import com.alientation.wordle.graphics.Window;
import com.alientation.wordle.graphics.renderable.Renderable;
import com.alientation.wordle.graphics.renderable.dimension.Dimension;
import com.alientation.wordle.graphics.renderable.dimension.DimensionContainer;

public class RenderableCollection extends RenderableComponent {
	
	protected ArrayList<RenderableComponent> renderableElements;
	
	public static class Builder extends RenderableComponent.Builder {
		private ArrayList<RenderableComponent> renderableElements;
		
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
	
	public RenderableCollection(Builder builder) {
		super(builder);
		this.renderableElements = builder.renderableElements;
	}
	
	public RenderableCollection(Renderable container, DimensionContainer dimensions, Color color) {
		super(container,dimensions,color);
		renderableElements = new ArrayList<>();
	}
	/*
	public RenderableCollection(Renderable container, DimensionContainer dimensions) {
		super(container,dimensions);
		renderableElements = new ArrayList<>();
	}
	
	public RenderableCollection(Renderable container, Color color) {
		super(container,color);
		renderableElements = new ArrayList<>();
	}
	
	public RenderableCollection(Renderable container) {
		super(container);
		renderableElements = new ArrayList<>();
	}
	*/
	
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
}