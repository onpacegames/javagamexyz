package com.opg.javagamexyz.artemis;

import com.artemis.Entity;
import com.artemis.World;
import com.opg.javagamexyz.artemis.components.Expires;
import com.opg.javagamexyz.artemis.components.Player;
import com.opg.javagamexyz.artemis.components.Position;
import com.opg.javagamexyz.artemis.components.Sprite;
import com.opg.javagamexyz.artemis.components.Velocity;

public class EntityFactory {
	public static Entity createPlayer(World world, float x, float y) {
		Entity e = world.createEntity();
		
		e.addComponent(new Position(x, y));
		e.addComponent(new Sprite("textures-original/fighter.png"));
		e.addComponent(new Velocity());
		e.addComponent(new Player());
		
		return e;
	}
	
	public static Entity createBullet(World world, float x, float y) {
		Entity e = world.createEntity();
		
		e.addComponent(new Position(x, y));
		e.addComponent(new Sprite("textures-original/bullet.png"));
		e.addComponent(new Velocity(0, 200));
		e.addComponent(new Expires(1.0f));
		
		return e;
	}
}