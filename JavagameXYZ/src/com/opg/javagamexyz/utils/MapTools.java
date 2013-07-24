package com.opg.javagamexyz.utils;

import com.opg.javagamexyz.custom.Pair;

public class MapTools {
	public static final int col_multiple = 34;
	public static final int row_multiple = 38;
	public static final String name = "hex";
	
	public static Pair[] getNeighbors(int x, int y, int n) {
		Pair[] coordinates = new Pair[3 * (n * n + n)];
		int i = 0;
		int min;
		for (int row = y - n; row < y + n + 1; row++) {
			min = MyMath.min(2 * (row - y + n), n, -2 * (row - y - n) + 1);
			for (int col = x - min; col < x + min + 1; col++) {
				if (x == col && y == row) {
					continue;
				} else if (x % 2 == 0) {
					coordinates[i] = new Pair(col, 2 * y - row);
				} else {
					coordinates[i] = new Pair(col, row);
				}
				i++;
			}
		}
		return coordinates;
	}
}