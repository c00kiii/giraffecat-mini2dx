package com.giraffecookie.giraffecat.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.giraffecookie.giraffecat.GiraffeCatGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(GiraffeCatGame.GAME_IDENTIFIER);
		config.vSyncEnabled = false;
		config.fullscreen = false;
		config.width = 1600;
		config.height = 900;

		new DesktopMini2DxGame(new GiraffeCatGame(), config);
	}
}
