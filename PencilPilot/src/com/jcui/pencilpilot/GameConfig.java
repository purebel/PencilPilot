/**
 * 
 */
package com.jcui.pencilpilot;

import com.badlogic.gdx.Gdx;

/**
 * @author jcui
 * 
 */
public class GameConfig {
	private static GameConfig singleton = null;
	private static int width;
	private static int height;

	private GameConfig() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	public static int getWidth() {
		if (singleton == null)
			new GameConfig();
		return width;
	}

	public static int getHeight() {
		if (singleton == null)
			new GameConfig();
		return height;
	}

}
