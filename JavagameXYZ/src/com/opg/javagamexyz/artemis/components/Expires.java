package com.opg.javagamexyz.artemis.components;

import com.artemis.Component;

public class Expires extends Component {
	public float delay;
	
	public Expires() {
		this(0);
	}
	
	public Expires(float delay) {
		this.delay = delay;
	}
}