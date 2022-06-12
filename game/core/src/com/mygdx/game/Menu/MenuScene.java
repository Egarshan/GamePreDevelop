package com.mygdx.game.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Corridor.GameCamera;
import com.mygdx.game.MyGdxGame;

public class MenuScene implements Screen {

    private MenuCamera camera;
    private Viewport viewport;
    private MenuEventListener listener;
    private MenuBackground menuBackground;
    private MenuHud menuHud;
    private MenuFont menuFont;
    private MenuLoader menuLoader;
    private MyGdxGame game;

    public MenuScene(MyGdxGame game, MenuLoader menuLoader) {
        this.game = game;
        this.menuLoader = menuLoader;

        camera = new MenuCamera(MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        camera.position.set(MyGdxGame.WIDTH/2f, MyGdxGame.HEIGHT/2f, 0);

        viewport = new ExtendViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camera);

        menuFont = new MenuFont();
        menuBackground = new MenuBackground();
        menuHud = new MenuHud(menuFont);

        listener = new MenuEventListener(game, menuHud);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(listener);
    }

    @Override
    public void render(float delta) {
        MyGdxGame.batch.setProjectionMatrix(camera.combined);
        camera.update();

        camera.update(delta);

        menuBackground.update(delta);
        menuHud.update(delta);

        menuBackground.render();
        menuHud.render();
    }

    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        viewport.apply();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        dispose();
    }

    @Override
    public void dispose() {
        menuBackground.dispose();
        menuHud.dispose();
        menuLoader.dispose();
    }
}
