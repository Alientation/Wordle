package com.alientation.gui.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.alientation.gui.graphics.renderable.RenderableComponent;

public class MouseInput implements MouseListener, MouseMotionListener {
	
	private RenderableComponent container;
	
	public MouseInput(RenderableComponent container) {
		this.container = container;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		container.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		container.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		container.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		container.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		container.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		container.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		container.mouseReleased(e);
	}
	
	
}
