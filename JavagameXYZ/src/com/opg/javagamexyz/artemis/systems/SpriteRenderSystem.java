package com.opg.javagamexyz.artemis.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.opg.javagamexyz.artemis.components.Position;
import com.opg.javagamexyz.artemis.components.Sprite;
import com.opg.javagamexyz.artemis.components.SpriteAnimation;

public class SpriteRenderSystem extends EntitySystem {
	@Mapper protected ComponentMapper<Position> pm;
	@Mapper protected ComponentMapper<Sprite> sm;
	@Mapper protected ComponentMapper<SpriteAnimation> sam;
	
	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	
	protected HashMap<String, Array<AtlasRegion>> atlasRegions;
	
	protected List<Entity> sortedEntities;
	
	@SuppressWarnings("unchecked")
	public SpriteRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(Position.class, Sprite.class));
		
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
		batch = new SpriteBatch();
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/characters.atlas"), Gdx.files.internal("textures"));
		atlasRegions = new HashMap<String, Array<AtlasRegion>>();
		for (AtlasRegion region : atlas.getRegions()) {
			if (!atlasRegions.containsKey(region.name)) {
				atlasRegions.put(region.name, new Array<AtlasRegion>());
			}
			atlasRegions.get(region.name).add(region);
		}
		
		sortedEntities = new ArrayList<Entity>();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
	
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (Entity entity : sortedEntities) {
			process(entity);
		}
	}
	
	@Override
	protected void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	protected void process(Entity e) {
		//if (pm.has(e)) {
			//Position position = pm.getSafe(e);
			Position position = pm.get(e);
			Sprite sprite = sm.get(e);
			
			float posX = 6f + (position.x + 0.5f) * 34 - sprite.region.getRegionWidth() / 2 * sprite.scaleX;
			float posY = 38 * (0.5f + 0.5f *(position.x % 2) + position.y) - sprite.region.getRegionHeight() / 2 * sprite.scaleY;
			
			batch.setColor(sprite.r, sprite.g, sprite.b, sprite.a);
			
			batch.draw(
				sprite.region,
				posX, posY,
				0, 0,
				sprite.region.getRegionWidth(), sprite.region.getRegionHeight(),
				sprite.scaleX, sprite.scaleY,
				sprite.rotation
			);
		//}
	}
	
	@Override
	protected void end() {
		batch.end();
	}
	
	@Override
	protected void inserted(Entity e) {
		Sprite sprite = sm.get(e);
		sortedEntities.add(e);
		
		// TODO this code can be moved into the Animation system with a resource/asset manager
		Array<AtlasRegion> spriteAtlasRegions = atlasRegions.get(sprite.name);
		
		sprite.region = spriteAtlasRegions.first();
		
		if (sam.has(e)) {
			SpriteAnimation anim = sam.get(e);
			anim.animation = new Animation(anim.frameDuration, spriteAtlasRegions, anim.playMode);
		}
		
		Collections.sort(sortedEntities, new Comparator<Entity>() {
			@Override
			public int compare(Entity e1, Entity e2) {
				Sprite s1 = sm.get(e1);
				Sprite s2 = sm.get(e2);
				return s1.layer.compareTo(s2.layer);
			}
		});
	}
	
	@Override
	protected void removed(Entity e) {
		sortedEntities.remove(e);
	}
}