package com.opg.javagamexyz.utils;

public class HexMapGenerator {
	public static final int N = 5;
	public static final int WMULT = 5;
	public static final int HMULT = 3;
	
	public HexMapGenerator() {
	}
	
	public int[][] getDiamondSquare() {
		MidpointDisplacement md = new MidpointDisplacement();
		return md.getMap(N, WMULT, HMULT);
	}
}