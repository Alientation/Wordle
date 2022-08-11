package com.alientation.gui.util;

public class Util {

	/**
	 *
	 *
	 * @param n
	 * @param rel
	 * @return
	 */
	@Deprecated
	public static int convertFromRel(int n, float rel) {
		return (int) (n * rel);
	}

	/**
	 *
	 *
	 * @param n1
	 * @param n2
	 * @return
	 */
	@Deprecated
	public static float convertToRel(int n1, int n2) {
		return ((float) n1) / n2;
	}
	
}
