package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class NoiseBackground {
    private TextureRegion noiseBackground;

    private Vector2 position;
    private float width, height;

    private boolean isNoise;

    public NoiseBackground() {
        noiseBackground = ResourcesClass.getResources().get(20)[0];

        position = new Vector2(0, 0);
        height = MyGdxGame.HEIGHT;
        width = height*2.767f;

        isNoise = false;
    }

    public void update(float deltaTime) {
    }

    public void render() {
        if (isNoise)
            MyGdxGame.batch.draw(noiseBackground, position.x, position.y, width, height);
    }

    public void dispose() {
    }

    public boolean getIsNoise() {
        return isNoise;
    }

    public void setIsNoise(boolean b) {
        isNoise = b;
    }
}
