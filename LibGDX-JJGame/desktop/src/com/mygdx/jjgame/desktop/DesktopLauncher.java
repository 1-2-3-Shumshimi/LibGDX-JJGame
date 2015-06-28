package com.mygdx.jjgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.jjgame.JJGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "JJGAME";
		config.width = 1080;
		config.height = 720;
		new LwjglApplication(new JJGame(), config);
	}
}
