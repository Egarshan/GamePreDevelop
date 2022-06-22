package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Menu.MenuLoader;

public class MyGdxGame extends Game {

	private static Pixmap cursor, cursorHand, cursorHammer, cursorHammerGlow, cursorKey, cursorKeyGlow;
	public static int WIDTH, HEIGHT;
	public static SpriteBatch batch;
	public static Data data;

	public static AudioPlayer audioPlayer;

	public static boolean curIsChanged, cursorIsItem;

	public static String currentCursor;
	
	@Override
	public void create () {
		Graphics.DisplayMode d = Gdx.graphics.getDisplayMode();
		Gdx.graphics.setTitle("На перепутье");
		WIDTH = d.width;
		HEIGHT = d.height;

		curIsChanged = false;
		cursorIsItem = false;

		Gdx.graphics.setFullscreenMode(d);
		Gdx.graphics.setResizable(false);
		
		batch = new SpriteBatch();
		data = new Data();
		if(!Data.getPrefs().getBoolean("launched")) {
			Data.firstLaunchSettings();
		}
		cursor = new Pixmap(Gdx.files.internal("обычный курсор.png"));
		cursorHand = new Pixmap(Gdx.files.internal("курсор взятия.png"));
		cursorHammer = new Pixmap(Gdx.files.internal("Inventory/Молоток_icon.gif"));
		cursorHammerGlow = new Pixmap(Gdx.files.internal("Inventory/Молоток_icon_glow.gif"));
		cursorKey = new Pixmap(Gdx.files.internal("Inventory/Ключ_icon.gif"));
		cursorKeyGlow = new Pixmap(Gdx.files.internal("Inventory/Ключ_icon_glow.gif"));

		Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursor, 1, 1));
		setScreen(new MenuLoader(this));

		audioPlayer = new AudioPlayer();

		//currentCursor = "simple";
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
		cursorHand.dispose();
		cursorHammer.dispose();
		this.screen.dispose();
	}

	public static void changeCursor (String name) {
		if (!curIsChanged) {
			curIsChanged = true;
			switch (name) {
				case "hand":
					Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorHand, 16, 16));
					currentCursor = "hand";
					break;
				case "hammer":
					Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorHammer, 64, 64));
					currentCursor = "hammer";
					break;
				case "hammerGlow":
					Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorHammerGlow, 64, 64));
					currentCursor = "hammerGlow";
					break;
				case "key":
					Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorKey, 64, 64));
					currentCursor = "key";
					break;
				case "keyGlow":
					Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorKeyGlow, 64, 64));
					currentCursor = "keyGlow";
					break;
			}
		}
		else {
			Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursor, 1, 1));
			currentCursor = "simple";
			curIsChanged = false;
			cursorIsItem = false;
		}
	}
}
