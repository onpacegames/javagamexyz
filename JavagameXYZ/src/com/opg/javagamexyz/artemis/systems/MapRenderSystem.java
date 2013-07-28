package com.opg.javagamexyz.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.opg.javagamexyz.artemis.components.GameMap;
import com.opg.javagamexyz.utils.MapTools;

public class MapRenderSystem extends EntityProcessingSystem {
	@Mapper protected ComponentMapper<GameMap> gm;
	
	protected SpriteBatch batch;
	protected Array<AtlasRegion> textures;
	
	protected OrthographicCamera camera;
	
	@SuppressWarnings("unchecked")
	public MapRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(GameMap.class));
		
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
		batch = new SpriteBatch();
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/maptiles.atlas"), Gdx.files.internal("textures"));
		textures = atlas.findRegions(MapTools.name);
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
	protected void process(Entity e) {
		GameMap gameMap = gm.get(e);
		TextureRegion reg;
		int x, y;
		
		int x0 = 0;
		int x1 = gameMap.width;
		
		int y0 = 0;
		int y1 = gameMap.height;
		
		// Loop over everything in the window to draw
		// Because I am drawing a hexmap tile, I chose to do 2 columns at once. As such, I had to stop shy of the far right column, because col+1 would break for it.
		// Thus we do that final column separately.
		for (int row = y0; row < y1; row++) {
			for (int col = x0; col < x1 - 1; col += 2) {
				x = col * MapTools.col_multiple;
				y = row * MapTools.row_multiple;
				reg = textures.get(gameMap.map[col][row]);
				batch.draw(
					reg,
					x, y,
					0, 0,
					reg.getRegionWidth(), reg.getRegionHeight(),
					1, 1,
					0
				);
				
				x += MapTools.col_multiple;
				y += MapTools.row_multiple / 2;
				reg = textures.get(gameMap.map[col][row]);
				batch.draw(
					reg,
					x, y,
					0, 0,
					reg.getRegionWidth(), reg.getRegionHeight(),
					1, 1,
					0
				);
			}
			if (x1 >= gameMap.width) {
				int col = gameMap.width - 1;
				x = col * MapTools.col_multiple;
				y = row * MapTools.row_multiple;
				reg = textures.get(gameMap.map[col][row]);
				batch.draw(
					reg,
					x, y,
					0, 0,
					reg.getRegionWidth(), reg.getRegionHeight(),
					1, 1,
					0
				);
			}
		}
	}
	
	@Override
	protected void end() {
		batch.end();
	}
}