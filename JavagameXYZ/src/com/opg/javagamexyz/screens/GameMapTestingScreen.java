package com.opg.javagamexyz.screens;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.opg.javagamexyz.JavagameXYZ;
import com.opg.javagamexyz.artemis.EntityFactory;
import com.opg.javagamexyz.artemis.systems.MapHudRenderSystem;
import com.opg.javagamexyz.artemis.systems.MapRenderSystem;
import com.opg.javagamexyz.artemis.systems.PlayerMapInputSystem;
import com.opg.javagamexyz.artemis.systems.SpriteRenderSystem;

public class GameMapTestingScreen extends JavagameXYZScreen {
	protected OrthographicCamera camera;
	
	protected World world;
	
	protected MapRenderSystem mapRenderSystem;
	protected SpriteRenderSystem spriteRenderSystem;
	protected MapHudRenderSystem mapHudRenderingSystem;
	
	public GameMapTestingScreen(Game game) {
		super(game);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, JavagameXYZ.Constants.Game.FRAME_WIDTH, JavagameXYZ.Constants.Game.FRAME_HEIGHT);
		
		world = new World();
		
		world.setManager(new GroupManager());
		
		mapRenderSystem = world.setSystem(new MapRenderSystem(camera), true);
		spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera), true);
		mapHudRenderingSystem = world.setSystem(new MapHudRenderSystem(), true);
		
		world.setSystem(new PlayerMapInputSystem(camera));
		
		world.initialize();
		
		EntityFactory.createGameMap(world).addToWorld();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		camera.update();
		
		world.setDelta(delta);
		world.process();
		
		mapRenderSystem.process();
		spriteRenderSystem.process();
		mapHudRenderingSystem.process();
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