package com.alientation.gui.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.alientation.gui.graphics.events.Event;
import com.alientation.gui.graphics.events.EventDispatcher;
import com.alientation.gui.graphics.events.types.*;
import com.alientation.gui.graphics.renderable.Renderable;

public class Window extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static final int INIT_WIDTH = 800, INIT_HEIGHT = 1000, NUM_TICKS = 60;
	public static final String INIT_TITLE = "WORDLE";
	
	public static Window INIT_WINDOW;
	
	protected BufferedImage image;
	protected Graphics g;
	protected BufferStrategy bs;
	
	protected Renderable renderable;
	protected JFrame frame;
	
	protected int prevWidth, prevHeight, fps, tps;
	protected Thread windowThread;
	protected boolean running;
	protected EventDispatcher eventDispatcher;
	
	
	public Window(int width, int height, String title) {
		this(width,height,title,true,true);
	}
	
	public Window(int width, int height, String title, boolean resizable) {
		this(width,height,title,resizable,true);
	}
	
	public Window(int width, int height, String title, boolean resizable, boolean visible) {
		if (INIT_WINDOW == null)
			INIT_WINDOW = this;
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width,height));
		frame.setSize(new Dimension(width,height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(resizable);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(visible);
		Toolkit.getDefaultToolkit().setDynamicLayout(false);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MousePressedEvent event = new MousePressedEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				MouseReleasedEvent event = new MouseReleasedEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				MouseMovedEvent event = new MouseMovedEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseScrolledEvent event = new MouseScrolledEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				MouseClickedEvent event = new MouseClickedEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				MouseDraggedEvent event = new MouseDraggedEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				MouseEnteredEvent event = new MouseEnteredEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MouseExitedEvent event = new MouseExitedEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
		});

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				KeyTypedEvent event = new KeyTypedEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				KeyPressedEvent event = new KeyPressedEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				KeyReleasedEvent event = new KeyReleasedEvent(renderable.getWindow(), e);
				eventDispatcher.dispatch(event);
			}
		});

		eventDispatcher = new EventDispatcher(this);

		renderable = new Renderable.Builder().window(this).build();
	}
	
	public void resize() {
		renderable.resized();
	}
	
	public void tick() {
		if (prevWidth != this.getWidth() || prevHeight != this.getHeight()) {
			prevWidth = this.getWidth();
			prevHeight = this.getHeight();
			resize();
		}
	}
	
	public void render() {
		preRender();
		postRender();
	}
	
	public void preRender() {
		bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
	}
	
	public void postRender() {
		g.dispose();
		bs.show();
	}
	
	public synchronized void start() {
		this.createBufferStrategy(3);
		bs = this.getBufferStrategy();
		g = bs.getDrawGraphics();
		
		this.running = true;
		this.windowThread = new Thread(this);
		this.windowThread.start();
	}
	
	public synchronized void stop() {
		try {
			this.windowThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.running = false;
	}
	
	/**
	 * Ensures that the specified number of ticks per second is reached while rendering as fast as possible in the remaining time
	 */
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = NUM_TICKS;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int numTicks = 0;
		long now;
		while(this.running) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				numTicks++;
				delta--;
				frames++;
				render();
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				
				this.fps = frames;
				this.tps = numTicks;
				frames = 0;
				numTicks = 0;
			}
		}
		stop();
	}	
	public Graphics getGraphics() { 
		return this.g; 
	}
	
	public Renderable getRenderable() {
		return this.renderable;
	}
	
	public void setRenderable(Renderable renderable) {
		this.renderable = renderable;
	}
	
	public int getFPS() {
		return fps;
	}
	
	public int getTPS() {
		return tps;
	}
}
