package com.alientation.gui.graphics.renderable;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.alientation.gui.graphics.Window;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionComponent;

/**
 * Base for all renderable objects. All renderables must inherit from this.
 *
 *
 */
public class Renderable {
	//Window that this renderable is contained within
	protected Window window;
	//whether this renderable needs to update the render because this and/or subreferences were changed visually
	protected boolean requireRenderUpdate = true;
	//whether this renderable needs to reorder the z indexes because this and/or subreferences were updated
	protected boolean requireZIndexUpdate = true;
	//allows for the z index to be updated as needed
	protected boolean dynamicZIndexing = true;

	//RenderableComponents that are contained within the bounds of this renderable
	protected Set<RenderableComponent> subreferences;
	//dimensionComponents accessed by this renderable
	protected Set<DimensionComponent> dimensionReferences;

	//Unique id potentially for later to store and load guis from data files
	protected String id;
	public Renderable(Builder<?> builder) {
		this.window = builder.window;
		this.subreferences = builder.subreferences;
		this.dimensionReferences = builder.dimensionReferences;
		this.dynamicZIndexing = builder.dynamicZIndexing;
		this.id = builder.id;
	}
	
	/**
	 * Base constructor for all renderables
	 * 
	 * @param window container
	 */
	public Renderable(Window window) {
		this.window = window != null ? window : Window.INIT_WINDOW;
	}
	
	public Renderable addSubreference(RenderableComponent subreference) {
		this.subreferences.add(subreference);
		this.requireRenderUpdate = true;
		this.requireZIndexUpdate = true;
		return this;
	}
	
	public Renderable removeSubreference(RenderableComponent subreference) {
		this.subreferences.remove(subreference);
		this.requireRenderUpdate = true;
		this.requireZIndexUpdate = true;
		return this;
	}
	
	public Set<RenderableComponent> getSubreferences() { return this.subreferences; }

	/**
	 * Dynamically update Z Index if required
	 */
	public void reorderZIndexing(int pastZIndex) {
		this.requireZIndexUpdate = false;
		for (RenderableComponent renderableComponent : subreferences)
			renderableComponent.reorderZIndexing(pastZIndex + 1);
	}
	
	public void render(Graphics g) {
		System.out.println(id);
	}

	public void tick() {
		//TODO dunno whether to remove requireRenderUpdate, could instead just request that the window redraw

		for (RenderableComponent renderableComponent : subreferences)
			renderableComponent.tick();
	}
	
