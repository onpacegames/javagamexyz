package com.opg.javagamexyz.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.opg.javagamexyz.artemis.components.Expires;

public class ExpiringSystem extends EntityProcessingSystem {
	@Mapper protected ComponentMapper<Expires> em;
	
	@SuppressWarnings("unchecked")
	public ExpiringSystem() {
		super(Aspect.getAspectForAll(Expires.class));
	}

	@Override
	protected void process(Entity e) {
		Expires expires = em.get(e);
		expires.delay -= world.delta;
		if (expires.delay <= 0) {
			e.deleteFromWorld();
		}
	}
}