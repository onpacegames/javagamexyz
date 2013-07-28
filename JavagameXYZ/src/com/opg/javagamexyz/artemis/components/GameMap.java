package com.opg.javagamexyz.artemis.components;

import com.artemis.Component;
import com.opg.javagamexyz.utils.HexMapGenerator;

public class GameMap extends Component {
	public int[][] map;
	public int width, height;
	
	public GameMap() {
		HexMapGenerator hmg = new HexMapGenerator();
		map = hmg.getDiamondSquare();
		width = map.length;
		height = map[0].length;
	}
}