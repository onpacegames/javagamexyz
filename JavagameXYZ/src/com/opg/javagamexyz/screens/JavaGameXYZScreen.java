package com.opg.javagamexyz.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class JavaGameXYZScreen implements Screen {
	protected Game game;
	
	public JavaGameXYZScreen(Game game) {
		this.game = game;
	}
}