package com.alientation.wordle.graphics.renderable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.alientation.wordle.graphics.Window;
import com.alientation.wordle.graphics.renderable.dimension.*;
import com.alientation.wordle.graphics.renderable.dimension.component.*;

/**
 * 
 *
 */
public class RenderableComponent extends Renderable {
	/*
	 * The default renderable of the first Window created
	 */
	public static RenderableComponent BASE = new RenderableComponent();
	
	/*
	 * The container this renderable is contained in. Default is the renderable base
	 */
	protected Renderable container;
	
	/*
	 * Dimension objects, used so there can be relative dimensions to another component's dimension
	 */
	protected Dimension x,y,width,height,marginX,marginY;  //relative position to the container
	
	/*
	 * The Color of this renderable's background
	 */
	protected Color color;
	
	/*
	 * Whether the component should be rendered
	 */
	protected boolean visible;
	
	protected ArrayList<DimensionComponent> dimensionReferences;
	
	public static class Builder extends Renderable.Builder{
		private Renderable container;
		private Dimension x,y,width,height,marginX,marginY;
		private Color color;
		private boolean visible;
		private ArrayList<DimensionComponent> dimensionReferences;
		
		public Builder() {
			this.dimensionReferences = new ArrayList<>();
		}
		
		public Builder window(Window window) {
			super.window(window);
			return this;
		}
		
		public Builder subreference(RenderableComponent renderable) {
			super.subreference(renderable);
			return this;
		}
		
		public Builder subreferences(ArrayList<RenderableComponent> renderables) {
			super.subreferences(renderables);
			return this;
		}
		
		public Builder dimensionReference(DimensionComponent dc) {
			this.dimensionReferences.add(dc);
			return this;
		}
		
		public Builder dimensionReferences(ArrayList<DimensionComponent> dimensions) {
			this.dimensionReferences.addAll(dimensions);
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
			this.container = container;
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
		
		public Builder color(Color color) {
			this.color = color;
			return this;
		}
		
		public Builder visible(boolean visible) {
			this.visible = visible;
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
			if (color == null)
				color = Color.WHITE;
		}
	}
	
	public RenderableComponent(Builder builder) {
		super(builder);
		this.container = builder.container;
		this.x = builder.x;
		this.y = builder.y;
		this.width = builder.width;
		this.height = builder.height;
		this.marginX = builder.marginX;
		this.marginY = builder.marginY;
		this.color = builder.color;
		this.visible = builder.visible;
		this.container.addSubreference(this);
		this.dimensionReferences = new ArrayList<>();
		
		registerDimensions();
	}
	
	
	public RenderableComponent(Renderable container, DimensionContainer dimensions, Color color) {
		super(new Renderable.Builder().window(container.getWindow()));
		this.container = container;
		this.x = dimensions.getDimension(DimensionID.X);
		this.y = dimensions.getDimension(DimensionID.Y);
		this.width = dimensions.getDimension(DimensionID.WIDTH);
		this.height = dimensions.getDimension(DimensionID.HEIGHT);
		this.marginX = dimensions.getDimension(DimensionID.MARGINX);
		this.marginY = dimensions.getDimension(DimensionID.MARGINY);
		this.color = color;
		this.container.addSubreference(this);
		this.dimensionReferences = new ArrayList<>();
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
				.color(Color.WHITE)
		);
	}
	
	public void registerDimensions() {
		registerDimensions(x,y,width,height,marginX,marginY);
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
	
	/**
	 * 
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		//if (this.visible)
			//g.drawImage(render(),x(), y(), null);
			
		g.setColor(color);
		g.fillRect(x(), y(), width(), height());
		super.render(g);
	}
	
	public void updateRender() {
		if (!reqUpdate)
			return;
		reqUpdate = false;
		render = new BufferedImage(width(),height(),BufferedImage.TYPE_INT_ARGB);
		Graphics temp = render.createGraphics();
		temp.setColor(this.color);
		temp.fillRect(0, 0, width(), height());
		
		for (RenderableComponent r : subreferences)
			temp.drawImage(r.render(), r.x(), r.y(), null);
	}
	
	/*
	 * User Input Events
	 */
	
	/**
	 * TODO: Instead have handlers that implement these stuff
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void addDimensionReference(DimensionComponent d) {
		this.dimensionReferences.add(d);
	}
	
	public void removeDimensionReference(DimensionComponent d) {
		this.dimensionReferences.remove(d);
	}
	
	@Override
	public String toString() {
		return container.id() + " -> " + id + ":{" + "(x,y): (" + x() + "," + y() + ") - (w,h): (" + width() + "," + height() + ") - (mX,mY): (" + marginX() + "," + marginY() + ")}";
	}
	
	public RenderableComponent setVisibility(boolean visible) {
		this.visible = visible;
		return this;
	}
	
	public boolean getVisibility() {
		return this.visible;
	}
	
	/*
	 * Area
	 */
	public Rectangle getSafeArea() {
		return new Rectangle(safeX(),safeY(),safeWidth(),safeHeight());
	}
	
	public Rectangle getArea() {
		return new Rectangle(x(),y(),width(),height());
	}
	
	/*
	 * Container
	 */
	public RenderableComponent setContainer(RenderableComponent container) {
		this.container = container;
		this.reqUpdate = true;
		return this;
	}
	
	public Renderable getContainer() {
		return this.container;
	}
	
	/*
	 * Color
	 */
	public RenderableComponent setColor(Color color) {
		this.color = color;
		this.reqUpdate = true;
		return this;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void reqUpdate() {
		this.reqUpdate = true;
	}
	
	/*TODO: finish setting up subreference registration for dimensions stuff
	 * X
	 */
	public RenderableComponent setX(StaticDimension x) {
		this.x.unregister(this);
		this.x = x;
		this.x.register(this);
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setX(RelativeDimension x) {
		this.dimensionReferences.remove(((RelativeDimension) this.x).getRelTo());
		this.x.unregister(this);
		this.x = x;
		this.x.register(this);
		this.dimensionReferences.add(((RelativeDimension) this.x).getRelTo());
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setX(DimensionComponent relTo, float val) {
		if (this.x instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) this.x).getRelTo());
		this.x = new RelativeDimension(relTo,val);
		this.reqUpdate = true;
		this.dimensionReferences.add(((RelativeDimension) this.width).getRelTo());
		return this;
	}
	
	public RenderableComponent setX(int val) {
		if (this.x instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) this.x).getRelTo());
		this.x = new StaticDimension(val);
		this.reqUpdate = true;
		return this;
	}
	
