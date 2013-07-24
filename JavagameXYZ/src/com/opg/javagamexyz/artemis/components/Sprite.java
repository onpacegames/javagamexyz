package com.opg.javagamexyz.artemis.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite extends Component {
	public enum Layer {
		DEFAULT,
		BACKGROUND,
		ACTORS_1,
		ACTORS_2,
		ACTORS_3,
		PARTICLES;
		
		public int getLayerId() {
			return ordinal();
		}
	}
	
	public String name;
	public  TextureRegion region;
	public float r, g, b, a;
	public float scaleX, scaleY;
	public float rotation;
	public Layer layer;
	public int x, y;
	public int width, height;
	
	public Sprite() {
		this("default", Layer.DEFAULT);
	}
	
	public Sprite(String name) {
		this(name, Layer.DEFAULT);
	}
	
	public Sprite(String name, Layer layer) {
		this.name = name;
		this.layer = layer;
		
		r = g = b = a = 1.0f;
		scaleX = scaleY = 1.0f;
		rotation = 0;
	}
}