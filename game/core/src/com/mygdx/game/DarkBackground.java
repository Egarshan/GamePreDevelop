package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class DarkBackground {
    private static TextureRegion darkBackground;
    private static Vector2 position;
    private static float width, height;

    private static boolean isDark;

    public  DarkBackground() {
        darkBackground = ResourcesClass.getResources().get(20)[1];

        position = new Vector2(0, 0);
        height = MyGdxGame.HEIGHT;
        width = height*2.767f;

        isDark = false;
    }

    public static void update(float deltaTime) {
    }

    public static void render() {
        if (isDark)
            MyGdxGame.batch.draw(darkBackground, position.x, position.y, width, height);
    }

    public static void dispose() {
    }

    public static boolean getIsDark() {
        return isDark;
    }

    public static void setIsDark(boolean b) {
        isDark = b;
    }
}
