package com.mygdx.game.Corridor;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Data;
import com.mygdx.game.MyGdxGame;

public class GameCamera extends OrthographicCamera {
    private boolean moveRight, moveLeft;

    private CorridorBackground corridorBackground;

    public GameCamera(float width, float height, CorridorBackground corridorBackground) {
        super(width, height);
        this.corridorBackground = corridorBackground;
        moveRight = false;
        moveLeft = false;
        setCameraPosition(MyGdxGame.WIDTH/2f);
    }

    public void update(float deltaTime) {
        if(!moveRight) {
            if(isCameraOnLeft()) {
                position.x = MyGdxGame.WIDTH/2f;
            }
        }
        if(!moveLeft) {
            if(isCameraOnRight()) {
                position.x = corridorBackground.getWidth()-MyGdxGame.WIDTH/2f;
            }
        }
    }

    public void dispose() {
        Data.saveCamera(position.x, position.y);
    }

    public void setMoveRight(boolean b) {
        moveRight = b;
    }

    public void setMoveLeft(boolean b) {
        moveLeft = b;
    }

    public boolean isCameraOnRight() {
        return position.x >= corridorBackground.getWidth()-MyGdxGame.WIDTH/2f;
    }
    public boolean isCameraOnLeft() {
        return position.x <= MyGdxGame.WIDTH/2f;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public Vector3 getCameraPosition() {
        return position;
    }

    public void setCameraPosition(float x) {
        position.set(x, position.y, 0);
    }
}
