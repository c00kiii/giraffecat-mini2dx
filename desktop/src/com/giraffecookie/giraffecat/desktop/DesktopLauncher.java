package com.giraffecookie.giraffecat.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.giraffecookie.giraffecat.GiraffeCatGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(GiraffeCatGame.GAME_IDENTIFIER);
		config.vSyncEnabled = false;
		config.fullscreen = false;
		config.width = 640;
		config.height = 360;

		new DesktopMini2DxGame(new GiraffeCatGame(), config);
	}
}
