package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Corridor.GameCamera;

public class Reactions extends Text {

    private static JsonValue reactions;
    private Label label;
    private Vector2 labelPosition;
    private float labelWidth, labelHeight;
    private boolean draw, startTimer;
    private Timer timer;
    private GameCamera camera;

    public Reactions(GameCamera camera) {
        this.camera = camera;
        draw = false;
        reactions = getReactions();
        label = new Label("", new Label.LabelStyle(getMainFont(), Color.WHITE));
        label.setWrap(true);
        label.setAlignment(Align.center);
        labelWidth = Gdx.graphics.getWidth()/1.5f;
        labelHeight = Gdx.graphics.getHeight()/7f;
        labelPosition = new Vector2();
        label.setWidth(labelWidth);
        label.setHeight(labelHeight);
        label.setFontScale(0.5f);
        startTimer = false;

        timer = new Timer();
    }

    public void update(float deltaTime) {
        labelPosition.set(camera.getCameraPosition().x-labelWidth/2f, Gdx.graphics.getHeight()/50f);
        label.setPosition(labelPosition.x, labelPosition.y);
        if(draw && !startTimer) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    timer.stop();
                    timer.clear();
                    startTimer = false;
                    draw = false;
                }
            }, 3, 0, 0);
            timer.start();
            startTimer = true;
        }
    }

    public void render() {
        if(draw) {
            label.draw(MyGdxGame.batch, 1);
        }
    }

    public void setLabelText(String text) {
        label.setText(text);
    }

    public void setReaction(String name) {
        setLabelText(getText(getReactions(), name).asString());
    }

    public void setReaction(String name1, String name2) {
        draw = false;
        setLabelText(getText(getReactions(), name1, name2).asString());
    }

    public void cancelReaction() {
        setLabelText("");
        timer.stop();
        timer.clear();
        draw = false;
        startTimer = false;
    }

    public void setDraw(boolean b) {
        draw = b;
    }

    public boolean isReactionEmpty() {
        return label.getText().length <= 1;
    }
}
