package com.alientation.gui.graphics.renderable;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.events.EventDispatcher;
import com.alientation.gui.graphics.events.EventHandler;
import com.alientation.gui.graphics.events.EventListener;
import com.alientation.gui.graphics.renderable.dimension.Dimension;
import com.alientation.gui.graphics.renderable.dimension.RelativeDimension;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;
import com.alientation.gui.graphics.renderable.dimension.component.*;

import java.awt.*;

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
	//Whether any dimensions have been updated
	protected boolean requireDimensionUpdate = false;
	protected int zIndex;
	protected RenderableBackground background;
	protected RenderableFrame frame;

	protected EventDispatcher eventDispatcher;

	/**
	 * TODO: fix builder class in other renderable classes
	 */

	
	public RenderableComponent(Builder<?> builder) {
		super(builder);
		this.container = builder.container;
		this.marginX = builder.marginX;
		this.marginY = builder.marginY;
		this.visible = builder.visible;
		this.container.addSubreference(this);
		this.zIndex = builder.zIndex;
		this.background = new RenderableBackground(this, builder.backgroundImage, builder.backgroundColor,
				builder.backgroundTransparency);
		this.frame = new RenderableFrame(this, builder.x, builder.y, builder.width, builder.height,
				builder.radiusWidth, builder.radiusHeight, builder.thickness, builder.frameColor, builder.frameTransparency);

		this.eventDispatcher = new EventDispatcher(this);
		registerDimensions();
	}

	/**
	 *
	 *
	 */
	private RenderableComponent() {
		this(new Builder<>()
				.container(Window.INIT_WINDOW.getRenderable())
				.x(new StaticDimension(0))
				.y(new StaticDimension(0))
				.width(new RelativeDimension(new DimensionSafeWidth(Window.INIT_WINDOW.getRenderable()),1.0f))
				.height(new RelativeDimension(new DimensionSafeHeight(Window.INIT_WINDOW.getRenderable()),1.0f))
				.marginX(new StaticDimension(0))
				.marginY(new StaticDimension(0))
				.backgroundColor(Color.WHITE)
				.id("BASE-RENDERABLE")
		);
	}
	
	public void registerDimensions() {
		registerDimensions(marginX,marginY, frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight(),
				frame.getRadiusWidth(),frame.getRadiusHeight(), frame.getThickness());
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

		//currently the only way for a dimension's value changes is if the overarching window is resized.
		//need to make it work all the time
		//since the rendering only happens when needed, the dimensionComponent's optimization of only updating the value
		//when needed is not required necessarily
		//however it would be nice to still have it
		for (DimensionComponent d : this.dimensionReferences)
			d.valueChanged();
	}

	/**
	 * Dynamically update Z Index if required
	 */
	@Override
	public void reorderZIndexing(int pastZIndex) {
		if (dynamicZIndexing && this.zIndex <= pastZIndex) {
			this.zIndex = pastZIndex + 1;
			this.window.setUpdateWindowRenderer(true);
		}
		this.requireZIndexUpdate = false;
		for (RenderableComponent renderableComponent : subreferences)
			renderableComponent.reorderZIndexing(this.zIndex);
	}

	public void tick() {
		super.tick();
		if (requireDimensionUpdate) {
			requireDimensionUpdate = false;
			requireRenderUpdate = true;
			for (DimensionComponent d : this.dimensionReferences)
				d.valueChanged();
		}
	}

	public void render(Graphics g) {
		System.out.print(container.id + " ");
		super.render(g);
		g.setColor(background.getColor());
		//g.fillRect(x(),y(),width(),height());
		g.fillRoundRect(x(), y(), width(), height(), radiusWidth(), radiusHeight());
	}

	public void addDimensionReference(DimensionComponent d) { this.dimensionReferences.add(d); }
	public void removeDimensionReference(DimensionComponent d) { this.dimensionReferences.remove(d); }

	public void registerEventListener(EventListener eventListener) { this.eventDispatcher.registerEventListener(eventListener); }
	public void registerEventHandler(EventHandler eventHandler) { this.eventDispatcher.registerEventHandler(eventHandler); }
	
	@Override
	public String toString() {
		return container.id() + " -> " + id + ":{" + "(x,y): (" + x() + "," + y() + ") - (w,h): (" + width() + "," +
				height() + ") - (mX,mY): (" + marginX() + "," + marginY() + ")}";
	}

	//todo fix the bugs
	public void setWindow(Window window) {
		super.setWindow(window);
		this.window.getRenderable().removeSubreference(this);
		this.container.removeSubreference(this);
		this.window = window;
		this.container = this.window.getRenderable();
		this.window.getRenderable().addSubreference(this);
	}
	
	public RenderableComponent setVisibility(boolean visible) {
		this.visible = visible;
		window.getRenderable().requireRenderUpdate = true;
		return this;
	}
	
	public boolean getVisibility() { return this.visible; }
	public Rectangle getSafeArea() { return new Rectangle(safeX(),safeY(),safeWidth(),safeHeight()); }
	public Rectangle getArea() { return new Rectangle(x(),y(),width(),height()); }

	public RenderableComponent setContainer(RenderableComponent container) {
		if (container == this.container) return this;
		this.container.removeSubreference(this);
		this.container = container;
		this.container.addSubreference(this);
		this.requireRenderUpdate = true;
		this.requireZIndexUpdate = true;
		return this;
	}
	
	public Renderable getContainer() { return this.container; }

	public RenderableComponent setBackgroundColor(Color color) {
		this.background.setColor(color);
		this.requireRenderUpdate = true;
		return this;
	}

	public RenderableComponent setFrameColor(Color color) {
		this.frame.setColor(color);
		this.requireRenderUpdate = true;
		return this;
	}

	public RenderableComponent setBackgroundTransparency(float transparency) {
		background.setTransparency(transparency);
		this.requireRenderUpdate = true;
		return this;
	}

	public RenderableComponent setFrameTransparency(float transparency) {
		frame.setTransparency(transparency);
		this.requireRenderUpdate = true;
		return this;
	}
	
	public Color getBackgroundColor() { return background.getColor(); }
	public Color getFrameColor() { return frame.getColor(); }
	public float getBackgroundTransparency() { return background.getTransparency(); }
	public float getFrameTransparency() { return frame.getTransparency(); }
	public RenderableBackground getBackground() { return background; }
	public RenderableFrame getFrame() { return frame; }

	//for optimizations, only draw if it requires an update TODO DOES IT EVEN WORK???
	public void reqUpdate() {
		this.requireRenderUpdate = true;
		System.out.println("req update");
	}


	//'x' is the true x relative to the window container
	//'relX' is the relative x to the renderable container (Think this isn't actually working) TODO fix this
	public RenderableComponent setX(Dimension x) {
		frame.getX().unregister(this);
		x.register(this);
		if (frame.getX() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getX()).getRelTo());
		frame.setX(x);
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		if (frame.getX() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getX()).getRelTo());
		return this;
	}
	public Dimension getX() { return frame.getX(); }
	public int x() { return frame.getX().val() + container.safeX(); }
	public int relX() { return frame.getX().val(); }
	public int safeX() { return frame.getX().val() + container.safeX() + marginX.val(); }

	public RenderableComponent setY(Dimension y) {
		frame.getY().unregister(this);
		y.register(this);
		if (frame.getY() instanceof RelativeDimension)
			dimensionReferences.remove(((RelativeDimension) frame.getY()).getRelTo());
		frame.setY(y);
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		if (frame.getY() instanceof RelativeDimension)
			dimensionReferences.add(((RelativeDimension) frame.getY()).getRelTo());
		return this;
	}
	public Dimension getY() { return frame.getY(); }
	public int y() { return frame.getY().val() + container.safeY(); }
	public int relY() { return frame.getY().val(); }
	public int safeY() { return frame.getY().val() + container.safeY() + marginY.val(); }

	public RenderableComponent setWidth(Dimension width) {
		frame.getWidth().unregister(this);
		width.register(this);
		if (frame.getWidth() instanceof RelativeDimension)
			dimensionReferences.remove(((RelativeDimension) frame.getWidth()).getRelTo());
		frame.setWidth(width);
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		if (frame.getWidth() instanceof RelativeDimension)
			dimensionReferences.add(((RelativeDimension) frame.getWidth()).getRelTo());
		return this;
	}
	public Dimension getWidth() { return frame.getWidth(); }
	public int width() { return frame.getWidth().val(); }
	public int safeWidth() {
		return frame.getWidth().val() - marginX.val() * 2;
	}

	public RenderableComponent setHeight(Dimension height) {
		frame.getHeight().unregister(this);
		height.register(this);
		if (frame.getHeight() instanceof RelativeDimension)
			dimensionReferences.remove(((RelativeDimension) frame.getHeight()).getRelTo());
		frame.setHeight(height);
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		if (frame.getHeight() instanceof RelativeDimension)
			dimensionReferences.add(((RelativeDimension) frame.getHeight()).getRelTo());
		return this;
	}
	public Dimension getHeight() { return frame.getHeight(); }
	public int height() { return frame.getHeight().val(); }
	public int safeHeight() { return frame.getHeight().val() - marginY.val() * 2; }

	public RenderableComponent setMarginX(Dimension marginX) {
		this.getMarginX().unregister(this);
		marginX.register(this);
		if (this.marginX instanceof RelativeDimension)
			dimensionReferences.remove(((RelativeDimension) this.marginX).getRelTo());
		this.marginX = marginX;
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		if (this.marginX instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) this.marginX).getRelTo());
		return this;
	}
	public Dimension getMarginX() { return this.marginX; }
	public int marginX() { return this.marginX.val(); }
	
	public RenderableComponent setMarginY(Dimension marginY) {
		this.getMarginY().unregister(this);
		marginY.register(this);
		if (this.marginY instanceof RelativeDimension)
			dimensionReferences.remove(((RelativeDimension) this.marginY).getRelTo());
		this.marginY = marginY;
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		if (this.marginY instanceof RelativeDimension)
			dimensionReferences.add(((RelativeDimension) this.marginY).getRelTo());
		return this;
	}
	public Dimension getMarginY() { return this.marginY; }
	public int marginY() { return this.marginY.val(); }

	public RenderableComponent setRadiusWidth(Dimension radiusWidth) {
		this.getRadiusWidth().unregister(this);
		radiusWidth.register(this);
		if (frame.getRadiusWidth() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getRadiusWidth()).getRelTo());
		frame.setRadiusWidth(radiusWidth);
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		if (frame.getRadiusWidth() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getRadiusWidth()).getRelTo());
		return this;
	}
	public Dimension getRadiusWidth() { return frame.getRadiusWidth(); }
	public int radiusWidth() { return frame.getRadiusWidth().val(); }

	public RenderableComponent setRadiusHeight(Dimension radiusHeight) {
		this.getRadiusHeight().unregister(this);
		radiusHeight.register(this);
		if (frame.getRadiusHeight() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getRadiusHeight()).getRelTo());
		frame.setRadiusHeight(radiusHeight);
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		if (frame.getRadiusHeight() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getRadiusHeight()).getRelTo());
		return this;
	}
	public Dimension getRadiusHeight() { return frame.getRadiusHeight(); }
	public int radiusHeight() { return frame.getRadiusHeight().val(); }

	public RenderableComponent setThickness(Dimension thickness) {
		this.getThickness().unregister(this);
		thickness.register(this);
		if (frame.getThickness() instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) frame.getThickness()).getRelTo());
		frame.setThickness(thickness);
		requireRenderUpdate = true;
		requireDimensionUpdate = true;
		if (frame.getThickness() instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) frame.getThickness()).getRelTo());
		return this;
	}
	public Dimension getThickness() { return frame.getThickness(); }
	public int thickness() { return frame.getThickness().val(); }
	public int getZIndex() { return zIndex; }
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
		this.container.requireZIndexUpdate = true;
		this.container.requireRenderUpdate = true;
	}

	public static class Builder<T extends Builder<T>> extends Renderable.Builder<T> {
		protected Renderable container;
		protected Dimension x,y,width,height,marginX,marginY,radiusWidth,radiusHeight,thickness;
		protected Color backgroundColor, frameColor;
		protected boolean visible = true;
		protected float backgroundTransparency, frameTransparency;
		protected RenderableImage backgroundImage;
		protected int zIndex = 0;

		public Builder() {

		}
		public T container(Renderable container) {
			this.container = container;
			this.window = container.window;
			return (T) this;
		}

		public T x(Dimension x) {
			this.x = x;
			return (T) this;
		}

		public T y(Dimension y) {
			this.y = y;
			return (T) this;
		}

		public T width(Dimension width) {
			this.width = width;
			return (T) this;
		}

		public T height(Dimension height) {
			this.height = height;
			return (T) this;
		}

		public T marginX(Dimension marginX) {
			this.marginX = marginX;
			return (T) this;
		}

		public T marginY(Dimension marginY) {
			this.marginY = marginY;
			return (T) this;
		}

		public T radiusWidth(Dimension radiusWidth) {
			this.radiusWidth = radiusWidth;
			return (T) this;
		}

		public T radiusHeight(Dimension radiusHeight) {
			this.radiusHeight = radiusHeight;
			return (T) this;
		}

		public T thickness(Dimension thickness) {
			this.thickness = thickness;
			return (T) this;
		}

		public T backgroundTransparency(float backgroundTransparency) {
			this.backgroundTransparency = backgroundTransparency;
			return (T) this;
		}

		public T frameTransparency(float frameTransparency) {
			this.frameTransparency = frameTransparency;
			return (T) this;
		}

		public T backgroundImage(RenderableImage backgroundImage) {
			this.backgroundImage = backgroundImage;
			return (T) this;
		}

		public T backgroundColor(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
			return (T) this;
		}

		public T frameColor(Color frameColor) {
			this.frameColor = frameColor;
			return (T) this;
		}

		public T visible(boolean visible) {
			this.visible = visible;
			return (T) this;
		}

		public T zIndex(int zIndex) {
			this.zIndex = zIndex;
			return (T) this;
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
			if (radiusWidth == null)
				radiusWidth = StaticDimension.MIN;
			if (radiusHeight == null)
				radiusHeight = StaticDimension.MIN;
			if (thickness == null)
				thickness = StaticDimension.MIN;
			if (backgroundColor == null)
				backgroundColor = Color.WHITE;
			if (frameColor == null)
				frameColor = Color.LIGHT_GRAY;
		}
	}
}