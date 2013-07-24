package com.opg.javagamexyz.artemis.components;

import com.artemis.Component;

public class Position extends Component {
	public float x, y;
	
	public Position() {
		this(0, 0);
	}
	
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
}