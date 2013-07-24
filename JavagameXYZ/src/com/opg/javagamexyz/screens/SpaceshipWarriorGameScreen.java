package com.opg.javagamexyz.screens;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.opg.javagamexyz.JavagameXYZ;
import com.opg.javagamexyz.artemis.EntityFactory;
import com.opg.javagamexyz.artemis.systems.CollisionSystem;
import com.opg.javagamexyz.artemis.systems.ColorAnimationSystem;
import com.opg.javagamexyz.artemis.systems.EntitySpawningTimerSystem;
import com.opg.javagamexyz.artemis.systems.ExpiringSystem;
import com.opg.javagamexyz.artemis.systems.HealthRenderSystem;
import com.opg.javagamexyz.artemis.systems.HudRenderSystem;
import com.opg.javagamexyz.artemis.systems.MovementSystem;
import com.opg.javagamexyz.artemis.systems.ParallaxStarRepeatingSystem;
import com.opg.javagamexyz.artemis.systems.PlayerInputSystem;
import com.opg.javagamexyz.artemis.systems.ScaleAnimationSystem;
import com.opg.javagamexyz.artemis.systems.SpriteRenderSystem;

public class SpaceshipWarriorGameScreen extends JavaGameXYZScreen {
	protected OrthographicCamera camera;
	
	protected World world;
	
	protected SpriteRenderSystem spriteRenderSystem;
	protected HealthRenderSystem healthRenderingSystem;
	protected HudRenderSystem hudRenderingSystem;
	
	public SpaceshipWarriorGameScreen(Game game) {
		super(game);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, JavagameXYZ.Constants.Game.FRAME_WIDTH, JavagameXYZ.Constants.Game.FRAME_HEIGHT);
		
		world = new World();
		
		world.setManager(new GroupManager());
		
		spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera), true);
		healthRenderingSystem = world.setSystem(new HealthRenderSystem(camera), true);
		hudRenderingSystem = world.setSystem(new HudRenderSystem(camera), true);
		
		world.setSystem(new PlayerInputSystem(camera));
		world.setSystem(new MovementSystem());
		world.setSystem(new ExpiringSystem());
		world.setSystem(new EntitySpawningTimerSystem());
		world.setSystem(new CollisionSystem());
		world.setSystem(new ColorAnimationSystem());
		world.setSystem(new ScaleAnimationSystem());
		world.setSystem(new ParallaxStarRepeatingSystem());
		
		world.initialize();
		
		EntityFactory.createPlayer(world, 150, 150).addToWorld();
		
		for (int i = 0; i < 500; i++) {
			EntityFactory.createStar(world).addToWorld();
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		camera.update();
		
		world.setDelta(delta);
		world.process();
		
		spriteRenderSystem.process();
		healthRenderingSystem.process();
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