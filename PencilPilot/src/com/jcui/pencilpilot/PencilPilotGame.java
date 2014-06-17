package com.jcui.pencilpilot;

import com.badlogic.gdx.Game;

public class PencilPilotGame extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

}
