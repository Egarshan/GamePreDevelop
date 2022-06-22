package com.mygdx.game.Corridor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.*;

public class CorridorScene implements Screen {

    private GameCamera camera;
    private static Viewport viewport1;
    private CorridorEventListener listener;
    private CorridorBackground corridorBackground;
    protected NoiseBackground noiseBackground;
    protected DarkBackground darkBackground;

    private Player player;
    private Diary diary;
    private Item[] items;
    private Hud hud;

    protected ExitConfirm exitConfirm;
    protected FinishWindow finishWindow;

//    private Dialogs dialogs;
    private Dialog dialog;
    private boolean cameraMoveRight;
    private CorridorLoader corridorLoader;
    private Reactions reactions;
    private MyGdxGame game;

    private Inventory inventory;
    private MaskSelector maskSelector;

    private boolean keyIsAvailable, windowIsBroken, meetIsDone, doorIsOpen;

    public CorridorScene(MyGdxGame game, CorridorLoader corridorLoader) {
        this.game = game;
        this.corridorLoader = corridorLoader;
        corridorBackground = new CorridorBackground();
        noiseBackground = new NoiseBackground();
        darkBackground = new DarkBackground();
        camera = new GameCamera(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, corridorBackground);

        viewport1 = new ExtendViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camera);

        diary = new Diary(camera);
        items = new Item[9];
        items[5] = new Hammer();
        items[4] = new Photo();
        items[1] = new DoorOpen();
        items[0] = new DoorClose();
        items[2] = new RubbishBin();
        items[6] = new Flowers();
        items[3] = new MaskAnger();
        items[7] = new Item(ResourcesClass.getResources().get(4)[8], new Vector2(1850, 530), 303, 260, false);
        items[8] = new Key();

        inventory = new Inventory(camera);

        reactions = new Reactions(camera);

        player = new Player(this, camera, (RubbishBin)items[2], (Flowers) items[6], reactions);

        maskSelector = new MaskSelector(camera, player.getMask());

        hud = new Hud(camera, player);

//        dialogs = new Dialogs(camera, player);
        dialog = new Dialog(camera, player);

        exitConfirm = new ExitConfirm(game, camera);
        finishWindow = new FinishWindow(game, camera);

        listener = new CorridorEventListener(game, this, player, diary, hud, exitConfirm, inventory, maskSelector, dialog);

        cameraMoveRight = false;

        keyIsAvailable = false;
        windowIsBroken = false;
        meetIsDone = false;
        doorIsOpen = false;
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
        maskSelector.update(delta);
//        dialogs.update();
        dialog.update();
        for(Item i : items) {
            i.update(delta);
        }
        exitConfirm.update(delta);
        finishWindow.update(delta);
        if(diary.isShowDiary()) {
            corridorBackground.render(2);
            for(Item i : items) {
                i.render();
            }
            MyGdxGame.batch.setShader(null);
            player.render();
            hud.render();
        } else {
            corridorBackground.render(2);
            for(Item i : items) {
                i.render();
            }
            player.render();
            noiseBackground.render();
            darkBackground.render();
            hud.render();
            reactions.render();
//            dialogs.render();
            dialog.render();
            exitConfirm.render();
            finishWindow.render();
        }
        diary.render();
        inventory.render(items);
        maskSelector.render();
    }

    public static Viewport getViewport1() {
        return viewport1;
    }

//    public Dialogs getDialogs() {
//        return dialogs;
//    }

    public Dialog getDialog() {
        return dialog;
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
//        dialogs.dispose();
        dialog.dispose();
        exitConfirm.dispose();
    }

    public Reactions getReactions() {
        return reactions;
    }

    public Item[] getItems() {
        return items;
    }

    public boolean getKeyIsAvailable() {
        return keyIsAvailable;
    }

    public void setKeyIsAvailable(boolean b) {
        keyIsAvailable = b;
    }

    public Hud getHud() {
        return hud;
    }

    public boolean getWindowIsBroken() {
        return windowIsBroken;
    }

    public void setWindowIsBroken(boolean b) {
        windowIsBroken = b;
    }

    public boolean getMeetIsDone () {
        return meetIsDone;
    }

    public void setMeetIsDone (boolean b) {
        meetIsDone = b;
    }

    public void setDoorIsOpen(boolean b) {
        doorIsOpen = b;
    }

    public boolean getDoorIsOpen() {
        return doorIsOpen;
    }
}
