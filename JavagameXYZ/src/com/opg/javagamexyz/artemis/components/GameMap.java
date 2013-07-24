package com.opg.javagamexyz.artemis.components;

import com.artemis.Component;

public class GameMap extends Component {
	public int[][] map;
	public int width, height;
	
	public GameMap() {
		map = new int[][] {
			{0, 1, 2, 3, 4, 5, 6, 7, 8 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{3, 3, 3, 3, 3, 3, 3, 3, 3 },
			{3, 3, 3, 3, 3, 3, 3, 3, 3 }
		};
		
		width = map.length;
		height = map[0].length;
	}
}