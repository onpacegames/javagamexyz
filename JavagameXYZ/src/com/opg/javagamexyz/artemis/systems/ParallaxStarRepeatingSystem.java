package com.opg.javagamexyz.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.opg.javagamexyz.JavagameXYZ.Constants;
import com.opg.javagamexyz.artemis.components.ParallaxStar;
import com.opg.javagamexyz.artemis.components.Position;

public class ParallaxStarRepeatingSystem extends IntervalEntityProcessingSystem {
	@Mapper protected ComponentMapper<Position> pm;

	@SuppressWarnings("unchecked")
	public ParallaxStarRepeatingSystem() {
		super(Aspect.getAspectForAll(ParallaxStar.class, Position.class), 1);
	}

	@Override
	protected void process(Entity e) {
		Position position = pm.get(e);

		if (position.y < 0) {
			position.y = Constants.Game.FRAME_HEIGHT;
		}
	}
}