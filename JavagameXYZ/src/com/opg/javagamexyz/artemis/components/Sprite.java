package com.opg.javagamexyz.artemis.components;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Sprite extends Component {
	public Texture sprite;
	public Color color;
	public Vector2 scale;
	public float rotation;
	
	public Sprite() {
		this("textures-original/fighter.png");
	}
	
	public Sprite(String path) {
		sprite = new Texture(Gdx.files.internal(path));
		
		color = Color.WHITE;
		scale = new Vector2(1, 1);
		rotation = 0;
	}
}