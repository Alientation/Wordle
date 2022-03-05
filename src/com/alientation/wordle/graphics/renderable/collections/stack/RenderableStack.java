package com.alientation.wordle.graphics.renderable.collections.stack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.alientation.wordle.graphics.renderable.RenderableComponent;
import com.alientation.wordle.graphics.Window;
import com.alientation.wordle.graphics.renderable.Renderable;
import com.alientation.wordle.graphics.renderable.Sizing;
import com.alientation.wordle.graphics.renderable.collections.RenderableCollection;
import com.alientation.wordle.graphics.renderable.dimension.DimensionID;
import com.alientation.wordle.graphics.renderable.dimension.Dimension;
import com.alientation.wordle.graphics.renderable.dimension.DimensionContainer;
import com.alientation.wordle.graphics.renderable.dimension.RelativeDimension;
import com.alientation.wordle.graphics.renderable.dimension.StaticDimension;
import com.alientation.wordle.graphics.renderable.dimension.component.DimensionSafeHeight;
import com.alientation.wordle.graphics.renderable.dimension.component.DimensionSafeWidth;

public class RenderableStack extends RenderableCollection {
	protected Dimension spacing;
	
	//TODO: Use canvas resize events instead
	private int prevSpacing, prevSafeHeight, prevSafeWidth;
	
	protected Sizing sizing;
	protected ArrayList<RenderableStackElement> stackSlots;
	
	public static class Builder extends RenderableCollection.Builder {
		private ArrayList<RenderableStackElement> stackSlots;
		private Sizing sizing;
		private Dimension spacing;
		
		
		public Builder() {
			this.stackSlots = new ArrayList<RenderableStackElement>();
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
			super.renderableElements(renderableElements);
			return this;
		}
		
		public Builder addRenderable(RenderableComponent component) {
			super.addRenderable(component);
			return this;
		}
		
		public Builder stackSlots(ArrayList<RenderableStackElement> stackSlots) {
			this.stackSlots = stackSlots;
			return this;
		}
		
		public Builder addSlot(RenderableStackElement slot) {
			this.stackSlots.add(slot);
			return this;
		}
		
		public Builder spacing(Dimension spacing) {
			this.spacing = spacing;
			return this;
		}
		
		public Builder sizing(Sizing sizing) {
			this.sizing = sizing;
			return this;
		}
		
		public RenderableStack build() throws IllegalStateException {
			validate();
			return new RenderableStack(this);
		}
		
		public void validate() throws IllegalStateException {
			super.validate();
		}
		
	}
	
	public RenderableStack(Builder builder) {
		super(builder);
		this.stackSlots = builder.stackSlots;
		this.spacing = builder.spacing;
		this.sizing = builder.sizing;
		this.registerDimensions();
	}
	
	public RenderableStack(Renderable container, DimensionContainer dimensions, Color color, Sizing sizing) {
		super(container,dimensions,color);
		this.spacing = dimensions.getDimension(DimensionID.SPACING);
		update();
		this.sizing = sizing;
		stackSlots = new ArrayList<>();
		this.registerDimensions();
	}
	
	public void registerDimensions() {
		registerDimensions(this.spacing);
	}
	
	public void update() {
		this.prevSpacing = spacing.val();
		this.prevSafeHeight = this.safeHeight();
		this.prevSafeWidth = this.safeWidth();
	}
	
	public void render(Graphics g) {
		if (prevSpacing != spacing.val() || prevSafeHeight != safeHeight() || prevSafeWidth != safeWidth()) {
			resizeElements();
			update();
		}
		super.render(g);
		for (int i = 0; i < stackSlots.size(); i++) {
			stackSlots.get(i).render(g);
		}
	}
	
	public RenderableStack addRenderable(RenderableComponent element) {
		RenderableStackElement stackElement = new RenderableStackElement(this,
				new DimensionContainer(
						new StaticDimension(0),
						new StaticDimension(0),
						new RelativeDimension(new DimensionSafeWidth(this),1f),
						new RelativeDimension(new DimensionSafeHeight(this),1f),
						new StaticDimension(0),
						new StaticDimension(0)
			),
			Color.WHITE,element);
		
		this.stackSlots.add(stackElement);
		resizeElements();
		return this;
	}
	
	public RenderableStack removeRenderable(RenderableComponent element) {
		for (int i = 0; i < stackSlots.size(); i++)
			if (stackSlots.get(i).hasChild(element)) {
				stackSlots.remove(i);
				break;
			}
		resizeElements();
		return this;
	}
	
	public void resizeElements() {
		if (this.sizing == null) return;
		this.sizing.resize(this);
	}
	
	public RenderableStack setSpacing(Dimension spacing) {
		this.spacing.unregister(this);
		spacing.register(this);
		if (this.spacing instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) this.spacing).getRelTo());
		this.spacing = spacing;
		if (this.spacing instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) this.spacing).getRelTo());
		return this;
	}
	
	public int spacing() {
		return this.spacing.val();
	}
	
	public Dimension getSpacing() {
		return this.spacing;
	}
	
	public Sizing getSizing() {
		return this.sizing;
	}
	
	public RenderableStack setSizing(Sizing sizing) {
		this.sizing = sizing;
		return this;
	}
	
	public ArrayList<RenderableStackElement> getStackSlots() {
		return this.stackSlots;
	}
	
	public boolean hasElement(RenderableComponent element) {
		for (int i = 0; i < stackSlots.size(); i++)
			if (stackSlots.get(i).hasChild(element))
				return true;
		return false;
	}
	
	public int size() {
		return this.stackSlots.size();
	}
}
