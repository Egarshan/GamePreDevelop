package com.mygdx.game.Corridor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.*;

public class CorridorScene implements Screen {

    private GameCamera camera;
    private static Viewport viewport1;
    private CorridorEventListener listener;
    private CorridorBackground corridorBackground;
    protected NoiseBackground noiseBackground;
    private TextureRegion backgroundNoise;

    private Player player;
    private Diary diary;
    private Item[] items;
    private Hud hud;

    private ExitConfirm exitConfirm;

    private Dialogs dialogs;
    private boolean cameraMoveRight;
    private CorridorLoader corridorLoader;
    private Reactions reactions;
    private MyGdxGame game;

    private Inventory inventory;

    public CorridorScene(MyGdxGame game, CorridorLoader corridorLoader) {
        this.game = game;
        this.corridorLoader = corridorLoader;
        corridorBackground = new CorridorBackground();
        noiseBackground = new NoiseBackground();
        camera = new GameCamera(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, corridorBackground);


        viewport1 = new ExtendViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camera);

        diary = new Diary(camera);
        items = new Item[7];
        items[5] = new Hammer();
        items[4] = new Photo();
        items[1] = new DoorOpen();
        items[0] = new DoorClose();
        items[2] = new RubbishBin();
        items[3] = new Flowers();
        items[6] = new AngerMask();

        inventory = new Inventory(camera, this);

        reactions = new Reactions(camera);

        player = new Player(this, camera, (RubbishBin)items[2], (Flowers) items[3], reactions);

        hud = new Hud(camera, player);

        dialogs = new Dialogs(camera);

        exitConfirm = new ExitConfirm(game, camera);

        listener = new CorridorEventListener(game, this, player, diary, hud, exitConfirm, inventory);

        cameraMoveRight = false;

        game.getMusicBackground().setLooping(true);
        game.getMusicBackground().play();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(listener);
    }

    @Override
    public void render(float delta) {
        MyGdxGame.batch.setProjectionMatrix(camera.combined);
        camera.update();

        reactions.update(delta);

        corridorBackground.update(delta);
        for(Item i : items) {
            i.update(delta);
        }
        player.update(delta);
        camera.update(delta);
        hud.update(delta);
        diary.update(delta);
        inventory.update(delta);
        dialogs.update();
        for(Item i : items) {
            i.update(delta);
        }
        exitConfirm.update(delta);
        if(diary.isShowDiary()) {
            corridorBackground.render();
            for(Item i : items) {
                i.render();
            }
            MyGdxGame.batch.setShader(null);
            player.render();
            hud.render();
        } else {
            corridorBackground.render();
            for(Item i : items) {
                i.render();
            }
            player.render();
            noiseBackground.render();
            hud.render();
            reactions.render();
            dialogs.render();
            exitConfirm.render();
        }
        diary.render();
        inventory.render(items);
    }

    public static Viewport getViewport1() {
        return viewport1;
    }

    public Dialogs getDialogs() {
        return dialogs;
    }

    @Override
    public void resize(int width, int height) {
        viewport1.update(width, height, true);
        camera.position.set(Data.getPrefs().getFloat("camera_position_x"), Data.getPrefs().getFloat("camera_position_y"), 0);
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
        corridorBackground.dispose();
        player.dispose();
        for(Item i : items) {
            i.dispose();
        }
        hud.dispose();
        corridorLoader.dispose();
        camera.dispose();
        dialogs.dispose();
        exitConfirm.dispose();
    }

    public Reactions getReactions() {
        return reactions;
    }

    public Item[] getItems() {
        return items;
    }


}
