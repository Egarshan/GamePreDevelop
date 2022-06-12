package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Menu.MenuLoader;

public class MyGdxGame extends Game {

	private static Pixmap cursor, cursorHand;
	public static int WIDTH, HEIGHT;
	public static SpriteBatch batch;
	public static Data data;

	public static boolean curIsHand;

	private Music musicBackground;
	
	@Override
	public void create () {
		Graphics.DisplayMode d = Gdx.graphics.getDisplayMode();
		Gdx.graphics.setTitle("На перепутье");
		WIDTH = d.width;
		HEIGHT = d.height;

		curIsHand = false;

		//Gdx.graphics.setFullscreenMode(d);
		//Gdx.graphics.setResizable(false);
		
		batch = new SpriteBatch();
		data = new Data();
		if(!Data.getPrefs().getBoolean("launched")) {
			Data.firstLaunchSettings();
		}
		cursor = new Pixmap(Gdx.files.internal("обычный курсор.png"));
		cursorHand = new Pixmap(Gdx.files.internal("курсор взятия.png"));

		Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursor, 1, 1));
		setScreen(new MenuLoader(this));

		musicBackground = Gdx.audio.newMusic(Gdx.files.internal("sounds/background_music.mp3"));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();
		super.render();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		cursor.dispose();
		this.screen.dispose();
	}

	public static void changeCursor () {
		if (curIsHand){
			Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursor, 1, 1));
			curIsHand = false;
		}
		else {
			Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorHand, 1, 1));
			curIsHand = true;
		}
	}

	public Music getMusicBackground() {
		return musicBackground;
	}
}
