package com.alientation.gui.graphics.renderable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.EventDispatcher;
import com.alientation.gui.graphics.renderable.dimension.*;
import com.alientation.gui.graphics.renderable.dimension.component.*;

/**
 * Background
 *  * - image
 *  * - color, transparency
 *  * Frame
 *  * - radius (edges radius)
 *  * - thickness
 *  * - color, transparency (edge)
 *
 */
public class RenderableComponent extends Renderable {

	//The default renderable of the first Window created
	public static RenderableComponent BASE = new RenderableComponent();

	//The container this renderable is contained in. Default is the renderable base
	protected Renderable container;
	
	//Dimension objects, used so there can be relative dimensions to another component's dimension
	protected Dimension marginX,marginY;  //relative position to the container

	
	//Whether the component should be rendered
	protected boolean visible;

	//dimensionComponents who accesses this renderable
	protected Set<DimensionComponent> dimensionReferences;

	protected int zIndex;
	protected RenderableBackground background;
	protected RenderableFrame frame;

	protected EventDispatcher eventDispatcher;

	/**
	 * TODO: fix builder class in other renderable classes
	 */

	
	public RenderableComponent(Builder builder) {
		super(builder);
		this.container = builder.container;
		this.marginX = builder.marginX;
		this.marginY = builder.marginY;
		this.visible = builder.visible;
		this.container.addSubreference(this);
		this.dimensionReferences = builder.dimensionReferences;

		this.background = new RenderableBackground(this, builder.backgroundImage, builder.backgroundColor, builder.backgroundTransparency);
		this.frame = new RenderableFrame(this, builder.x, builder.y, builder.width, builder.height, builder.radius, builder.thickness, builder.frameColor, builder.frameTransparency);
		this.eventDispatcher = new EventDispatcher(this);
		registerDimensions();
	}
	
	private RenderableComponent() {
		this(new RenderableComponent.Builder()
				.container(Window.INIT_WINDOW.getRenderable())
				.x(new StaticDimension(0))
				.y(new StaticDimension(0))
				.width(new RelativeDimension(new DimensionSafeWidth(Window.INIT_WINDOW.getRenderable()),1.0f))
				.height(new RelativeDimension(new DimensionSafeHeight(Window.INIT_WINDOW.getRenderable()),1.0f))
				.marginX(new StaticDimension(0))
				.marginY(new StaticDimension(0))
				.backgroundColor(Color.WHITE)
		);
	}
	
	public void registerDimensions() {
		registerDimensions(marginX,marginY, frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight(), frame.getRadius(), frame.getThickness());
	}
	
	protected void registerDimensions(Dimension... ds) {
		for (Dimension d : ds) {
			if (d == null) continue;
			if (d instanceof RelativeDimension)
				dimensionReferences.add(((RelativeDimension) d).getRelTo());
			d.register(this);
		}
	}
	
	public void resized() {
		super.resized();
		for (DimensionComponent d : this.dimensionReferences)
			d.valueChanged();
	}

	public void render(Graphics g) {
		//if (this.visible)
			//g.drawImage(render(),x(), y(), null);


		g.setColor(background.getColor());
		g.fillRect(x(), y(), width(), height());
		super.render(g);
	}

	/**
	 * TODO
	 */
	public void updateRender() {
		if (!reqUpdate)
			return;
		reqUpdate = false;
		render = new BufferedImage(width(),height(),BufferedImage.TYPE_INT_ARGB);
		Graphics temp = render.createGraphics();
		temp.setColor(background.getColor());
		temp.fillRect(0, 0, width(), height());
		
		for (RenderableComponent r : subreferences)
			temp.drawImage(r.render(), r.x(), r.y(), null);
	}

	public void addDimensionReference(DimensionComponent d) { this.dimensionReferences.add(d); }
	public void removeDimensionReference(DimensionComponent d) { this.dimensionReferences.remove(d); }
	
	@Override
	public String toString() {
		return container.id() + " -> " + id + ":{" + "(x,y): (" + x() + "," + y() + ") - (w,h): (" + width() + "," + height() + ") - (mX,mY): (" + marginX() + "," + marginY() + ")}";
	}
	
	public RenderableComponent setVisibility(boolean visible) {
		this.visible = visible;
		window.getRenderable().reqUpdate = true;
		return this;
	}
	
	public boolean getVisibility() { return this.visible; }
	public Rectangle getSafeArea() { return new Rectangle(safeX(),safeY(),safeWidth(),safeHeight()); }
	public Rectangle getArea() { return new Rectangle(x(),y(),width(),height()); }

	public RenderableComponent setContainer(RenderableComponent container) {
		this.container = container;
		this.reqUpdate = true;
		return this;
	}
	
	public Renderable getContainer() { return this.container; }

	public RenderableComponent setBackgroundColor(Color color) {
		this.background.setColor(color);
		this.reqUpdate = true;
		return this;
	}

	public RenderableComponent setFrameColor(Color color) {
		this.frame.setColor(color);
		this.reqUpdate = true;
		return this;
	}

	public RenderableComponent setBackgroundTransparency(float transparency) {
		background.setTransparency(transparency);
		this.reqUpdate = true;
		return this;
	}

	public RenderableComponent setFrameTransparency(float transparency) {
		frame.setTransparency(transparency);
		this.reqUpdate = true;
		return this;
	}
	
