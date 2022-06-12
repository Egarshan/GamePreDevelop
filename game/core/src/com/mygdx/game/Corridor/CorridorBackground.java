package com.mygdx.game.Corridor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourcesClass;

import java.util.Random;

public class CorridorBackground {

    private TextureRegion background, backgroundNoLight;
    private Vector2 position;
    private float width, height;
    //private Polygon polygon, polygon2, polygon3;

    private float timeSeconds = 0f;
    private float period;
    Random rand;

    private boolean isFlick;


    public CorridorBackground() {
        background = ResourcesClass.getResources().get(0)[0];
        backgroundNoLight = ResourcesClass.getResources().get(0)[1];

        position = new Vector2(0, 0);
        height = MyGdxGame.HEIGHT;
        width = height*2.767f;
        rand = new Random();

        isFlick = false;
    }


    public void update(float deltaTime) {
    }

    public void render() {
        period =  5 + rand.nextFloat() * (15 - 5);
        timeSeconds += Gdx.graphics.getDeltaTime();
            if(timeSeconds > period) isFlick = true;

        if (!isFlick)
            MyGdxGame.batch.draw(background, position.x, position.y, width, height);
        else
            flickLight();
    }

    public void dispose() {

    }
    public float getWidth() {
        return width;
    }

    private void flickLight () {
        MyGdxGame.batch.draw(backgroundNoLight, position.x, position.y, width, height);
        period = 6f;

        timeSeconds += Gdx.graphics.getDeltaTime();
        if(timeSeconds > period){
            timeSeconds-=period;
            isFlick = false;
        }
    }
}
