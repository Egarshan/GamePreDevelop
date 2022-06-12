package com.mygdx.game.Menu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourcesClass;

public class MenuBackground {

    private TextureRegion backgroundTexture;
    private Vector2 position;
    private float width, height;

    public MenuBackground() {
        backgroundTexture = ResourcesClass.getResources().get(0)[0];
        position = new Vector2(0, 0);
        width = MyGdxGame.WIDTH;
        height = width/1.777f;
    }

    public void update(float deltaTime) {

    }

    public void render() {
        MyGdxGame.batch.draw(backgroundTexture, position.x, position.y, width, height);
    }

    public void dispose() {

    }
}
