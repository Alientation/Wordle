package com.alientation.wordle.graphics.font;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Font {
	
	public BufferedImage[][] bis;
	public BufferedImage[] characters;
	public BufferedImage[] bi = new BufferedImage[3];
	public Font(String path1, String path2, String path3, int numFonts) {
		try {
			bi[0] = ImageIO.read(new File(path1));
			bi[1] = ImageIO.read(new File(path2));
			//bi[2] = ImageIO.read(new File(path3));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bis = new BufferedImage[3][numFonts];
	}
	
	public void loadImages(BufferedImage bi) {
		
	}
	
	public BufferedImage[][] getBufferedImages() { return bis; }
	public void drawFont(int ID, int style, int x, int y, Color color, int size, Graphics g) { 
		g.drawImage(bis[style][ID], x, y, size * (bis[style][ID].getWidth()), size * (bis[style][ID].getHeight()),null);
	}
}
