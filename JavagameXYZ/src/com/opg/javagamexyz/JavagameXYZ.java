package com.opg.javagamexyz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.opg.javagamexyz.screens.SpaceshipWarriorGameScreen;

public class JavagameXYZ extends Game {
	@Override
	public void create() {
		setScreen(new SpaceshipWarriorGameScreen(this));
	}
	
	@Override
	public void render() {
		super.render();
		
		Gdx.graphics.setTitle(Constants.Game.TITLE + " - " + Gdx.graphics.getFramesPerSecond() + "fps");
	}
	
	public static class Constants {
		public static class Game {
			public static final int FRAME_WIDTH = 1280;
			public static final int FRAME_HEIGHT = 720;
			
			public static final String TITLE = "JavagameXYZ";
		}
	}
}