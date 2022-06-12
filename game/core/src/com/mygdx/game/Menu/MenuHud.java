package com.mygdx.game.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourcesClass;

public class MenuHud {

    private TextureRegion titleTexture, buttonPressedTexture, buttonTexture;
    private Vector2 titlePosition;
    private Vector2 loadButtonPosition, newGameButtonPosition, galleryButtonPosition, optionsButtonPosition, exitButtonPosition;
    private float titleWidth, titleHeight;
    private float buttonPressedWidth, buttonPressedHeight;
    private float buttonWidth, buttonHeight;
    private boolean loadButtonActive, newGameButtonActive, galleryButtonActive, optionsButtonActive, exitButtonActive;
    private boolean touchedDownLoadButton, touchedDownNewGameButton, touchedDownGalleryButton, touchedDownOptionsButton, touchedDownExitButton;
    private Label loadLabel, newGameLabel, galleryLabel, optionsLabel, exitLabel;
    private float loadLabelScale, newGameLabelScale, galleryLabelScale, optionsLabelScale, exitLabelScale;
    private MenuFont menuFont;

    public MenuHud(MenuFont menuFont) {
        this.menuFont = menuFont;
        titleTexture = ResourcesClass.getResources().get(1)[0];
        buttonTexture = ResourcesClass.getResources().get(1)[2];
        buttonPressedTexture = ResourcesClass.getResources().get(1)[1];

        titleWidth = MyGdxGame.WIDTH/2.7f;
        titleHeight = titleWidth/7.45f;
        buttonWidth = MyGdxGame.WIDTH/5.15f;
        buttonHeight = buttonWidth/5.012f;
        buttonPressedWidth = MyGdxGame.WIDTH/4.6f;
        buttonPressedHeight = buttonPressedWidth/3.921f;

        titlePosition = new Vector2(MyGdxGame.WIDTH/1.9f, MyGdxGame.HEIGHT/1.29f);
        loadButtonPosition = new Vector2(titlePosition.x+titleWidth/2.14f, titlePosition.y-1.8f*titleHeight);
        newGameButtonPosition = new Vector2(loadButtonPosition.x, loadButtonPosition.y-1.5f*buttonHeight);
        galleryButtonPosition = new Vector2(newGameButtonPosition.x, newGameButtonPosition.y-1.5f*buttonHeight);
        optionsButtonPosition = new Vector2(galleryButtonPosition.x, galleryButtonPosition.y-1.5f*buttonHeight);
        exitButtonPosition = new Vector2(optionsButtonPosition.x, optionsButtonPosition.y-1.5f*buttonHeight);

        loadButtonActive = false;
        newGameButtonActive = false;
        galleryButtonActive = false;
        optionsButtonActive = false;
        exitButtonActive = false;

        touchedDownLoadButton = false;
        touchedDownNewGameButton = false;
        touchedDownGalleryButton = false;
        touchedDownOptionsButton = false;
        touchedDownExitButton = false;

        loadLabelScale = 0.5f;
        newGameLabelScale = 0.5f;
        galleryLabelScale = 0.5f;
        optionsLabelScale = 0.5f;
        exitLabelScale = 0.5f;

        loadLabel = new Label("Загрузить", new Label.LabelStyle(menuFont.getFont(), Color.WHITE));
        loadLabel.setBounds(loadButtonPosition.x+buttonWidth/9f, loadButtonPosition.y+buttonHeight/2f, 0, 0);
        loadLabel.setFontScale(0.5f);

        newGameLabel = new Label("Новая игра", new Label.LabelStyle(menuFont.getFont(), Color.WHITE));
        newGameLabel.setBounds(newGameButtonPosition.x+buttonWidth/9f, newGameButtonPosition.y+buttonHeight/2f, 0, 0);
        newGameLabel.setFontScale(0.5f);

        galleryLabel = new Label("Галерея", new Label.LabelStyle(menuFont.getFont(), Color.WHITE));
        galleryLabel.setBounds(galleryButtonPosition.x+buttonWidth/9f, galleryButtonPosition.y+buttonHeight/2f, 0, 0);
        galleryLabel.setFontScale(0.5f);

        optionsLabel = new Label("Настройки", new Label.LabelStyle(menuFont.getFont(), Color.WHITE));
        optionsLabel.setBounds(optionsButtonPosition.x+buttonWidth/9f, optionsButtonPosition.y+buttonHeight/2f, 0, 0);
        optionsLabel.setFontScale(0.5f);

        exitLabel = new Label("Выход", new Label.LabelStyle(menuFont.getFont(), Color.WHITE));
        exitLabel.setBounds(exitButtonPosition.x+buttonWidth/9f, exitButtonPosition.y+buttonHeight/2f, 0, 0);
        exitLabel.setFontScale(0.5f);
    }

