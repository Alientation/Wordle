package com.alientation.gui.graphics.renderable;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.alientation.gui.graphics.Window;

/**
 * Base for all renderable objects. All renderables must inherit from this.
 *
 */
public class Renderable {
	
	/*
	 * Window that this renderable is contained within
	 */
	protected Window window;
	
	/*
	 * The BufferedImage that details this renderable only, dimensions equal to the dimension of this renderable. Used for z ordering and rendering optimization. Still in development
	 */
	protected BufferedImage render;
	protected boolean reqUpdate;
	protected ArrayList<RenderableComponent> subreferences;
	
	protected String id;
	
	
	public static class Builder {
		private Window window;
		private BufferedImage render;
		private ArrayList<RenderableComponent> subreferences;
		private String id;
		
		public Builder() {
			this.subreferences = new ArrayList<>();
		}
		
		public Builder window(Window window) {
			this.window = window;
			return this;
		}
		
		public Builder subreference(RenderableComponent renderable) {
			subreferences.add(renderable);
			return this;
		}
		
		public Builder subreferences(ArrayList<RenderableComponent> renderable) {
			subreferences.addAll(renderable);
			return this;
		}
		
		public Builder render(BufferedImage render) {
			this.render = render;
			return this;
		}
		
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		public Renderable build() throws IllegalStateException {
			validate();
			return new Renderable(this);
		}
		
		public void validate() throws IllegalStateException {
			if (id == null)
				id = "unidentified";
		}
	}
	
	public Renderable(Builder builder) {
		this.window = builder.window;
		this.render = builder.render;
		this.subreferences = builder.subreferences;
		this.reqUpdate = true;
		this.id = builder.id;
	}
	
	/**
	 * Base constructor for all renderables
	 * 
	 * @param window container
	 */
	public Renderable(Window window, BufferedImage render) {
		this.window = window != null ? window : Window.INIT_WINDOW;
		this.render = render;
		this.reqUpdate = true;
	}
	
	public Renderable addSubreference(RenderableComponent subreference) {
		this.subreferences.add(subreference);
		this.reqUpdate = true;
		return this;
	}
	
	public Renderable removeSubreference(RenderableComponent subreference) {
		this.subreferences.remove(subreference);
		this.reqUpdate = true;
		return this;
	}
	
	public ArrayList<RenderableComponent> getSubreferences() {
		return this.subreferences;
	}
	
	/**
	 * TODO: implement
	 * 
	 * @return the BufferedImage of this renderable. Used so z ordering can be implemented
	 */
	public BufferedImage render() {
		updateRender();
		return render;
	}
	
	public void render(Graphics g) {
		for (RenderableComponent r : subreferences)
			r.render(g);
		//g.drawImage(render(),x(), y(), null);
	}
	
	/**
	 * TODO: fix this render optimization.. instead of having Renderable and RenderableComponent, combine these two, add update events to dimensions
	 */
	public void updateRender() {
		if (!reqUpdate)
			return;
		reqUpdate = false;
		render = new BufferedImage(width(),height(),BufferedImage.TYPE_INT_ARGB);
		Graphics temp = render.createGraphics();
		for (RenderableComponent r : subreferences)
			temp.drawImage(r.render(), r.x(), r.y(), null);
	}
	
	public void resized() {
		this.reqUpdate = true;
		for (RenderableComponent r : subreferences)
			r.resized();
	}
	
	/**
	 * 
	 * @return the window
	 */
	public Window getWindow() {
		return this.window;
	}
	
	/**
	 * Sets the window the current renderable is contained within and updates references to resolve potential conflicts
	 * 
	 * @param window
	 */
	public void setWindow(Window window) {
		//TODO: resolve potential conflicts that arise from changing the windows
		this.window = window;
		this.reqUpdate = true;
	}
	
	@Override
	public String toString() {
		return window.getName() + " -> " + id + ":{" + "(x,y): (" + x() + "," + y() + ") - (w,h): (" + width() + "," + height() + ") - (mX,mY): (" + marginX() + "," + marginY() + ")}";
	}
	
	/**
	 * Returns the safe area bounds. The safe area is the area minus the margins.
	 * 
	 * @return Rectangle bounds
	 */
	public Rectangle getSafeArea() {
		return getArea();
	}
	
	/**
	 * Returns the area bounds
	 * 
	 * @return Rectangle bounds
	 */
	public Rectangle getArea() {
		return new Rectangle(x(),y(),width(),height());
	}
	
	/**
	 * Returns the absolute x position of this component relative to the canvas. Initially the renderable base is only contained within the window which is essentially the canvas, so x position is that of the window. Should be 0.
	 * 
	 * @return x position
	 */
	public int x() {
		return this.window.getX();
	}
	
	/**
	 * Returns the relative x position of this component relative to the container. Initially the renderable base is only contained within the window which is essentially the canvas.
	 * 
	 * @return relative x position to the container
	 */
	public int relX() {
		return 0;
	}
	
	/**
	 * Returns the x position that marks the start of the safe area depending on the marginX
	 * 
	 * @return safe x position
	 */
	public int safeX() {
		return this.window.getX();
	}
	
	/**
	 * Returns the absolute y position of this component relative to the canvas. Initially the renderable base is only contained within the window which is essentially the canvas, so y position is that of the window. Should be 0.
	 * 
	 * @return y position
	 */
	public int y() {
		return this.window.getY();
	}
	
	/**
	 * Returns the relative y position of this component relative to the container. Initially the renderable base is only contained within the window which is essentially the canvas.
	 * 
	 * @return relative y position to the container
	 */
	public int relY() {
		return 0;
	}
	
	/**
	 * Returns the y position that marks the start of the safe area depending on the marginY
	 * 
	 * @return safe y position
	 */
	public int safeY() {
		return this.window.getY();
	}
	
	/**
	 * Returns the overall width of the renderable
	 * 
	 * @return overall width
	 */
	public int width() {
		return this.window.getWidth();
	}
	
	/**
	 * Returns the safe width of the section of this renderable inside the marginX
	 * 
	 * @return safe width
	 */
	public int safeWidth() {
		return this.window.getWidth();
	}
	
	/**
	 * Returns the overall height of this renderable
	 * 
	 * @return overall height
	 */
	public int height() {
		return this.window.getHeight();
	}
	
	/**
	 * Returns the safe height of the section of this renderable inside the marginY
	 * 
	 * @return safe height
	 */
	public int safeHeight() {
		return this.window.getHeight();
	}
	
	/**
	 * Returns the marginX of this renderable
	 * 
	 * @return marginX
	 */
	public int marginX() {
		return 0;
	}
	
	/**
	 * Returns the marginY of this renderable
	 * 
	 * @return marginY
	 */
	public int marginY() {
		return 0;
	}
	
	/**
	 * For debugging purposes
	 * 
	 * @return component id
	 */
	public String id() {
		return this.id;
	}
}
