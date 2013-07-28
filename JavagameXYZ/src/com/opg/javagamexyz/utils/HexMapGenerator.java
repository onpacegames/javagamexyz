package com.opg.javagamexyz.utils;

public class HexMapGenerator {
	public HexMapGenerator() {
	}
	
	public int[][] getDiamondSquare() {
		MidpointDisplacement md = new MidpointDisplacement();
		return md.getMap();
	}
}