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
import java.io.Serial;

import javax.swing.JFrame;

import com.alientation.gui.graphics.events.EventDispatcher;
import com.alientation.gui.graphics.events.EventHandler;
import com.alientation.gui.graphics.events.EventListener;
import com.alientation.gui.graphics.events.types.key.KeyPressedEvent;
import com.alientation.gui.graphics.events.types.key.KeyReleasedEvent;
import com.alientation.gui.graphics.events.types.key.KeyTypedEvent;
import com.alientation.gui.graphics.events.types.mouse.*;
import com.alientation.gui.graphics.renderable.Renderable;

public class Window extends Canvas implements Runnable {
	@Serial
	private static final long serialVersionUID = 1L;
	public static final int INIT_WIDTH = 800, INIT_HEIGHT = 1000, INIT_TPS = 60;
	public static final String INIT_TITLE = "Untitled";
	public static final String RESOURCE = "res\\";
	public static final String IMAGES = RESOURCE + "images\\";
	public static final String FONTS = RESOURCE + "fonts\\";
	public static Window INIT_WINDOW;

	protected BufferedImage icon;

	//potentially make these events handled and distributed by eventDispatcher instead
	protected Render preRender;
	protected Render render;
	protected Render postRender;


	protected Graphics g;
	protected BufferStrategy bs;
	protected Renderable renderable;
	protected JFrame frame;

	protected int prevWidth, prevHeight;
	protected EventDispatcher eventDispatcher;

	//run loop
	protected static final int NANOSECONDS = 1000000000;
	protected double ns;
	protected int targetTPS, tps;
	protected Thread windowThread;
	protected boolean running;
	
	
	public Window(int width, int height, String title) {
		this(width,height,title,true);
	}
	
	public Window(int width, int height, String title, boolean resizable) {
		this(width,height,title,resizable,true,null,INIT_TPS,null,
				null,null);
	}
	
	public Window(int width, int height, String title, boolean resizable, boolean visible,
				  BufferedImage icon, int tps,
				  Render preRender, Render render, Render postRender) {
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

		targetTPS = tps;
		updateTimeBetweenTicks();

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MousePressedEvent event = new MousePressedEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				MouseReleasedEvent event = new MouseReleasedEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				MouseMovedEvent event = new MouseMovedEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseScrolledEvent event = new MouseScrolledEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				MouseClickedEvent event = new MouseClickedEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				MouseDraggedEvent event = new MouseDraggedEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				MouseEnteredEvent event = new MouseEnteredEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MouseExitedEvent event = new MouseExitedEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
		});

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				KeyTypedEvent event = new KeyTypedEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				KeyPressedEvent event = new KeyPressedEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				KeyReleasedEvent event = new KeyReleasedEvent(renderable, e);
				eventDispatcher.dispatch(event);
			}
		});

		eventDispatcher = new EventDispatcher(this.renderable);

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

	public void updateTimeBetweenTicks() {
		ns = ((float)NANOSECONDS) / targetTPS;
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
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
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
	 * Ensures that the specified number of ticks per second is reached while rendering as
	 * fast as possible in the remaining time
	 */
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		long now;
		long timer = System.currentTimeMillis();
		double delta = 0;
		int numTicks = 0;
		while(this.running) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				render();
				numTicks++;
				delta--;
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.tps = numTicks;
				numTicks = 0;
			}
		}
		stop();
	}

	public void registerEventListener(EventListener eventListener) { this.eventDispatcher.registerEventListener(eventListener); }
	public void registerEventHandler(EventHandler eventHandler) { this.eventDispatcher.registerEventHandler(eventHandler); }
	public Graphics getGraphics() { return this.g; }
	public Renderable getRenderable() { return this.renderable; }
	public void setRenderable(Renderable renderable) { this.renderable = renderable; }
	public int getTPS() { return tps; }
	public BufferedImage getIcon() { return icon; }
	public void setIcon(BufferedImage icon) {
		this.icon = icon;
		frame.setIconImage(icon);
	}
	public int getTargetTPS() { return targetTPS; }

	public void setTargetTPS(int targetTPS) {
		this.targetTPS = targetTPS;
		updateTimeBetweenTicks();
	}
	public int getPrevWidth() { return prevWidth; }
	public int getPrevHeight() { return prevHeight; }
	public EventDispatcher getEventDispatcher() { return eventDispatcher; }
	public double getNs() { return ns; }
	public int getTps() { return tps; }
	public boolean isRunning() { return running; }
	public Render getPreRender() { return preRender; }
	public void setPreRender(Render preRender) { this.preRender = preRender; }
	public Render getRender() { return render; }
	public void setRender(Render render) { this.render = render; }
	public Render getPostRender() { return postRender; }
	public void setPostRender(Render postRender) { this.postRender = postRender; }
}
