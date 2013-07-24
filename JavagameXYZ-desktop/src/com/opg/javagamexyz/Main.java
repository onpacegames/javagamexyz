package com.opg.javagamexyz;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.opg.javagamexyz.utils.ImagePacker;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ImagePacker.run();
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = JavagameXYZ.Constants.Game.TITLE;
		cfg.useGL20 = true;
		cfg.resizable = false;
		cfg.fullscreen = false;
		cfg.width = JavagameXYZ.Constants.Game.FRAME_WIDTH;
		cfg.height = JavagameXYZ.Constants.Game.FRAME_HEIGHT;
		
		new LwjglApplication(new JavagameXYZ(), cfg);
	}
}