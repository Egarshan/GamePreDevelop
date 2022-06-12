package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Mask extends Item {

    public static String anger = "anger";
    public static String sad = "sad";

    public Mask(TextureRegion texture, Vector2 position, float width, float height, boolean visible) {
        super(texture, position, width, height, visible);
    }
}
