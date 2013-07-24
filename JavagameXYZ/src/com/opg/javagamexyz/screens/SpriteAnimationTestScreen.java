package com.opg.javagamexyz.screens;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.opg.javagamexyz.JavagameXYZ;
import com.opg.javagamexyz.artemis.EntityFactory;
import com.opg.javagamexyz.artemis.systems.HudRenderSystem;
import com.opg.javagamexyz.artemis.systems.SpriteAnimationSystem;
import com.opg.javagamexyz.artemis.systems.SpriteRenderSystem;

public class SpriteAnimationTestScreen extends JavagameXYZScreen {
	protected OrthographicCamera camera;
	
	protected World world;
	
	protected SpriteRenderSystem spriteRenderSystem;
	protected HudRenderSystem hudRenderingSystem;
	
	public SpriteAnimationTestScreen(Game game) {
		super(game);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, JavagameXYZ.Constants.Game.FRAME_WIDTH, JavagameXYZ.Constants.Game.FRAME_HEIGHT);
		
		world = new World();
		
		world.setManager(new GroupManager());
		
		spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera), true);
		hudRenderingSystem = world.setSystem(new HudRenderSystem(camera), true);
		
		world.setSystem(new SpriteAnimationSystem());
		
		world.initialize();
		
		for (int i = 0; i < 50; i++) {
			EntityFactory.createClaude(world, MathUtils.random(0, JavagameXYZ.Constants.Game.FRAME_WIDTH), MathUtils.random(0, JavagameXYZ.Constants.Game.FRAME_HEIGHT)).addToWorld();
		}
		
		EntityFactory.createPlayer(world, 150, 150).addToWorld();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		camera.update();
		
		world.setDelta(delta);
		world.process();
		
		spriteRenderSystem.process();
		hudRenderingSystem.process();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}