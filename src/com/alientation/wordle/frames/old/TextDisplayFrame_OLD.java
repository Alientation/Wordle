package com.alientation.wordle.frames.old;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.alientation.gui.graphics.font.PixelFont;
import com.alientation.gui.graphics.renderable.DeprecatedRenderable;

public class TextDisplayFrame_OLD extends DeprecatedRenderable {
	private static PixelFont pf;
	
	private float relSizeX, relSizeY;
	private int size;
	private String[] text;
	private String original;
	private int[] style;
	
	private Color color;
	private BufferedImage bi = null;
	
	public TextDisplayFrame_OLD(DeprecatedRenderable container, String t, float relX, int minX, int maxX, float relY, int minY, int maxY, float relWidth, int minWidth, int maxWidth, float relHeight, int minHeight, int maxHeight, float relMarginX, int minMarginX, int maxMarginX, float relMarginY, int minMarginY, int maxMarginY, Color color, float relSizeX, float relSizeY, boolean symmetricalDimensions, boolean symmetricalMargins) {
		super(container,relX,minX,maxX, relY, minY, maxY, relWidth, minWidth, maxWidth, relHeight, minHeight, maxHeight, relMarginX, minMarginX, maxMarginX, relMarginY, minMarginY, maxMarginY, color, symmetricalDimensions, symmetricalMargins);
		
		this.color = color;
		this.relSizeX = relSizeX;
		this.relSizeY = relSizeY;
		
		this.text = t.split(" ");
		this.original = t;
		processText();
	}
	
	public void resize() {
		super.resized();
		updateBI();
	}
	
	@Override
	public String toString() {
		return "TextDisplay ( " + original + ") " + super.toString();
	}
	
	public void updateBI() {
		bi = new BufferedImage(2 * marginX + width,2 * marginY + height, BufferedImage.TYPE_INT_ARGB);
		int character = 0;
		int xx = marginX;
		int yy = marginY;
		for (int t = 0; t < text.length; t++) {
			if (style[character] > 2) {
				int tt = style[character] % 10;
				xx += tt * (pf.getBufferedImage(' ',0).getWidth() + size);
				int nn = style[character] / 10;
				yy += nn * (pf.getBufferedImage(' ',style[character]).getHeight() * size + size);
				character++;
			}
			if (xx + getTextLength(text[t]) + pf.getBufferedImage(' ',0).getWidth() * size >= width) {
				xx = marginX;
				yy += pf.getBufferedImage(text[t].charAt(0),style[character]).getHeight() * size + size;
			}
			if (xx != marginX) {
				xx += pf.getBufferedImage(' ',style[character]).getWidth() * size;
			}
			for (char c : text[t].toCharArray()) {
				for (int yyy = 0; yyy < pf.getBufferedImage(c,style[character]).getHeight() * size; yyy++) {
					for (int xxx = 0; xxx < pf.getBufferedImage(c,style[character]).getWidth() * size; xxx++) {
						if (xx + xxx < bi.getWidth() && yy + yyy < bi.getHeight() && pf.getBufferedImage(c,style[character]).getRGB(xxx/size, yyy/size) != 0) {
							bi.setRGB(xx + xxx, yy + yyy, color.getRGB());
						}
					}
				}
				xx += pf.getBufferedImage(c,style[character]).getWidth() * size + size;
				character++;
			}
		}
	}
	
	public void processText() {
		style = new int[original.length()];
		int character = 0;
		int prevStyle = 0;
		for (int s = 0; s < text.length; s++) {
			for (int c = 0; c < text[s].length(); c++) {
				boolean bool = true;
				if (text[s].charAt(c) == '&' && c != text[s].length()-1) {
					bool = false;
					switch(text[s].charAt(c+1)) {
					case '0':
						prevStyle = 0;
						break;
					case '1':
						prevStyle = 1;
						break;
						
					case '2':
						prevStyle = 2;
						break;
					case 'n':
						style[c] += 10;
						break;
					case 't':
						style[c] += 11;
					default:
						bool = true;
					}
				}
				if (bool == false) {
					if (c+2 < text[s].length())
						text[s] = text[s].substring(0, c) + text[s].substring(c+2);
					else
						text[s] = text[s].substring(0,c);
					c--;
				} else {
					style[character] = prevStyle;
					character++;
				}
			}
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		processText();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		processText();
	}

	public int getMarginX() {
		return marginX;
	}

	public void setMarginX(int margin) {
		this.marginX = margin;
		processText();
	}
	
	public int getMarginY() {
		return marginX;
	}

	public void setMarginY(int margin) {
		this.marginY = margin;
		processText();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		processText();
	}

	public String getText() {
		return original;
	}

	public void setText(String text) {
		this.original = text;
		processText();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		processText();
	}

	public int getTextLength(String t) {
		int length = 0;
		for (char c : t.toCharArray()) {
			length += pf.getBufferedImage(c,0).getWidth() * size + size;
		}
		return length;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(bi,x,y,width,height,null);
	}
	
	public static void setPixelFont(PixelFont pff) {
		pf = pff;
	}

	public float getRelX() {
		return relX;
	}

	public void setRelX(float relX) {
		this.relX = relX;
	}

	public float getRelY() {
		return relY;
	}

	public void setRelY(float relY) {
		this.relY = relY;
	}

	public float getRelWidth() {
		return relWidth;
	}

	public void setRelWidth(float relWidth) {
		this.relWidth = relWidth;
	}

	public float getRelHeight() {
		return relHeight;
	}

	public void setRelHeight(float relHeight) {
		this.relHeight = relHeight;
	}

	public float getRelMarginX() {
		return relMarginX;
	}

	public void setRelMarginX(float relMarginX) {
		this.relMarginX = relMarginX;
	}

	public float getRelMarginY() {
		return relMarginY;
	}

	public void setRelMarginY(float relMarginY) {
		this.relMarginY = relMarginY;
	}

	public float getRelSizeX() {
		return relSizeX;
	}

	public void setRelSizeX(float relSizeX) {
		this.relSizeX = relSizeX;
	}

	public float getRelSizeY() {
		return relSizeY;
	}

	public void setRelSizeY(float relSizeY) {
		this.relSizeY = relSizeY;
	}
}
