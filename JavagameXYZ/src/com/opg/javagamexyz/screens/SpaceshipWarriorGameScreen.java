package com.opg.javagamexyz.screens;

import com.artemis.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.opg.javagamexyz.artemis.EntityFactory;
import com.opg.javagamexyz.artemis.systems.ExpiringSystem;
import com.opg.javagamexyz.artemis.systems.MovementSystem;
import com.opg.javagamexyz.artemis.systems.PlayerInputSystem;
import com.opg.javagamexyz.artemis.systems.SpriteRenderSystem;

public class SpaceshipWarriorGameScreen extends JavaGameXYZScreen {
	protected OrthographicCamera camera;
	
	protected World world;
	
	protected SpriteRenderSystem spriteRenderSystem;
	
	public SpaceshipWarriorGameScreen(Game game) {
		super(game);
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		world = new World();
		
		spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera), true);
		
		world.setSystem(new PlayerInputSystem(camera));
		world.setSystem(new MovementSystem());
		world.setSystem(new ExpiringSystem());
		
		world.initialize();
		
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