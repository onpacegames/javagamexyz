package com.opg.javagamexyz.artemis.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

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
	public Color color;
	public Vector2 scale;
	public float rotation;
	public Layer layer;
	
	public Sprite() {
		this("default", Layer.DEFAULT);
	}
	
	public Sprite(String name) {
		this(name, Layer.DEFAULT);
	}
	
	public Sprite(String name, Layer layer) {
		this.name = name;
		this.layer = layer;
		
		color = Color.WHITE;
		scale = new Vector2(1, 1);
		rotation = 0;
	}
}