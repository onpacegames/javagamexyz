package com.opg.javagamexyz.utils;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class ImagePacker {
	public static void run() {
		Settings settings = new Settings();
		settings.filterMin = TextureFilter.Linear;
		settings.filterMag = TextureFilter.Linear;
		settings.pot = false;
		TexturePacker2.process(settings, "../JavagameXYZ-android/assets/textures-original", "../JavagameXYZ-android/assets/textures", "pack");
	}
}