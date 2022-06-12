package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Item {

    protected TextureRegion texture;
    protected Vector2 position;
    protected float width, height;
    protected boolean visible;
    protected boolean touchedDown;

    private boolean isTaken;

    public Item(TextureRegion texture, Vector2 position, float width, float height, boolean visible) {
        this.texture = texture;
        this.position = position;
        this.width = width;
        this.height = height;
        this.visible = visible;

        isTaken = false;
    }

    public void update(float deltaTime) {
    }

    public void render() {
        if(visible) {
            MyGdxGame.batch.draw(texture, position.x, position.y, width, height);
        }
    }

    public void dispose() {

    }

    public void setVisible(boolean b) {
        visible = b;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setTouchedDown(boolean b) {
        touchedDown = b;
    }

    public boolean getTouchedDown() {
        return touchedDown;
    }

    public void setIsTaken (boolean b) {
        isTaken = b;
    }

    public boolean getIsTaken() {
        return isTaken;
    }
}