    public void update(float deltaTime) {
        loadLabel.setFontScale(loadLabelScale);
        newGameLabel.setFontScale(newGameLabelScale);
        galleryLabel.setFontScale(galleryLabelScale);
        optionsLabel.setFontScale(optionsLabelScale);
        exitLabel.setFontScale(exitLabelScale);

        if(isLoadButtonActive()) {
            loadLabel.setBounds(loadButtonPosition.x-(buttonPressedWidth-buttonWidth)+buttonPressedWidth/9f, loadButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f+buttonPressedHeight/1.8f, 0, 0);
        } else {
            loadLabel.setBounds(loadButtonPosition.x+buttonWidth/9f, loadButtonPosition.y+buttonHeight/1.8f, 0, 0);
        }
        if(isNewGameButtonActive()) {
            newGameLabel.setBounds(newGameButtonPosition.x-(buttonPressedWidth-buttonWidth)+buttonPressedWidth/9f, newGameButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f+buttonPressedHeight/1.8f, 0, 0);
        } else {
            newGameLabel.setBounds(newGameButtonPosition.x+buttonWidth/9f, newGameButtonPosition.y+buttonHeight/1.8f, 0, 0);
        }
        if(isGalleryButtonActive()) {
            galleryLabel.setBounds(galleryButtonPosition.x-(buttonPressedWidth-buttonWidth)+buttonPressedWidth/9f, galleryButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f+buttonPressedHeight/1.8f, 0, 0);
        } else {
            galleryLabel.setBounds(galleryButtonPosition.x+buttonWidth/9f, galleryButtonPosition.y+buttonHeight/1.8f, 0, 0);
        }
        if(isOptionsButtonActive()) {
            optionsLabel.setBounds(optionsButtonPosition.x-(buttonPressedWidth-buttonWidth)+buttonPressedWidth/9f, optionsButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f+buttonPressedHeight/1.8f, 0, 0);
        } else {
            optionsLabel.setBounds(optionsButtonPosition.x+buttonWidth/9f, optionsButtonPosition.y+buttonHeight/1.8f, 0, 0);
        }
        if(isExitButtonActive()) {
            exitLabel.setBounds(exitButtonPosition.x-(buttonPressedWidth-buttonWidth)+buttonPressedWidth/9f, exitButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f+buttonPressedHeight/1.8f, 0, 0);
        } else {
            exitLabel.setBounds(exitButtonPosition.x+buttonWidth/9f, exitButtonPosition.y+buttonHeight/1.8f, 0, 0);
        }
    }

    public void render() {
        MyGdxGame.batch.draw(titleTexture, titlePosition.x, titlePosition.y, titleWidth, titleHeight);
        if(isLoadButtonActive()) {
            MyGdxGame.batch.draw(buttonPressedTexture, loadButtonPosition.x-(buttonPressedWidth-buttonWidth), loadButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f, buttonPressedWidth, buttonPressedHeight);
        } else {
            MyGdxGame.batch.draw(buttonTexture, loadButtonPosition.x, loadButtonPosition.y, buttonWidth, buttonHeight);
        }
        if(isNewGameButtonActive()) {
            MyGdxGame.batch.draw(buttonPressedTexture, newGameButtonPosition.x-(buttonPressedWidth-buttonWidth), newGameButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f, buttonPressedWidth, buttonPressedHeight);
        } else {
            MyGdxGame.batch.draw(buttonTexture, newGameButtonPosition.x, newGameButtonPosition.y, buttonWidth, buttonHeight);
        }
        if(isGalleryButtonActive()) {
            MyGdxGame.batch.draw(buttonPressedTexture, galleryButtonPosition.x-(buttonPressedWidth-buttonWidth), galleryButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f, buttonPressedWidth, buttonPressedHeight);
        } else {
            MyGdxGame.batch.draw(buttonTexture, galleryButtonPosition.x, galleryButtonPosition.y, buttonWidth, buttonHeight);
        }
        if(isOptionsButtonActive()) {
            MyGdxGame.batch.draw(buttonPressedTexture, optionsButtonPosition.x-(buttonPressedWidth-buttonWidth), optionsButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f, buttonPressedWidth, buttonPressedHeight);
        } else {
            MyGdxGame.batch.draw(buttonTexture, optionsButtonPosition.x, optionsButtonPosition.y, buttonWidth, buttonHeight);
        }
        if(isExitButtonActive()) {
            MyGdxGame.batch.draw(buttonPressedTexture, exitButtonPosition.x-(buttonPressedWidth-buttonWidth), exitButtonPosition.y-(buttonPressedHeight-buttonHeight)/2f, buttonPressedWidth, buttonPressedHeight);
        } else {
            MyGdxGame.batch.draw(buttonTexture, exitButtonPosition.x, exitButtonPosition.y, buttonWidth, buttonHeight);
        }
        loadLabel.draw(MyGdxGame.batch, 1);
        newGameLabel.draw(MyGdxGame.batch, 1);
        galleryLabel.draw(MyGdxGame.batch, 1);
        optionsLabel.draw(MyGdxGame.batch, 1);
        exitLabel.draw(MyGdxGame.batch, 1);
    }

