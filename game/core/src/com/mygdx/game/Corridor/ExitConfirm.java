package com.mygdx.game.Corridor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Menu.MenuLoader;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourcesClass;

public class ExitConfirm {

    private TextureRegion background;
    private TextureRegion frame, text, leftButton, leftButtonLight, rightButton, rightButtonLight;
    private TextureRegion currentFrame;
    private Vector2 backgroundPosition;
    private Vector2 framePosition, textPosition, leftButtonPosition, rightButtonPosition;

    private float backgroundWidth, backgroundHeight;
    private float frameWidth, frameHeight, textWidth, textHeight;
    private float leftButtonWidth, leftButtonHeight, leftButtonLightWidth, leftButtonLightHeight;
    private float rightButtonWidth, rightButtonHeight, rightButtonLightWidth, rightButtonLightHeight;

    private boolean isCursorOnLeftButton, isCursorOnRightButton, touchedLeftButton, touchedRightButton;
    private boolean draw;

    private MyGdxGame game;
    private GameCamera camera;


    public ExitConfirm(MyGdxGame game, GameCamera camera) {
        this.game = game;
        this.camera = camera;

        background = ResourcesClass.getResources().get(20)[0];
        frame = ResourcesClass.getResources().get(17)[0];
        text = ResourcesClass.getResources().get(17)[1];
        leftButton = ResourcesClass.getResources().get(17)[2];
        leftButtonLight = ResourcesClass.getResources().get(17)[3];
        rightButton = ResourcesClass.getResources().get(17)[4];
        rightButtonLight = ResourcesClass.getResources().get(17)[5];

        backgroundWidth = MyGdxGame.WIDTH;
        backgroundHeight = MyGdxGame.HEIGHT;
        frameWidth = MyGdxGame.WIDTH/1.5f;
        frameHeight = frameWidth/1.727f;
        textWidth = frameWidth/2.3f;
        textHeight = textWidth/2.674f;
        leftButtonWidth = frameWidth/4f;
        leftButtonHeight = leftButtonWidth/3.529f;
        leftButtonLightWidth = leftButtonWidth;
        leftButtonLightHeight = leftButtonHeight;
        rightButtonWidth = leftButtonWidth;
        rightButtonHeight = leftButtonHeight;
        rightButtonLightWidth = leftButtonLightWidth;
        rightButtonLightHeight = leftButtonLightHeight;


        backgroundPosition = new Vector2(camera.getCameraPosition().x-MyGdxGame.WIDTH/2f, 0);
        framePosition = new Vector2(camera.getCameraPosition().x-frameWidth/2f, MyGdxGame.HEIGHT/2f-frameHeight/2f);
        textPosition = new Vector2(framePosition.x+frameWidth/2f-textWidth/2f, framePosition.y+frameHeight/2f+textHeight);
        leftButtonPosition = new Vector2(framePosition.x+frameWidth/2f-leftButtonWidth, framePosition.y+frameHeight/2f-leftButtonHeight);
        rightButtonPosition = new Vector2(framePosition.x+frameWidth/2f+rightButtonWidth, leftButtonPosition.y);

        isCursorOnLeftButton = false;
        isCursorOnRightButton = false;
        touchedLeftButton = false;
        touchedRightButton = false;
        draw = false;
    }

    public void update(float delta) {
        if(draw) {
            backgroundPosition.set(camera.getCameraPosition().x - MyGdxGame.WIDTH / 2f, 0);
            framePosition.set(camera.getCameraPosition().x - frameWidth / 2f, MyGdxGame.HEIGHT / 2f - frameHeight / 2f);
            textPosition.set(framePosition.x + frameWidth / 2f - textWidth / 2f, framePosition.y + frameHeight - 1.8f*textHeight);
            leftButtonPosition.set(framePosition.x + frameWidth / 2f - 1.25f*leftButtonWidth, framePosition.y + frameHeight / 2f - leftButtonHeight/3f);
            rightButtonPosition.set(framePosition.x + frameWidth / 2f + rightButtonWidth/4f, leftButtonPosition.y);

            if(touchedRightButton) {
                draw = false;
            }
            if(touchedLeftButton) {
                game.setScreen(new MenuLoader(game));
                game.getMusicBackground().pause();
            }
        } else {
            touchedLeftButton = false;
            touchedRightButton = false;
        }
    }

    public void render() {
        if(draw) {
            MyGdxGame.batch.draw(background, backgroundPosition.x, backgroundPosition.y, backgroundWidth, backgroundHeight);
            MyGdxGame.batch.draw(frame, framePosition.x, framePosition.y, frameWidth, frameHeight);
            if(isCursorOnLeftButton) {
                MyGdxGame.batch.draw(leftButtonLight, leftButtonPosition.x, leftButtonPosition.y, leftButtonWidth, leftButtonHeight);
            } else {
                MyGdxGame.batch.draw(leftButton, leftButtonPosition.x, leftButtonPosition.y, leftButtonWidth, leftButtonHeight);
            }

            if(isCursorOnRightButton) {
                MyGdxGame.batch.draw(rightButtonLight, rightButtonPosition.x, rightButtonPosition.y, rightButtonWidth, rightButtonHeight);
            } else {
                MyGdxGame.batch.draw(rightButton, rightButtonPosition.x, rightButtonPosition.y, rightButtonWidth, rightButtonHeight);
            }
            MyGdxGame.batch.draw(text, textPosition.x, textPosition.y, textWidth, textHeight);
        }
    }

    public void dispose() {
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public void setCursorOnLeftButton(boolean cursorOnLeftButton) {
        isCursorOnLeftButton = cursorOnLeftButton;
    }

    public void setCursorOnRightButton(boolean cursorOnRightButton) {
        isCursorOnRightButton = cursorOnRightButton;
    }

    public Vector2 getLeftButtonPosition() {
        return leftButtonPosition;
    }

    public Vector2 getRightButtonPosition() {
        return rightButtonPosition;
    }

    public float getLeftButtonWidth() {
        return leftButtonWidth;
    }

    public float getLeftButtonHeight() {
        return leftButtonHeight;
    }

    public float getRightButtonWidth() {
        return rightButtonWidth;
    }

    public float getRightButtonHeight() {
        return rightButtonHeight;
    }

    public void setTouchedLeftButton(boolean touchedLeftButton) {
        this.touchedLeftButton = touchedLeftButton;
    }

    public void setTouchedRightButton(boolean touchedRightButton) {
        this.touchedRightButton = touchedRightButton;
    }
}
