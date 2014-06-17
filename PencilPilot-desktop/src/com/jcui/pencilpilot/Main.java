package com.jcui.pencilpilot;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "PencilPilot";
		cfg.useGL20 = false;
		// Set to half of 1280/720 (Sony M35t)
		char x = 'h';
		switch (x) {
		case 'h':
			cfg.width = 360;
			cfg.height = 640;
			break;
		case 'f':
			cfg.width = 480;
			cfg.height = 852;
			break;
		default:
			break;
		}

		new LwjglApplication(new PencilPilotGame(), cfg);
	}
}
