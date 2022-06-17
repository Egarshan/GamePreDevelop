package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class DarkBackground {
    private TextureRegion darkBackground;

    private Vector2 position;
    private float width, height;

    private boolean isDark;

    public DarkBackground() {
        darkBackground = ResourcesClass.getResources().get(20)[1];

        position = new Vector2(0, 0);
        height = MyGdxGame.HEIGHT;
        width = height*2.767f;

        isDark = false;
    }

    public void update(float deltaTime) {
    }

    public void render() {
        if (isDark)
            MyGdxGame.batch.draw(darkBackground, position.x, position.y, width, height);
    }

    public void dispose() {
    }

    public boolean getIsDark() {
        return isDark;
    }

    public void setIsDark(boolean b) {
        isDark = b;
    }
}
