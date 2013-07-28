package com.opg.javagamexyz.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.opg.javagamexyz.artemis.components.Player;
import com.opg.javagamexyz.artemis.components.Position;
import com.opg.javagamexyz.custom.Pair;
import com.opg.javagamexyz.utils.MapTools;

public class PlayerMapInputSystem extends EntityProcessingSystem implements InputProcessor {
	@Mapper protected ComponentMapper<Position> pm;
	
	protected OrthographicCamera camera;
	protected Vector3 mouseVector = new Vector3();
	protected Vector3 deltaVector = new Vector3();
	
	@SuppressWarnings("unchecked")
	public PlayerMapInputSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(Player.class));
		
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	protected void process(Entity e) {
		mouseVector.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouseVector);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// Get the hex cell being clicked
		Pair<Integer, Integer> coords = MapTools.window2world(Gdx.input.getX(), Gdx.input.getY(), camera);
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		deltaVector.set(-camera.zoom * Gdx.input.getDeltaX(), camera.zoom * Gdx.input.getDeltaY(), 0);
		camera.translate(deltaVector);
		
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if ((camera.zoom > 0.2f || amount == 1) && (camera.zoom < 8 || amount == -1)) {
			camera.zoom += amount * 0.1;
		}
		
		return true;
	}
}