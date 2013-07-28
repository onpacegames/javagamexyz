package com.opg.javagamexyz.utils;

public class MyMath {
	public static int min(int a, int b) {
		if (a < b) {
			return a;
		}
		return b;
	}
	
	public static int min(int a, int b, int c) {
		int minAB = min(a, b);
		if (minAB < c) {
			return minAB;
		}
		return c;
	}
	
	public static int max(int a, int b) {
		if (a > b) {
			return a;
		}
		return b;
	}
	
	public static int pow(int a, int b) {
		if (b > 1) {
			return a * pow(a, b - 1);
		}
		return a;
	}
}