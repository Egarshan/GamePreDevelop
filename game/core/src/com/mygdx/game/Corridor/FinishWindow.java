package com.mygdx.game.Corridor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.AudioPlayer;
import com.mygdx.game.Menu.MenuLoader;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourcesClass;
import com.mygdx.game.Text;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class FinishWindow {

    private TextureRegion background;
    private TextureRegion frame, okButton, okButtonLight;
    private TextureRegion currentFrame;
    private Vector2 backgroundPosition;
    private Vector2 framePosition, okButtonPosition;

    private float backgroundWidth, backgroundHeight;
    private float frameWidth, frameHeight;
    private float okButtonWidth, okButtonHeight, okButtonLightWidth, okButtonLightHeight;

    private boolean isCursorOnOkButton, touchedOkButton;
    private boolean draw;

    private MyGdxGame game;
    private GameCamera camera;

    private Text text;

    private Label finishLabel, okLabel;

    public FinishWindow(MyGdxGame game, GameCamera camera) {
        this.game = game;
        this.camera = camera;

        text = new Text();
        finishLabel = new Label("Спасибо за прохождение \nдемоверсии игры \n\"На перепутье!\"", new Label.LabelStyle(text.getMainFont(), Color.WHITE));
        finishLabel.setAlignment(Align.center);

        okLabel = new Label("OK", new Label.LabelStyle(text.getMainFont(), Color.WHITE));
        okLabel.setAlignment(Align.center);

        background = ResourcesClass.getResources().get(20)[0];
        frame = ResourcesClass.getResources().get(17)[0];
        okButton = ResourcesClass.getResources().get(17)[2];
        okButtonLight = ResourcesClass.getResources().get(17)[3];

        backgroundWidth = MyGdxGame.WIDTH;
        backgroundHeight = MyGdxGame.HEIGHT;
        frameWidth = MyGdxGame.WIDTH/1.5f;
        frameHeight = frameWidth/1.727f;
        okButtonWidth = frameWidth/3.5f;
        okButtonHeight = okButtonWidth /3.529f;
        okButtonLightWidth = okButtonWidth;
        okButtonLightHeight = okButtonHeight;


        backgroundPosition = new Vector2(camera.getCameraPosition().x-MyGdxGame.WIDTH/2f, 0);
        framePosition = new Vector2(camera.getCameraPosition().x-frameWidth/2f, MyGdxGame.HEIGHT/2f-frameHeight/2f);
        okButtonPosition = new Vector2(camera.position.x - okButtonWidth /2, framePosition.y + frameHeight / 2.5f - okButtonHeight /3f);

        isCursorOnOkButton = false;
        touchedOkButton = false;
        draw = false;
    }

    public void update(float delta) {
        finishLabel.setBounds(framePosition.x, framePosition.y + frameHeight / 4.8f, frameWidth, frameHeight);
        okLabel.setBounds(okButtonPosition.x, okButtonPosition.y, okButtonWidth, okButtonHeight);
        if(draw) {
            backgroundPosition.set(camera.getCameraPosition().x - MyGdxGame.WIDTH / 2f, 0);
            framePosition.set(camera.getCameraPosition().x - frameWidth / 2f, MyGdxGame.HEIGHT / 2f - frameHeight / 2f);
            okButtonPosition.set(camera.position.x - okButtonWidth /2, framePosition.y + frameHeight / 3.5f - okButtonHeight /3f);

            if(touchedOkButton) {
                game.setScreen(new MenuLoader(game));
                AudioPlayer.getMusic()[0].pause();
            }
        } else {
            touchedOkButton = false;
        }
    }

    public void render() {
        if(draw) {
            MyGdxGame.batch.draw(background, backgroundPosition.x, backgroundPosition.y, backgroundWidth, backgroundHeight);
            MyGdxGame.batch.draw(frame, framePosition.x, framePosition.y, frameWidth, frameHeight);
            if(isCursorOnOkButton) {
                MyGdxGame.batch.draw(okButtonLight, okButtonPosition.x, okButtonPosition.y, okButtonWidth, okButtonHeight);
            } else {
                MyGdxGame.batch.draw(okButton, okButtonPosition.x, okButtonPosition.y, okButtonWidth, okButtonHeight);
            }
            finishLabel.draw(MyGdxGame.batch, 1);
            okLabel.draw(MyGdxGame.batch, 1);
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

    public void setCursorOnOkButton(boolean cursorOnOkButton) {
        isCursorOnOkButton = cursorOnOkButton;
    }

    public Vector2 getOkButtonPosition() {
        return okButtonPosition;
    }

    public float getOkButtonWidth() {
        return okButtonWidth;
    }

    public float getOkButtonHeight() {
        return okButtonHeight;
    }

    public void setTouchedOkButton(boolean touchedOkButton) {
        this.touchedOkButton = touchedOkButton;
    }
}
