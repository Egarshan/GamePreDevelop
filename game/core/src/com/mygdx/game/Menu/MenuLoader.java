package com.mygdx.game.Menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ResourcesClass;
import com.mygdx.game.MyGdxGame;

public class MenuLoader implements Screen {

    private AssetManager manager;
    private MyGdxGame game;
    private TextureRegion[] background;
    private TextureRegion[] menuHud;

    public MenuLoader(MyGdxGame game) {
        this.game = game;
        manager = new AssetManager();
        ResourcesClass.disposeResources();

        manager.load("фон_меню.png", Texture.class);
        manager.load("menu.png", Texture.class);
    }

    private void unpackBackground() {
        background = new TextureRegion[1];
        background[0] = new TextureRegion((Texture) manager.get("фон_меню.png"));
    }

    private void unpackMenuHud() {
        menuHud = new TextureRegion[] {
                new TextureRegion((Texture) manager.get("menu.png"), 1, 118, 760, 102),
                new TextureRegion((Texture) manager.get("menu.png"), 1, 1, 451, 115),
                new TextureRegion((Texture) manager.get("menu.png"), 454, 35, 406, 81)
        };
    }

    private void unpackAll() {
        unpackBackground();
        unpackMenuHud();
        ResourcesClass.addResources(background, menuHud);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(manager.update()) {
            unpackAll();
            game.setScreen(new MenuScene(game, this));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
