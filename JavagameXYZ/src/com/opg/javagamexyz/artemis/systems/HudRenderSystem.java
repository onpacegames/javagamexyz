package com.opg.javagamexyz.artemis.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.Mapper;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.opg.javagamexyz.JavagameXYZ.Constants;
import com.opg.javagamexyz.artemis.components.Position;
import com.opg.javagamexyz.artemis.components.Sprite;

public class HudRenderSystem extends VoidEntitySystem {
	@Mapper protected ComponentMapper<Position> pm;
	@Mapper protected ComponentMapper<Sprite> sm;
 
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font;

	public HudRenderSystem(OrthographicCamera camera) {
		this.camera = camera;
	}

	@Override
	protected void initialize() {
		batch = new SpriteBatch();

		Texture fontTexture = new Texture(Gdx.files.internal("fonts/normal_0.png"));
		fontTexture.setFilter(TextureFilter.Linear, TextureFilter.MipMapLinearLinear);
		TextureRegion fontRegion = new TextureRegion(fontTexture);
		font = new BitmapFont(Gdx.files.internal("fonts/normal.fnt"), fontRegion, false);
		font.setUseIntegerPositions(false);
	}

	@Override
	protected void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	@Override
	protected void processSystem() {
		batch.setColor(1, 1, 1, 1);
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 20, Constants.Game.FRAME_HEIGHT - 20);
		font.draw(batch, "Active entities: " + world.getEntityManager().getActiveEntityCount(), 20, Constants.Game.FRAME_HEIGHT - 40);
		font.draw(batch, "Total created: " + world.getEntityManager().getTotalCreated(), 20, Constants.Game.FRAME_HEIGHT - 60);
		font.draw(batch, "Total deleted: " + world.getEntityManager().getTotalDeleted(), 20, Constants.Game.FRAME_HEIGHT - 80);
	}
	
	@Override
	protected void end() {
		batch.end();
	}
}