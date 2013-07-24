package com.opg.javagamexyz.artemis.systems;

import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.Timer;
import com.badlogic.gdx.math.MathUtils;
import com.opg.javagamexyz.JavagameXYZ;
import com.opg.javagamexyz.artemis.EntityFactory;
import com.opg.javagamexyz.artemis.components.Sprite.Layer;

public class EntitySpawningTimerSystem extends VoidEntitySystem {
	protected Timer timer1;
	protected Timer timer2;
	protected Timer timer3;
	
	public EntitySpawningTimerSystem() {
		timer1 = new Timer(2, true) {
			@SuppressWarnings("synthetic-access")
			@Override
			public void execute() {
				EntityFactory.createEnemyShip(
					world,
					"enemy1",
					Layer.ACTORS_3,
					10,
					MathUtils.random(0, JavagameXYZ.Constants.Game.FRAME_WIDTH), JavagameXYZ.Constants.Game.FRAME_HEIGHT + 50,
					0, -40,
					20
				).addToWorld();
			}
		};
		
		timer2 = new Timer(6, true) {
			@SuppressWarnings("synthetic-access")
			@Override
			public void execute() {
				EntityFactory.createEnemyShip(
					world,
					"enemy2",
					Layer.ACTORS_2,
					20,
					MathUtils.random(0, JavagameXYZ.Constants.Game.FRAME_WIDTH), JavagameXYZ.Constants.Game.FRAME_HEIGHT + 100,
					0, -30,
					40
				).addToWorld();
			}
		};
		
		timer3 = new Timer(12, true) {
			@SuppressWarnings("synthetic-access")
			@Override
			public void execute() {
				EntityFactory.createEnemyShip(
					world,
					"enemy3",
					Layer.ACTORS_1,
					60,
					MathUtils.random(0, JavagameXYZ.Constants.Game.FRAME_WIDTH), JavagameXYZ.Constants.Game.FRAME_HEIGHT + 200,
					0, -20,
					70
				).addToWorld();
			}
		};
	}
	
	@Override
	protected void processSystem() {
		timer1.update(world.delta);
		timer2.update(world.delta);
		timer3.update(world.delta);
	}
}