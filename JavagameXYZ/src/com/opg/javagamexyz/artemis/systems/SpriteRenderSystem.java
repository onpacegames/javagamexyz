package com.opg.javagamexyz.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.opg.javagamexyz.artemis.components.Position;
import com.opg.javagamexyz.artemis.components.Sprite;

public class SpriteRenderSystem extends EntitySystem {
	@Mapper protected ComponentMapper<Position> pm;
	@Mapper protected ComponentMapper<Sprite> sm;
	
	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	
	@SuppressWarnings("unchecked")
	public SpriteRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(Position.class, Sprite.class));
		
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
		batch = new SpriteBatch();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
	
	@Override
	protected void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}
	
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < entities.size(); i++) {
			process(entities.get(i));
		}
	}

	protected void process(Entity e) {
		// TODO is this necessary?
		if (pm.has(e)) {
			// TODO getSafe(...) necessary?
			Position position = pm.getSafe(e);
			Sprite sprite = sm.get(e);
			
			batch.setColor(sprite.color);
			
			batch.draw(sprite.sprite, position.x, position.y);
		}
	}
	
	@Override
	protected void end() {
		batch.end();
	}
}