	public void resized() {
		this.requireRenderUpdate = true;
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
	 * @param window the jFrame window this renderable is container within. It can also be the window's renderable
	 */
	public void setWindow(Window window) {
		//TODO: resolve potential conflicts that arise from changing the windows
		this.window = window;
		this.requireRenderUpdate = true;
		this.requireZIndexUpdate = true;
	}
	
	@Override
	public String toString() {
		return window.getName() + " -> " + id + ":{" + "(x,y): (" + x() + "," + y() + ") - (w,h): (" + width() +
				"," + height() + ") - (mX,mY): (" + marginX() + "," + marginY() + ")}";
	}
	
	/**
	 * Returns the safe area bounds. The safe area is the area minus the margins.
	 * 
	 * @return Rectangle bounds
	 */
	public Rectangle getSafeArea() { return getArea(); }
	
	/**
	 * Returns the area bounds
	 * 
	 * @return Rectangle bounds
	 */
	public Rectangle getArea() { return new Rectangle(x(),y(),width(),height()); }
	
	/**
	 * Returns the absolute x position of this component relative to the canvas.
	 * Initially the renderable base is only contained within the window which is essentially the canvas,
	 * so x position is that of the window. Should be 0.
	 * 
	 * @return x position
	 */
	public int x() { return this.window.getX(); }
	
	/**
	 * Returns the relative x position of this component relative to the container.
	 * Initially the renderable base is only contained within the window which is essentially the canvas.
	 * 
	 * @return relative x position to the container
	 */
	public int relX() { return 0; }
	
	/**
	 * Returns the x position that marks the start of the safe area depending on the marginX
	 * 
	 * @return safe x position
	 */
	public int safeX() { return this.window.getX(); }
	
	/**
	 * Returns the absolute y position of this component relative to the canvas.
	 * Initially the renderable base is only contained within the window which is essentially the canvas,
	 * so y position is that of the window. Should be 0.
	 * 
	 * @return y position
	 */
	public int y() { return this.window.getY(); }
	
	/**
	 * Returns the relative y position of this component relative to the container.
	 * Initially the renderable base is only contained within the window which is essentially the canvas.
	 * 
	 * @return relative y position to the container
	 */
	public int relY() { return 0; }
	
	/**
	 * Returns the y position that marks the start of the safe area depending on the marginY
	 * 
	 * @return safe y position
	 */
	public int safeY() { return this.window.getY(); }
	
	/**
	 * Returns the overall width of the renderable
	 * 
	 * @return overall width
	 */
	public int width() { return this.window.getWidth(); }
	
	/**
	 * Returns the safe width of the section of this renderable inside the marginX
	 * 
	 * @return safe width
	 */
	public int safeWidth() { return this.window.getWidth(); }
	
	/**
	 * Returns the overall height of this renderable
	 * 
	 * @return overall height
	 */
	public int height() { return this.window.getHeight(); }
	
	/**
	 * Returns the safe height of the section of this renderable inside the marginY
	 * 
	 * @return safe height
	 */
	public int safeHeight() { return this.window.getHeight(); }
	
	/**
	 * Returns the marginX of this renderable
	 * 
	 * @return marginX
	 */
	public int marginX() { return 0; }
	
	/**
	 * Returns the marginY of this renderable
	 * 
	 * @return marginY
	 */
	public int marginY() { return 0; }
	
	/**
	 * For debugging purposes
	 * 
	 * @return component id
	 */
	public String id() { return this.id; }

	public boolean requireRenderUpdate() { return requireRenderUpdate; }
	public void setRequireRenderUpdate(boolean requireRenderUpdate) { this.requireRenderUpdate = requireRenderUpdate; }
	public boolean requireZIndexUpdate() { return requireZIndexUpdate; }
	public void setRequireZIndexUpdate(boolean requireZIndexUpdate) { this.requireZIndexUpdate = requireZIndexUpdate; }
	public boolean doDynamicZIndexing() { return dynamicZIndexing; }
	public void setDynamicZIndexing(boolean dynamicZIndexing) { this.dynamicZIndexing = dynamicZIndexing; }

	/**
	 * How to subclass Builder pattern classes
	 * <a href="https://stackoverflow.com/questions/17164375/subclassing-a-java-builder-class/34741836#34741836">...</a>
	 *
	 * @param <T> help with subclassing builders
	 */
	public static class Builder<T extends Builder<T>> {
		protected Window window;
		protected Set<RenderableComponent> subreferences;
		protected Set<DimensionComponent> dimensionReferences;
		protected String id;
		protected boolean dynamicZIndexing;

		public Builder() {
			this.subreferences = new HashSet<>();
			this.dimensionReferences = new HashSet<>();
		}
		public T window(Window window) {
			this.window = window;
			return (T) this;
		}
		public T subreference(RenderableComponent renderable) {
			subreferences.add(renderable);
			return (T) this;
		}
		public T subreferences(Collection<RenderableComponent> renderables) {
			subreferences.addAll(renderables);
			return (T) this;
		}
		public T dimensionReference(DimensionComponent dc) {
			this.dimensionReferences.add(dc);
			return (T) this;
		}
		public T dimensionReferences(Collection<DimensionComponent> dimensions) {
			this.dimensionReferences.addAll(dimensions);
			return (T) this;
		}
		public T id(String id) {
			this.id = id;
			return (T) this;
		}
		public T dynamicZIndexing(boolean dynamicZIndexing) {
			this.dynamicZIndexing = dynamicZIndexing;
			return (T) this;
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
}
