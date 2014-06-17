package com.jcui.pencilpilot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BackGroundActor extends Actor {
	private Texture textBG;
	private TextureRegion textRegBG;
	private float timer;

	// Constants
	private int SPEED_FACTOR_BG = 20;

	public BackGroundActor() {
		textBG = new Texture(
				Gdx.files.internal("data/graphic/shoot_background.png"));
		textRegBG = new TextureRegion(textBG, 0, 75, 480, 852);
		// backgroundActor.setPosition(Gdx.graphics.getWidth() / 2 -
		// backgroundActor.getWidth() / 2, 20);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		timer += Gdx.graphics.getDeltaTime();
		batch.draw(textRegBG, 0, -timer * SPEED_FACTOR_BG % 852, 480, 852, 480,
				852, 1, 1, 0);
		batch.draw(textRegBG, 0, 852 - timer * SPEED_FACTOR_BG % 852, 480, 852,
				480, 852, 1, 1, 0);
	}
}
