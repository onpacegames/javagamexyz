package com.opg.javagamexyz.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.opg.javagamexyz.artemis.components.Sprite;
import com.opg.javagamexyz.artemis.components.SpriteAnimation;

public class SpriteAnimationSystem extends EntityProcessingSystem {
	@Mapper protected ComponentMapper<Sprite> sm;
	@Mapper protected ComponentMapper<SpriteAnimation> sam;
	
	@SuppressWarnings("unchecked")
	public SpriteAnimationSystem() {
		super(Aspect.getAspectForAll(Sprite.class, SpriteAnimation.class));
	}

	@Override
	protected void process(Entity e) {
		Sprite sprite = sm.get(e);
		SpriteAnimation anim = sam.get(e);
		
		// TODO this continually accumulates, this isn't good!
		anim.stateTime += world.delta;
		
		// TODO set Region here instead of on the render system?
		TextureRegion region = anim.getFrame();
		sprite.x = region.getRegionX();
		sprite.y = region.getRegionY();
		sprite.width = region.getRegionWidth();
		sprite.height = region.getRegionHeight();
	}
}