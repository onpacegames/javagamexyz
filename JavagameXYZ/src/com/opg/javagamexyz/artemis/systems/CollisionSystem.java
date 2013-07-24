package com.opg.javagamexyz.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.Utils;
import com.opg.javagamexyz.JavagameXYZ.Constants;
import com.opg.javagamexyz.artemis.components.Bounds;
import com.opg.javagamexyz.artemis.components.Health;
import com.opg.javagamexyz.artemis.components.Position;

public class CollisionSystem extends EntitySystem {
	@Mapper protected ComponentMapper<Position> pm;
	@Mapper protected ComponentMapper<Bounds> bm;
	@Mapper protected ComponentMapper<Health> hm;
	
	protected Bag<CollisionPair> collisionPairs;
	
	@SuppressWarnings("unchecked")
	public CollisionSystem() {
		super(Aspect.getAspectForAll(Position.class, Bounds.class));
	}
	
	@Override
	protected void initialize() {
		collisionPairs = new Bag<CollisionPair>();
		
		collisionPairs.add(new CollisionPair(Constants.Groups.PLAYER_BULLETS, Constants.Groups.ENEMY_SHIPS, new CollisionHandler() {
			@Override
			public void handleCollision(Entity bullet, Entity enemyShip) {
				Health health = hm.get(enemyShip);
				health.health -= 10;
				
				bullet.deleteFromWorld();
				
				if (health.health <= 0) {
					enemyShip.deleteFromWorld();
				}
			}
		}));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < collisionPairs.size(); i++) {
			collisionPairs.get(i).checkForCollisions();
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	protected class CollisionPair {
		protected ImmutableBag<Entity> groupEntitiesA;
		protected ImmutableBag<Entity> groupEntitiesB;
		protected CollisionHandler handler;
		
		@SuppressWarnings("synthetic-access")
		public CollisionPair(String group1, String group2, CollisionHandler handler) {
			groupEntitiesA = world.getManager(GroupManager.class).getEntities(group1);
			groupEntitiesB = world.getManager(GroupManager.class).getEntities(group2);
			this.handler = handler;
		}
		
		public void checkForCollisions() {
			for (int a = 0; a < groupEntitiesA.size(); a++) {
				Entity entityA = groupEntitiesA.get(a);
				for (int b = 0; b < groupEntitiesB.size(); b++) {
					Entity entityB = groupEntitiesB.get(b);
					if (collisionExists(entityA, entityB)) {
						handler.handleCollision(entityA, entityB);
					}
				}
			}
		}
		
		protected boolean collisionExists(Entity e1, Entity e2) {
			Position p1 = pm.get(e1);
			Position p2 = pm.get(e2);
			
			Bounds b1 = bm.get(e1);
			Bounds b2 = bm.get(e2);
			
			return Utils.doCirclesCollide(p1.x, p1.y, b1.radius, p2.x, p2.y, b2.radius);
			// return Utils.distance(p1.x, p1.y, p2.x, p2.y) - b1.radius < b2.radius;
		}
	}
	
	protected interface CollisionHandler {
		public void handleCollision(Entity a, Entity b);
	}
}