	public Dimension getX() {
		return this.x;
	}
	
	public int x() {
		return this.x.val() + container.safeX();
	}
	
	public int relX() {
		return this.x.val();
	}
	
	public int safeX() {
		return this.x.val() + container.safeX() + marginX.val();
	}
	
	/*
	 * Y
	 */
	public RenderableComponent setY(Dimension y) {
		if (this.y instanceof RelativeDimension)
			this.dimensionReferences.remove(((RelativeDimension) this.y).getRelTo());
		this.y = y;
		this.reqUpdate = true;
		if (this.x instanceof RelativeDimension)
			this.dimensionReferences.add(((RelativeDimension) this.x).getRelTo());
		return this;
	}
	
	public RenderableComponent setY(DimensionComponent relTo, float val) {
		this.y = new RelativeDimension(relTo,val);
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setY(int val) {
		this.y = new StaticDimension(val);
		this.reqUpdate = true;
		return this;
	}
	
	public Dimension getY() {
		return this.y;
	}
	
	public int y() {
		return this.y.val() + container.safeY();
	}
	
	public int relY() {
		return this.y.val();
	}
	
	public int safeY() {
		return this.y.val() + container.safeY() + marginY.val();
	}
	
	/*
	 * Width
	 */
	public RenderableComponent setWidth(Dimension width) {
		this.width = width;
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setWidth(DimensionComponent relTo, float val) {
		this.width = new RelativeDimension(relTo,val);
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setWidth(int val) {
		this.width = new StaticDimension(val);
		this.reqUpdate = true;
		return this;
	}
	
	public Dimension getWidth() {
		return this.width;
	}
	
	public int width() {
		return this.width.val();
	}
	
	public int safeWidth() {
		return this.width.val() - marginX.val() * 2;
	}
	
	/*
	 * Height
	 */
	public RenderableComponent setHeight(Dimension height) {
		this.height = height;
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setHeight(DimensionComponent relTo, float val) {
		this.height = new RelativeDimension(relTo,val);
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setHeight(int val) {
		this.height = new StaticDimension(val);
		this.reqUpdate = true;
		return this;
	}
	
	public Dimension getHeight() {
		return this.height;
	}
	
	public int height() {
		return this.height.val();
	}
	
	public int safeHeight() {
		return this.height.val() - marginY.val() * 2;
	}
	
	/*
	 * Margin
	 */
	public RenderableComponent setMarginX(Dimension marginX) {
		this.marginX = marginX;
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setMarginX(DimensionComponent relTo, float val) {
		this.marginX = new RelativeDimension(relTo,val);
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setMarginX(int val) {
		this.marginX = new StaticDimension(val);
		this.reqUpdate = true;
		return this;
	}
	
	public Dimension getMarginX() {
		return this.marginX;
	}
	
	public int marginX() {
		return this.marginX.val();
	}
	
	public RenderableComponent setMarginY(Dimension marginY) {
		this.marginY = marginY;
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setMarginY(DimensionComponent relTo, float val) {
		this.marginY = new RelativeDimension(relTo,val);
		this.reqUpdate = true;
		return this;
	}
	
	public RenderableComponent setMarginY(int val) {
		this.marginY = new StaticDimension(val);
		this.reqUpdate = true;
		return this;
	}
	
	public Dimension getMarginY() {
		return this.marginY;
	}
	
	public int marginY() {
		return this.marginY.val();
	}
}