	public Color getBackgroundColor() { return background.getColor(); }
	public Color getFrameColor() { return frame.getColor(); }
	public float getBackgroundTransparency() { return background.getTransparency(); }
	public float getFrameTransparency() { return frame.getTransparency(); }
	public RenderableBackground getBackground() { return background; }
	public RenderableFrame getFrame() { return frame; }
	public void reqUpdate() { this.reqUpdate = true; }

	public RenderableComponent setX(Dimension x) {
		if (frame.getX() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getX()).getRelTo());
		frame.setX(x);
		this.reqUpdate = true;
		if (frame.getX() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getX()).getRelTo());
		return this;
	}
	public Dimension getX() { return frame.getX(); }
	public int x() { return frame.getX().val() + container.safeX(); }
	public int relX() { return frame.getX().val(); }
	public int safeX() { return frame.getX().val() + container.safeX() + marginX.val(); }

	public RenderableComponent setY(Dimension y) {
		if (frame.getY() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getY()).getRelTo());
		frame.setY(y);
		this.reqUpdate = true;
		if (frame.getY() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getY()).getRelTo());
		return this;
	}
	public Dimension getY() { return frame.getY(); }
	public int y() { return frame.getY().val() + container.safeY(); }
	public int relY() { return frame.getY().val(); }
	public int safeY() { return frame.getY().val() + container.safeY() + marginY.val(); }

	public RenderableComponent setWidth(Dimension width) {
		if (frame.getWidth() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getWidth()).getRelTo());
		frame.setWidth(width);
		this.reqUpdate = true;
		if (frame.getWidth() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getWidth()).getRelTo());
		return this;
	}
	public Dimension getWidth() { return frame.getWidth(); }
	public int width() { return frame.getWidth().val(); }
	public int safeWidth() {
		return frame.getWidth().val() - marginX.val() * 2;
	}

	public RenderableComponent setHeight(Dimension height) {
		if (frame.getHeight() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getHeight()).getRelTo());
		frame.setHeight(height);
		this.reqUpdate = true;
		if (frame.getHeight() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getHeight()).getRelTo());
		return this;
	}
	public Dimension getHeight() { return frame.getHeight(); }
	public int height() { return frame.getHeight().val(); }
	public int safeHeight() { return frame.getHeight().val() - marginY.val() * 2; }

	public RenderableComponent setMarginX(Dimension marginX) {
		if (this.marginX instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) this.marginX).getRelTo());
		this.marginX = marginX;
		this.reqUpdate = true;
		if (this.marginX instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) this.marginX).getRelTo());
		return this;
	}
	public Dimension getMarginX() { return this.marginX; }
	public int marginX() { return this.marginX.val(); }
	
	public RenderableComponent setMarginY(Dimension marginY) {
		if (this.marginY instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) this.marginY).getRelTo());
		this.marginY = marginY;
		this.reqUpdate = true;
		if (this.marginY instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) this.marginY).getRelTo());
		return this;
	}
	public Dimension getMarginY() { return this.marginY; }
	public int marginY() { return this.marginY.val(); }

	public RenderableComponent setRadius(Dimension radius) {
		if (frame.getRadius() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getRadius()).getRelTo());
		frame.setRadius(radius);
		this.reqUpdate = true;
		if (frame.getRadius() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getRadius()).getRelTo());
		return this;
	}
	public Dimension getRadius() { return frame.getRadius(); }
	public int radius() { return frame.getRadius().val(); }

	public RenderableComponent setThickness(Dimension thickness) {
		if (frame.getThickness() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getThickness()).getRelTo());
		frame.setThickness(thickness);
		this.reqUpdate = true;
		if (frame.getThickness() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getThickness()).getRelTo());
		return this;
	}
	public Dimension getThickness() { return frame.getThickness(); }
	public int thickness() { return frame.getThickness().val(); }

	public static class Builder extends Renderable.Builder{
		protected Renderable container;
		protected Dimension x,y,width,height,marginX,marginY,radius,thickness;
		protected Color backgroundColor, frameColor;
		protected boolean visible;
		protected float backgroundTransparency, frameTransparency;
		protected RenderableImage backgroundImage;
		protected Set<DimensionComponent> dimensionReferences;
		protected int zIndex;

		public Builder() {
			this.dimensionReferences = new HashSet<>();
		}

		public  Builder window(Window window) {
			super.window(window);
			return this;
		}

		public  Builder subreference(RenderableComponent renderable) {
			super.subreference(renderable);
			return this;
		}

		public   Builder subreferences(Collection<RenderableComponent> renderables) {
			super.subreferences(renderables);
			return this;
		}

		public   Builder render(BufferedImage render) {
			super.render(render);
			return this;
		}

		public   Builder id(String id) {
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
			this.window = container.window;
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

		public RenderableComponent build() throws IllegalStateException {
			validate();
			return new RenderableComponent(this);
		}

		public void validate() throws IllegalStateException {
			super.validate();
			if (container == null)
				container = BASE;
			if (x == null)
				x = StaticDimension.MIN;
			if (y == null)
				y = StaticDimension.MIN;
			if (width == null)
				width = new RelativeDimension(new DimensionWidth(container),1f);
			if (height == null)
				height = new RelativeDimension(new DimensionHeight(container),1f);
			if (marginX == null)
				marginX = StaticDimension.MIN;
			if (marginY == null)
				marginY = StaticDimension.MIN;
			if (radius == null)
				radius = StaticDimension.MIN;
			if (thickness == null)
				thickness = StaticDimension.MIN;
			if (backgroundColor == null)
				backgroundColor = Color.WHITE;
			if (frameColor == null)
				frameColor = Color.LIGHT_GRAY;
		}
	}
}