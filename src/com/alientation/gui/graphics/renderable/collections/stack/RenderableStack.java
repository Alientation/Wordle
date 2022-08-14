package com.alientation.gui.graphics.renderable.collections.stack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import com.alientation.gui.graphics.renderable.RenderableComponent;
import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.RenderableImage;
import com.alientation.gui.graphics.renderable.Sizing;
import com.alientation.gui.graphics.renderable.collections.RenderableCollection;
import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.RelativeDimension;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionComponent;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeHeight;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeWidth;

public class RenderableStack extends RenderableCollection {
	protected Dimension spacing;
	
	//TODO: Use canvas resize events instead
	private int prevSpacing, prevSafeHeight, prevSafeWidth;
	
	protected Sizing sizing;
	protected ArrayList<RenderableStackElement> stackSlots;
	
	public RenderableStack(Builder builder) {
		super(builder);
		this.stackSlots = builder.stackSlots;
		this.spacing = builder.spacing;
		this.sizing = builder.sizing;
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
		RenderableStackElement stackElement = new RenderableStackElement.Builder()
				.container(this)
				.x(new StaticDimension(0))
				.y(new StaticDimension(0))
				.width(new RelativeDimension(new DimensionSafeWidth(this),1f))
				.height(new RelativeDimension(new DimensionSafeHeight(this),1f))
				.marginX(new StaticDimension(0))
				.marginY(new StaticDimension(0))
				.backgroundColor(Color.WHITE)
				.child(element)
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

	public static class Builder extends RenderableCollection.Builder {
		protected ArrayList<RenderableStackElement> stackSlots;

		protected Sizing sizing;
		protected Dimension spacing;

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

		public Builder subreferences(Collection<RenderableComponent> renderables) {
			super.subreferences(renderables);
			return this;
		}

		public  Builder render(BufferedImage render) {
			super.render(render);
			return this;
		}

		public  Builder id(String id) {
			super.id(id);
			return this;
		}

		public  Builder dimensionReference(DimensionComponent dc) {
			super.dimensionReference(dc);
			return this;
		}

		public  Builder dimensionReferences(Collection<DimensionComponent> dimensions) {
			super.dimensionReferences(dimensions);
			return this;
		}

		public  Builder container(Renderable container) {
			super.container(container);
			return this;
		}

		public  Builder x(Dimension x) {
			super.x(x);
			return this;
		}

		public  Builder y(Dimension y) {
			super.y(y);
			return this;
		}

		public  Builder width(Dimension width) {
			super.width(width);
			return this;
		}

		public  Builder height(Dimension height) {
			super.height(height);
			return this;
		}

		public  Builder marginX(Dimension marginX) {
			super.marginX(marginX);
			return this;
		}

		public  Builder marginY(Dimension marginY) {
			super.marginY(marginY);
			return this;
		}

		public  Builder radius(Dimension radius) {
			super.radius(radius);
			return this;
		}

		public  Builder thickness(Dimension thickness) {
			super.thickness(thickness);
			return this;
		}

		public  Builder backgroundTransparency(float backgroundTransparency) {
			super.backgroundTransparency(backgroundTransparency);
			return this;
		}

		public  Builder frameTransparency(float frameTransparency) {
			super.frameTransparency(frameTransparency);
			return this;
		}

		public  Builder backgroundImage(RenderableImage backgroundImage) {
			super.backgroundImage(backgroundImage);
			return this;
		}

		public  Builder backgroundColor(Color backgroundColor) {
			super.backgroundColor(backgroundColor);
			return this;
		}

		public  Builder frameColor(Color frameColor) {
			super.frameColor(frameColor);
			return this;
		}

		public  Builder visible(boolean visible) {
			super.visible(visible);
			return this;
		}

		public  Builder zIndex(int zIndex) {
			super.zIndex(zIndex);
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
}
