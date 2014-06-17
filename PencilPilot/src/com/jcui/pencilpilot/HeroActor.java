package com.jcui.pencilpilot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HeroActor extends GameObject {

	private GameScreen screen;
	private Texture textShoot;
	private Animation animationHero;
	private Sprite spriteHero;
	private float timer = 0;
	private int state = STATE_NORMAL;

	public HeroActor(GameScreen screen) {
		this.screen = screen;
		textShoot = new Texture(Gdx.files.internal("data/graphic/shoot.png"));
		TextureRegion[] textRegionArray = {
				new TextureRegion(textShoot, 0, 99, 102, 126),
				new TextureRegion(textShoot, 165, 360, 102, 126) };
		animationHero = new Animation(0.2f, textRegionArray);
		animationHero.setPlayMode(Animation.LOOP);
		// spriteHero = new Sprite(new TextureRegion(textShoot, 0, 99, 102,
		// 126));
		// setPosition(Gdx.graphics.getWidth() / 2 - getWidth() / 2, 20);
		setWidth(102);
		setHeight(126);
		// spriteHero.setPosition(Gdx.graphics.getWidth() / 2 -
		// spriteHero.getWidth() / 2, 20);
	}

	public void update() {
		stateTime += Gdx.graphics.getDeltaTime();
		if (stateTime > 0.25f) {
			stateTime = 0;
			shoot();
		}
	}

	private void shoot() {
		// screen.addHeroBullet(0, 0);
		screen.addHeroBullet(getX() + getWidth() / 2, getY() + getHeight());

	}

	public void draw(SpriteBatch batch) {
		timer += Gdx.graphics.getDeltaTime();
		if (state == STATE_NORMAL) {
			TextureRegion keyFrame = animationHero.getKeyFrame(timer);
			batch.draw(keyFrame, getX(), getY(), 0, 0, getWidth(), getHeight(),
					1, 1, 0);
		} else {
		}
	}

	@Override
	public void translate(float offsetX, float offsetY) {
		if (this.x + offsetX < -(GameConfig.getWidth() + this.getWidth()) / 2)
			this.x = -(GameConfig.getWidth() + this.getWidth()) / 2;
		else if (this.x + offsetX > (GameConfig.getWidth() - this.getWidth()) / 2)
			this.x = (GameConfig.getWidth() - this.getWidth()) / 2;
		else
			this.x += offsetX;
		this.y += offsetY;
		System.out.println("The new hero X:" + this.x + " Y:" + this.y);
	}
}
