package com.mygdx.game.Menu;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Corridor.CorridorLoader;
import com.mygdx.game.Data;
import com.mygdx.game.MyGdxGame;

public class MenuEventListener implements InputProcessor {

    private MyGdxGame game;
    private MenuHud menuHud;

    public MenuEventListener(MyGdxGame game, MenuHud menuHud) {
        this.game = game;
        this.menuHud = menuHud;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = MyGdxGame.HEIGHT-screenY;
        if(screenX >= menuHud.getLoadButtonPosition().x && screenX <= menuHud.getLoadButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getLoadButtonPosition().y && screenY <= menuHud.getLoadButtonPosition().y+menuHud.getButtonHeight()) {
            menuHud.setTouchedDownLoadButton(true);
            return true;
        }
        if(screenX >= menuHud.getNewGameButtonPosition().x && screenX <= menuHud.getNewGameButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getNewGameButtonPosition().y && screenY <= menuHud.getNewGameButtonPosition().y+menuHud.getButtonHeight()) {
            menuHud.setTouchedDownNewGameButton(true);
            return true;
        }
        if(screenX >= menuHud.getGalleryButtonPosition().x && screenX <= menuHud.getGalleryButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getGalleryButtonPosition().y && screenY <= menuHud.getGalleryButtonPosition().y+menuHud.getButtonHeight()) {
            menuHud.setTouchedDownGalleryButton(true);
            return true;
        }
//        if(screenX >= menuHud.getOptionsButtonPosition().x && screenX <= menuHud.getOptionsButtonPosition().x+menuHud.getButtonWidth() &&
//                screenY >= menuHud.getOptionsButtonPosition().y && screenY <= menuHud.getOptionsButtonPosition().y+menuHud.getButtonHeight()) {
//            menuHud.setTouchedDownOptionsButton(true);
//            return true;
//        }
        if(screenX >= menuHud.getExitButtonPosition().x && screenX <= menuHud.getExitButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getExitButtonPosition().y && screenY <= menuHud.getExitButtonPosition().y+menuHud.getButtonHeight()) {
            menuHud.setTouchedDownExitButton(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenY = MyGdxGame.HEIGHT-screenY;
        if(screenX >= menuHud.getLoadButtonPosition().x && screenX <= menuHud.getLoadButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getLoadButtonPosition().y && screenY <= menuHud.getLoadButtonPosition().y+menuHud.getButtonHeight() && menuHud.isTouchedDownLoadButton()) {
            menuHud.setTouchedDownLoadButton(false);
            game.setScreen(new CorridorLoader(game));
            return true;
        }
        if(screenX >= menuHud.getNewGameButtonPosition().x && screenX <= menuHud.getNewGameButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getNewGameButtonPosition().y && screenY <= menuHud.getNewGameButtonPosition().y+menuHud.getButtonHeight() && menuHud.isTouchedDownNewGameButton()) {
            menuHud.setTouchedDownNewGameButton(false);
            Data.firstLaunchSettings();
            game.setScreen(new CorridorLoader(game));

            return true;
        }
        if(screenX >= menuHud.getGalleryButtonPosition().x && screenX <= menuHud.getGalleryButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getGalleryButtonPosition().y && screenY <= menuHud.getGalleryButtonPosition().y+menuHud.getButtonHeight() && menuHud.isTouchedDownGalleryButton()) {
            menuHud.setTouchedDownGalleryButton(false);
            return true;
        }
//        if(screenX >= menuHud.getOptionsButtonPosition().x && screenX <= menuHud.getOptionsButtonPosition().x+menuHud.getButtonWidth() &&
//                screenY >= menuHud.getOptionsButtonPosition().y && screenY <= menuHud.getOptionsButtonPosition().y+menuHud.getButtonHeight() && menuHud.isTouchedDownOptionsButton()) {
//            menuHud.setTouchedDownOptionsButton(false);
//            return true;
//        }
        if(screenX >= menuHud.getExitButtonPosition().x && screenX <= menuHud.getExitButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getExitButtonPosition().y && screenY <= menuHud.getExitButtonPosition().y+menuHud.getButtonHeight() && menuHud.isTouchedDownExitButton()) {
            menuHud.setTouchedDownExitButton(false);
            game.dispose();
            System.exit(0);
            return true;
        }
        menuHud.setTouchedDownLoadButton(false);
        menuHud.setTouchedDownNewGameButton(false);
        menuHud.setTouchedDownGalleryButton(false);
        menuHud.setTouchedDownOptionsButton(false);
        menuHud.setTouchedDownExitButton(false);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        screenY = MyGdxGame.HEIGHT-screenY;
        if(screenX >= menuHud.getLoadButtonPosition().x && screenX <= menuHud.getLoadButtonPosition().x+menuHud.getButtonWidth() &&
            screenY >= menuHud.getLoadButtonPosition().y && screenY <= menuHud.getLoadButtonPosition().y+menuHud.getButtonHeight()) {
            menuHud.setLoadButtonActive(true);
            menuHud.setLoadLabelScale(0.6f);
            return true;
        }
        if(screenX >= menuHud.getNewGameButtonPosition().x && screenX <= menuHud.getNewGameButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getNewGameButtonPosition().y && screenY <= menuHud.getNewGameButtonPosition().y+menuHud.getButtonHeight()) {
            menuHud.setNewGameButtonActive(true);
            menuHud.setNewGameLabelScale(0.6f);
            return true;
        }
        if(screenX >= menuHud.getGalleryButtonPosition().x && screenX <= menuHud.getGalleryButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getGalleryButtonPosition().y && screenY <= menuHud.getGalleryButtonPosition().y+menuHud.getButtonHeight()) {
            menuHud.setGalleryButtonActive(true);
            menuHud.setGalleryLabelScale(0.6f);
            return true;
        }
//        if(screenX >= menuHud.getOptionsButtonPosition().x && screenX <= menuHud.getOptionsButtonPosition().x+menuHud.getButtonWidth() &&
//                screenY >= menuHud.getOptionsButtonPosition().y && screenY <= menuHud.getOptionsButtonPosition().y+menuHud.getButtonHeight()) {
//            menuHud.setOptionsButtonActive(true);
//            menuHud.setOptionsLabelScale(0.6f);
//            return true;
//        }
        if(screenX >= menuHud.getExitButtonPosition().x && screenX <= menuHud.getExitButtonPosition().x+menuHud.getButtonWidth() &&
                screenY >= menuHud.getExitButtonPosition().y && screenY <= menuHud.getExitButtonPosition().y+menuHud.getButtonHeight()) {
            menuHud.setExitButtonActive(true);
            menuHud.setExitLabelScale(0.6f);
            return true;
        }
        menuHud.setLoadButtonActive(false);
        menuHud.setNewGameButtonActive(false);
        menuHud.setGalleryButtonActive(false);
//        menuHud.setOptionsButtonActive(false);
        menuHud.setExitButtonActive(false);

        menuHud.setLoadLabelScale(0.5f);
        menuHud.setNewGameLabelScale(0.5f);
        menuHud.setGalleryLabelScale(0.5f);
//        menuHud.setOptionsLabelScale(0.5f);
        menuHud.setExitLabelScale(0.5f);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
