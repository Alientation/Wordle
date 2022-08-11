package com.alientation.gui.graphics.renderable;

import com.alientation.gui.graphics.renderable.collections.stack.RenderableStack;
import com.alientation.gui.graphics.renderable.collections.stack.RenderableStackElement;
import com.alientation.gui.graphics.renderable.dimension.StaticDimension;

/**
 * Determines the dimension of each stack element
 * 4 preset arrangements though more can be added
 *
 */
public interface Sizing{
	public static final Sizing VERTICAL_EQUAL = new Sizing() {
		@Override
		public void resize(RenderableComponent renderable) {
			if (!(renderable instanceof RenderableStack)) return;
				
			RenderableStack stack = (RenderableStack) renderable;
			if (stack.size() == 0) return;
			int height = (stack.safeHeight() - stack.spacing() * (stack.size() - 1)) / stack.size();
			int currentY = 0;
			for (int i = 0; i < stack.getStackSlots().size(); i++) {
				RenderableStackElement element = stack.getStackSlots().get(i);
				if (i == stack.getStackSlots().size() - 1)
					element.setHeight(new StaticDimension(stack.safeHeight() - currentY));
				else 
					element.setHeight(new StaticDimension(height));
				element.setY(new StaticDimension(currentY));
				currentY+=height + stack.spacing();
			}
		}
	};
	
	public static final Sizing VERTICAL_PROPORTIONAL = new Sizing() {
		@Override
		public void resize(RenderableComponent renderable) {
			if (!(renderable instanceof RenderableStack)) return;
			RenderableStack stack = (RenderableStack) renderable;
			
			if (stack.size() == 0) return;
			int sumHeight = 0;
			int currentY = stack.safeY();
			for (int i = 0; i < stack.getRenderables().size(); i++)
				sumHeight+=stack.getRenderables().get(i).height();
			for (int i = 0; i < stack.getStackSlots().size(); i++) {
				RenderableStackElement element = stack.getStackSlots().get(i);
				if (i == stack.getStackSlots().size() - 1)
					element.setHeight(new StaticDimension(stack.safeHeight() - currentY));
				else
					element.setHeight(new StaticDimension(element.getChild().height() * stack.height() / sumHeight));
				element.setY(new StaticDimension(currentY));
				currentY += element.height() + stack.spacing();
			}
		}
	};
	
	public static final Sizing HORIZONTAL_EQUAL = new Sizing() {
		@Override
		public void resize(RenderableComponent renderable) {
			if (!(renderable instanceof RenderableStack)) return;
			RenderableStack stack = (RenderableStack) renderable;
			if (stack.size() == 0) return;
			int width = (stack.safeWidth() - stack.spacing() * (stack.size() - 1)) / stack.size();
			int currentX = 0;
			for (int i = 0; i < stack.getStackSlots().size(); i++) {
				RenderableStackElement element = stack.getStackSlots().get(i);
				if (i == stack.getStackSlots().size() - 1)
					element.setWidth(new StaticDimension(stack.safeWidth() - currentX));
				else
					element.setWidth(new StaticDimension(width));
				element.setX(new StaticDimension(currentX));
				currentX+=width + stack.spacing();
			}
		}
	};
	
	public static final Sizing HORIZONTAL_PROPORTIONAL = new Sizing() {
		@Override
		public void resize(RenderableComponent renderable) {
			if (!(renderable instanceof RenderableStack)) return;
			RenderableStack stack = (RenderableStack) renderable;
			if (stack.size() == 0) return;
			int sumWidth = 0;
			int currentX = stack.safeX();
			for (int i = 0; i < stack.getRenderables().size(); i++)
				sumWidth+=stack.getRenderables().get(i).width();
			for (int i = 0; i < stack.getStackSlots().size(); i++) {
				RenderableStackElement element = stack.getStackSlots().get(i);
				if (i == stack.getStackSlots().size() - 1)
					element.setWidth(new StaticDimension(stack.safeWidth() - currentX));
				else
					element.setHeight(new StaticDimension(element.getChild().width() * stack.width() / sumWidth));
				element.setX(new StaticDimension(currentX));
				currentX+=element.width() + stack.spacing();
			}
		}
	};
	
	void resize(RenderableComponent renderable);
}