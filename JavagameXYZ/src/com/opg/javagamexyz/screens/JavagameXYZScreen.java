package com.opg.javagamexyz.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class JavagameXYZScreen implements Screen {
	protected Game game;
	
	public JavagameXYZScreen(Game game) {
		this.game = game;
	}
}