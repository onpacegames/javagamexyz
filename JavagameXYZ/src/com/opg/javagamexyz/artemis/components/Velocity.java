package com.opg.javagamexyz.artemis.components;

import com.artemis.Component;

public class Velocity extends Component {
	public float vx, vy;
	
	public Velocity() {
		this(0, 0);
	}
	
	public Velocity(float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
	}
}