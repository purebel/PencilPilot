package com.jcui.pencilpilot;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class GameScreen implements Screen, InputProcessor {
	private Game game;

	private int screenWidth, screenHeight;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture textBG, textShoot;
	private TextureRegion textRegBG;
	private TextureRegion textRegPauseNor, textRegBullet;

	// Basic UI widgets:Image, Label, List, SelectBox, Slider, TextField,
	// Touchpad
	private Image imageBG1, imageBG2, imagePause;
	private Label labelScore;

	private BitmapFont font;
	private int score;

	private float timer;
	private Stage mStage;
	private SpriteBatch spriteBatch;

	private HeroActor hero;
	private final Pool<HeroBullet> heroBulletPool;
	private HeroBullet heroBullet;

	private Array<HeroBullet> heroBulletArray;

	public GameScreen(Game game) {
		super();
		this.game = game;
		spriteBatch = new SpriteBatch();

		Gdx.input.setInputProcessor(this);

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(GameConfig.getWidth(),
				GameConfig.getHeight());
		camera.position.set(GameConfig.getWidth() / 2,
				GameConfig.getHeight() / 2, 0);

		// Textures
		textBG = new Texture(
				Gdx.files.internal("data/graphic/shoot_background.png"));
		textShoot = new Texture(Gdx.files.internal("data/graphic/shoot.png"));
		// Font
		font = new BitmapFont(Gdx.files.internal("data/font/font.fnt"),
				Gdx.files.internal("data/font/font.png"), false);
		font.setColor(0.5f, 0.5f, 0.5f, 1);
		font.setScale(1.0f);

		// Texture Regions
		textRegBG = new TextureRegion(textBG, 0, 75, 480, 852);

		textRegPauseNor = new TextureRegion(textShoot, 267, 251, 60, 45);

		textRegBullet = new TextureRegion(textShoot, 1004, 987, 9, 21);

		mStage = new Stage(screenWidth, screenHeight);
		mStage.addActor(new BackGroundActor());
		imagePause = new Image(textRegPauseNor);
		imagePause.setPosition(0, Gdx.graphics.getHeight() - 50);
		mStage.addActor(imagePause);

		labelScore = new Label("000", new Label.LabelStyle(font, Color.GRAY));
		labelScore.setPosition(60, Gdx.graphics.getHeight() - 50);
		mStage.addActor(labelScore);

		hero = new HeroActor(this);
		heroBullet = new HeroBullet();
		hero.setPosition(-hero.getWidth() / 2, -Gdx.graphics.getHeight() / 2);
		heroBulletPool = new Pool<HeroBullet>(20, 20) {
			@Override
			protected HeroBullet newObject() {
				return new HeroBullet();
			}
		};
		// heroBulletArray = Pools.makeArrayFromPool(heroBulletArray,
		// heroBulletPool, 10);
		if (heroBulletArray == null) {
			HeroBullet bullet = heroBulletPool.obtain();
			heroBulletArray = new Array<HeroBullet>(false, 20,
					bullet.getClass());
		}
//		System.out.println((Gdx.graphics.getWidth() - hero.getWidth()) / 2f
//				+ " " + Gdx.graphics.getHeight() / 4f);

	}

	@Override
	public void render(float delta) {
		timer = Gdx.graphics.getDeltaTime();

		mStage.act(delta);
		mStage.draw();
		update(delta);
		draw(delta);

	}

	private void update(float delta) {
		spriteBatch.setProjectionMatrix(camera.combined);
		hero.update();
		for (HeroBullet bullet : heroBulletArray) {
			bullet.update();
		}
	}

	private void draw(float delta) {
		spriteBatch.begin();
		hero.draw(spriteBatch);
		// heroBullet.draw(spriteBatch);
		for (HeroBullet bullet : heroBulletArray) {
			bullet.draw(spriteBatch);
		}
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
		textBG.dispose();
		textShoot.dispose();
		font.dispose();
		mStage.dispose();
	}

	public void addHeroBullet(float x, float y) {
		if (heroBulletArray.size < 10) {
			HeroBullet heroBullet = heroBulletPool.obtain();
			heroBullet.setPosition(x - heroBullet.getWidth()/2, y);
			heroBulletArray.add(heroBullet);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case 19://UP
			hero.translate(0, 10);
			break;
		case 20://DOWN
			hero.translate(0, -10);
			break;
		case 21://LEFT
			hero.translate(-10, 0);
			break;
		case 22://RIGHT
			hero.translate(10, 0);
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
