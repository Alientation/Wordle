package com.alientation.wordle.util;

public class Util {
	
	public static int convertFromRel(int n, float rel) {
		return (int) (n * rel);
	}
	
	public static float convertToRel(int n1, int n2) {
		return ((float) n1) / n2;
	}
	
}
