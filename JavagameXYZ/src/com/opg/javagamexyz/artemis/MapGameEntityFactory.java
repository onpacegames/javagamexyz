package com.opg.javagamexyz.artemis;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.opg.javagamexyz.artemis.components.GameMap;
import com.opg.javagamexyz.artemis.components.Player;
import com.opg.javagamexyz.artemis.components.Position;
import com.opg.javagamexyz.artemis.components.Sprite;
import com.opg.javagamexyz.artemis.components.SpriteAnimation;

public class MapGameEntityFactory {
	public static Entity createMap(World world) {
		Entity e = world.createEntity();
		
		e.addComponent(new GameMap());
		
		return e;
	}
	
	public static Entity createPlayer(World world, float x, float y) {
		Entity e = world.createEntity();
		
		e.addComponent(new Position(x, y));
		
		Sprite sprite = new Sprite("anim");
		sprite.r = 255/255f;
		sprite.g = 255/255f;
		sprite.b = 255/255f;
		sprite.a = 1.0f;
		sprite.rotation = 0f;
		sprite.scaleX = 1f;
		sprite.scaleY = 1f;
		e.addComponent(sprite);
		
		SpriteAnimation anim = new SpriteAnimation();
		anim.playMode = Animation.LOOP_PINGPONG;
		anim.frameDuration = 0.3f;
		anim.stateTime = 0f;
		e.addComponent(anim);
		
		e.addComponent(new Player());
		
		return e;
	}
	
	public static Entity createWarrior(World world, float x, float y) {
		Entity e = world.createEntity();
		
		e.addComponent(new Position(x, y));
		
		Sprite sprite = new Sprite("anim");
		sprite.r = 255/255f;
		sprite.g = 255/255f;
		sprite.b = 255/255f;
		sprite.a = 1.0f;
		sprite.rotation = 0f;
		sprite.scaleX = 1f;
		sprite.scaleY = 1f;
		e.addComponent(sprite);
		
		SpriteAnimation anim = new SpriteAnimation();
		anim.playMode = Animation.LOOP_PINGPONG;
		anim.frameDuration = 0.3f;
		anim.stateTime = 0f;
		e.addComponent(anim);
		
		return e;
	}
}