    public void dispose() {

    }

    public boolean isLoadButtonActive() {
        return loadButtonActive;
    }

    public boolean isNewGameButtonActive() {
        return newGameButtonActive;
    }

    public boolean isGalleryButtonActive() {
        return galleryButtonActive;
    }

    public boolean isOptionsButtonActive() {
        return optionsButtonActive;
    }

    public boolean isExitButtonActive() {
        return exitButtonActive;
    }

    public void setLoadButtonActive(boolean loadButtonActive) {
        this.loadButtonActive = loadButtonActive;
    }

    public void setNewGameButtonActive(boolean newGameButtonActive) {
        this.newGameButtonActive = newGameButtonActive;
    }

    public void setGalleryButtonActive(boolean galleryButtonActive) {
        this.galleryButtonActive = galleryButtonActive;
    }

    public void setOptionsButtonActive(boolean optionsButtonActive) {
        this.optionsButtonActive = optionsButtonActive;
    }

    public void setExitButtonActive(boolean exitButtonActive) {
        this.exitButtonActive = exitButtonActive;
    }

    public Vector2 getLoadButtonPosition() {
        return loadButtonPosition;
    }

    public Vector2 getNewGameButtonPosition() {
        return newGameButtonPosition;
    }

    public Vector2 getGalleryButtonPosition() {
        return galleryButtonPosition;
    }

    public Vector2 getOptionsButtonPosition() {
        return optionsButtonPosition;
    }

    public Vector2 getExitButtonPosition() {
        return exitButtonPosition;
    }

    public float getButtonWidth() {
        return buttonWidth;
    }

    public float getButtonHeight() {
        return buttonHeight;
    }

    public void setLoadLabelScale(float loadLabelScale) {
        this.loadLabelScale = loadLabelScale;
    }

    public void setNewGameLabelScale(float newGameLabelScale) {
        this.newGameLabelScale = newGameLabelScale;
    }

    public void setGalleryLabelScale(float galleryLabelScale) {
        this.galleryLabelScale = galleryLabelScale;
    }

    public void setOptionsLabelScale(float optionsLabelScale) {
        this.optionsLabelScale = optionsLabelScale;
    }

    public void setExitLabelScale(float exitLabelScale) {
        this.exitLabelScale = exitLabelScale;
    }

    public void setTouchedDownLoadButton(boolean touchedDownLoadButton) {
        this.touchedDownLoadButton = touchedDownLoadButton;
    }

    public void setTouchedDownNewGameButton(boolean touchedDownNewGameButton) {
        this.touchedDownNewGameButton = touchedDownNewGameButton;
    }

    public void setTouchedDownGalleryButton(boolean touchedDownGalleryButton) {
        this.touchedDownGalleryButton = touchedDownGalleryButton;
    }

    public void setTouchedDownOptionsButton(boolean touchedDownOptionsButton) {
        this.touchedDownOptionsButton = touchedDownOptionsButton;
    }

    public void setTouchedDownExitButton(boolean touchedDownExitButton) {
        this.touchedDownExitButton = touchedDownExitButton;
    }

    public boolean isTouchedDownLoadButton() {
        return touchedDownLoadButton;
    }

    public boolean isTouchedDownNewGameButton() {
        return touchedDownNewGameButton;
    }

    public boolean isTouchedDownGalleryButton() {
        return touchedDownGalleryButton;
    }

    public boolean isTouchedDownOptionsButton() {
        return touchedDownOptionsButton;
    }

    public boolean isTouchedDownExitButton() {
        return touchedDownExitButton;
    }
}
