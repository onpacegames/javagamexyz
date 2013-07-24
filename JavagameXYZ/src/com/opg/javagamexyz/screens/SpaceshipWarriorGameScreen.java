package com.opg.javagamexyz.screens;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.opg.javagamexyz.artemis.components.Position;
import com.opg.javagamexyz.artemis.components.Sprite;
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
		
		world.initialize();
		
		Entity e = world.createEntity();
		e.addComponent(new Position(150, 150));
		e.addComponent(new Sprite());
		e.addToWorld();
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