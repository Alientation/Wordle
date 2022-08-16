package com.alientation.gui.graphics.renderable.collections.stack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.alientation.gui.graphics.renderable.RenderableComponent;
import com.alientation.gui.graphics.renderable.Sizing;
import com.alientation.gui.graphics.renderable.collections.RenderableCollection;
import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.RelativeDimension;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeHeight;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeWidth;

public class RenderableStack extends RenderableCollection {
	protected Dimension spacing;
	
	//TODO: Use canvas resize events instead
	private int prevSpacing, prevSafeHeight, prevSafeWidth, previousSize;
	
	protected Sizing sizing;
	protected ArrayList<RenderableStackElement> stackSlots;
	
	public RenderableStack(Builder<?> builder) {
		super(builder);
		this.stackSlots = builder.stackSlots;
		this.spacing = builder.spacing;
		this.sizing = builder.sizing;
		this.registerDimensions();
		this.resizeElements();
	}
	
	public void registerDimensions() {
		registerDimensions(this.spacing);
	}
	
	public void update() {
		this.prevSpacing = spacing.val();
		this.prevSafeHeight = this.safeHeight();
		this.prevSafeWidth = this.safeWidth();
		this.previousSize = stackSlots.size();
	}

	public void tick() {
		super.tick();
		if (prevSpacing != spacing.val() || prevSafeHeight != safeHeight() || prevSafeWidth != safeWidth() ||
				previousSize != stackSlots.size()) {
			resizeElements();
			update();
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
	
	public RenderableStack addRenderable(RenderableComponent element) {
		RenderableStackElement stackElement = new RenderableStackElement.Builder<>()
				.container(this)
				.x(new StaticDimension(0))
				.y(new StaticDimension(0))
				.width(new RelativeDimension(new DimensionSafeWidth(this),1f))
				.height(new RelativeDimension(new DimensionSafeHeight(this),1f))
				.marginX(new StaticDimension(0))
				.marginY(new StaticDimension(0))
				.backgroundColor(Color.WHITE)
				.child(element)
				.id("Stack-element")
				.build();
		
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
		this.requireRenderUpdate = true;
		this.requireDimensionUpdate = true;
	}
	
	public RenderableStack setSpacing(Dimension spacing) {
		this.spacing.unregister(this);
		spacing.register(this);
		this.spacing = spacing;
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		return this;
	}
	
	public int spacing() { return this.spacing.val(); }
	public Dimension getSpacing() { return this.spacing; }
	public Sizing getSizing() { return this.sizing; }
	
	public RenderableStack setSizing(Sizing sizing) {
		this.sizing = sizing;
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		return this;
	}
	
	public ArrayList<RenderableStackElement> getStackSlots() { return this.stackSlots; }
	public boolean hasElement(RenderableComponent element) {
		for (RenderableStackElement stackSlot : stackSlots)
			if (stackSlot.hasChild(element))
				return true;
		return false;
	}
	public int size() { return this.stackSlots.size(); }

	public static class Builder<T extends Builder<T>> extends RenderableCollection.Builder<T> {
		protected ArrayList<RenderableStackElement> stackSlots;

		protected Sizing sizing;
		protected Dimension spacing;

		public Builder() {
			this.stackSlots = new ArrayList<>();
		}

		public T stackSlots(ArrayList<RenderableStackElement> stackSlots) {
			this.stackSlots = stackSlots;
			return (T) this;
		}

		public T addSlot(RenderableStackElement slot) {
			this.stackSlots.add(slot);
			return (T) this;
		}

		public T spacing(Dimension spacing) {
			this.spacing = spacing;
			return (T) this;
		}

		public T sizing(Sizing sizing) {
			this.sizing = sizing;
			return (T) this;
		}

		public RenderableStack build() throws IllegalStateException {
			validate();
			return new RenderableStack(this);
		}

		public void validate() throws IllegalStateException {
			super.validate();
		}

	}
}
