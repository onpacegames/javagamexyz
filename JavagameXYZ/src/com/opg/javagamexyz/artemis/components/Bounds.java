package com.opg.javagamexyz.artemis.components;

import com.artemis.Component;

public class Bounds extends Component {
	public float radius;
	
	public Bounds() {
		this(0);
	}
	
	public Bounds(float radius) {
		this.radius = radius;
	}
}