package com.alientation.gui.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.alientation.gui.graphics.renderable.RenderableComponent;
@Deprecated
public class Deprecated_MouseInput implements MouseListener, MouseMotionListener {
	
	private RenderableComponent container;
	@Deprecated
	public Deprecated_MouseInput(RenderableComponent container) {
		this.container = container;
	}
	@Deprecated
	@Override
	public void mouseDragged(MouseEvent e) {
		container.mouseDragged(e);
	}
	@Deprecated
	@Override
	public void mouseMoved(MouseEvent e) {
		container.mouseMoved(e);
	}
	@Deprecated
	@Override
	public void mouseClicked(MouseEvent e) {
		container.mouseClicked(e);
	}
	@Deprecated
	@Override
	public void mouseEntered(MouseEvent e) {
		container.mouseEntered(e);
	}
	@Deprecated
	@Override
	public void mouseExited(MouseEvent e) {
		container.mouseExited(e);
	}
	@Deprecated
	@Override
	public void mousePressed(MouseEvent e) {
		container.mousePressed(e);
	}
	@Deprecated
	@Override
	public void mouseReleased(MouseEvent e) {
		container.mouseReleased(e);
	}
	
	
}
