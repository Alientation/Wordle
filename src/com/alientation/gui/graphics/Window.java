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
	public static final int INIT_WIDTH = 800, INIT_HEIGHT = 1000, INIT_TPS = 120, INIT_FPS = 60;
	public static final String INIT_TITLE = "Untitled";
	public static final String RESOURCE = "res\\";
	public static final String IMAGES = RESOURCE + "images\\";
	public static final String FONTS = RESOURCE + "fonts\\";
	public static Window INIT_WINDOW;

	protected BufferedImage icon;

	protected Graphics g;
	protected BufferStrategy bs;
	protected Renderable renderable;
	protected JFrame frame;
	protected WindowRenderer windowRenderer;
	protected boolean updateWindowRenderer = true;

	protected int prevWidth, prevHeight;
	protected EventDispatcher eventDispatcher;

	//run loop
	protected static final int NANOSECONDS = 1000000000;
	protected double nsPerTick, nsPerFrame;
	protected int targetTPS, tps, targetFPS, fps;
	protected Thread windowThread;
	protected boolean running;
	
	
	public Window(int width, int height, String title) {
		this(width,height,title,true);
	}
	
	public Window(int width, int height, String title, boolean resizable) {
		this(width,height,title,resizable,true,null,INIT_TPS,INIT_FPS);
	}
	
	public Window(int width, int height, String title, boolean resizable, boolean visible,
				  BufferedImage icon, int tps, int fps) {
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
		frame.setIgnoreRepaint(true);

		targetTPS = tps;
		targetFPS = fps;
		updateTimeBetweenUpdates();

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
				System.out.println("begin click");
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
		windowRenderer = new WindowRenderer(this);

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
		renderable.reorderZIndexing(0);
		if (updateWindowRenderer)
			windowRenderer.update();
		renderable.tick();
	}

	public void updateTimeBetweenUpdates() {
		nsPerTick = ((float)NANOSECONDS) / targetTPS;
		nsPerFrame = ((float) NANOSECONDS) / targetFPS;
	}
	
	public void preRender() {
		bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		/*g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());*/
	}

	public void render() {
		preRender();
		if (!windowRenderer.render(g)) {
			g.dispose();
			return; //no need to show anything, nothing was updated
		}

		postRender();
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
		double deltaTick = 0;
		double deltaFrame = 0;
		int numTicks = 0;
		int numFrames = 0;
		while(this.running) {
			now = System.nanoTime();
			deltaTick += (now - lastTime) / nsPerTick;
			deltaFrame += (now - lastTime) / nsPerFrame;
			lastTime = now;
			while (deltaTick >= 1) {
				tick();
				numTicks++;
				deltaTick--;
			}

			while (deltaFrame >= 1) {
				render();
				numFrames++;
				deltaFrame--;
			}

			//update tps (fps too since they are the same as of right now)
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.tps = numTicks;
				this.fps = numFrames;
				numTicks = 0;
				numFrames = 0;
			}

			//so that the cpu doesn't waste resources
			sync(Math.min(System.nanoTime() + (1f - deltaTick) * nsPerTick,System.nanoTime() + (1f - deltaFrame) * nsPerFrame));
		}
		stop();
	}

	public void sync(double endTime) {
		while (System.nanoTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
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
		updateTimeBetweenUpdates();
	}
	public int getTargetFPS() { return targetFPS; }
	public void setTargetFPS(int targetFPS) {
		this.targetFPS = targetFPS;
		updateTimeBetweenUpdates();
	}
	public int getPrevWidth() { return prevWidth; }
	public int getPrevHeight() { return prevHeight; }
	public EventDispatcher getEventDispatcher() { return eventDispatcher; }
	public double getNsPerTick() { return nsPerTick; }
	public int getTps() { return tps; }
	public boolean isRunning() { return running; }
	public void setUpdateWindowRenderer(boolean updateWindowRenderer) { this.updateWindowRenderer = updateWindowRenderer; }
}
