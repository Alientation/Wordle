package com.alientation.wordle.frames;

import java.awt.Color;

import com.alientation.gui.graphics.renderable.Renderable;
import com.alientation.gui.graphics.renderable.Sizing;
import com.alientation.gui.graphics.renderable.collections.stack.RenderableStack;
import com.alientation.gui.graphics.renderable.dimension.RelativeDimension;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeHeight;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeWidth;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeX;
import com.alientation.gui.graphics.renderable.dimension.component.DimensionSafeY;

public class MainFrame {
	private RenderableStack renderable;
	private RenderableStack wordleKeyboardStack;
	private WordleFrame wordleFrame;
	private DataFrame dataFrame;
	private KeyboardFrame keyboardFrame;
	private SettingFrame settingsFrame;
	
	public MainFrame(Renderable container) {
		renderable = new RenderableStack.Builder()
				.container(container)
				.x(new RelativeDimension(new DimensionSafeX(container),1f))
				.y(new RelativeDimension(new DimensionSafeY(container),1f))
				.width(new RelativeDimension(new DimensionSafeWidth(container),1f))
				.height(new RelativeDimension(new DimensionSafeHeight(container),1f))
				.marginX(new StaticDimension(10))
				.marginY(new StaticDimension(10))
				.spacing(new StaticDimension(10))
				.color(Color.GREEN)
				.sizing(Sizing.HORIZONTAL_EQUAL)
				.id("MAIN-FRAME")
				.build();
		
		settingsFrame = new SettingFrame(renderable);
		
		wordleKeyboardStack = new RenderableStack.Builder()
				.container(renderable)
				.spacing(new StaticDimension(5))
				.color(Color.PINK)
				.sizing(Sizing.VERTICAL_EQUAL)
				.marginX(new StaticDimension(5))
				.marginY(new StaticDimension(5))
				.build();
		
		wordleFrame = new WordleFrame(renderable);
		keyboardFrame = new KeyboardFrame(wordleKeyboardStack);
		wordleKeyboardStack.addRenderable(wordleFrame.getRenderable()).addRenderable(keyboardFrame.getRenderable());
		
		dataFrame = new DataFrame(renderable);
		
		renderable.addRenderable(settingsFrame.getRenderable()).addRenderable(wordleKeyboardStack).addRenderable(dataFrame.getRenderable());
	}

	public RenderableStack getRenderable() { return renderable; }
	public void setRenderable(RenderableStack renderable) { this.renderable = renderable; }
}
