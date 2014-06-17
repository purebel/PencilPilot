package com.jcui.pencilpilot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class HeroBullet extends GameObject {

	private Texture texture;
	private TextureRegion textRegion;
	private Animation animation;
	private Vector2 speed;

	public HeroBullet() {
		super();

		texture = new Texture(Gdx.files.internal("data/graphic/shoot.png"));
		textRegion = new TextureRegion(texture, 1004, 987, 9, 21);

		setWidth(9);
		setHeight(21);
		speed = new Vector2(0, GameConfig.getHeight() / 2f);
		
	}
	
	public void update(){
		stateTime += Gdx.graphics.getDeltaTime();
		if(stateTime > 10f)
		{
			stateTime = 0;
		}
		//translate(0, speed.y * Gdx.graphics.getDeltaTime());
//		System.out.println("stateTime=" + stateTime );
		translate(0, 5);
	}
	
	public void draw(SpriteBatch spriteBatch){
		spriteBatch.draw(textRegion, getX(), getY(), 0, 0, getWidth(), getHeight(), 1, 1, 0);
//		System.out.println("draw bullet at x:" + getX() + " y:" + getY());
	}
}
