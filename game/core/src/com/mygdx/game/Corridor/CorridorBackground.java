package com.mygdx.game.Corridor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AudioPlayer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourcesClass;

import java.util.Random;

public class CorridorBackground {

    private TextureRegion background, backgroundNoLight;
    private Vector2 position;
    private float width, height;
    private int flickPeriod;
    //private Polygon polygon, polygon2, polygon3;

    private float time = 0, flickTime = 0;
    Random rand;

    private boolean flickSound;


    public CorridorBackground() {
        background = ResourcesClass.getResources().get(0)[0];
        backgroundNoLight = ResourcesClass.getResources().get(0)[1];

        position = new Vector2(0, 0);
        height = MyGdxGame.HEIGHT;
        width = height*2.767f;
        rand = new Random();

        flickSound = false;

        AudioPlayer.getLamp()[0].loop(0.3f,1,0);
        AudioPlayer.getLamp()[4].loop(0.2f,1,0);

        flickPeriod = rand.nextInt(500 - 200) + 200;
    }


    public void update(float deltaTime) {
    }

    public void render(float delta) {

        if (time >= flickPeriod) {
            flickLight(delta);
            if (!flickSound) {
                AudioPlayer.getLamp()[rand.nextInt(4-1) + 1 ].play(0.15f);
                flickSound = true;
            }
        }
        else {
            time += delta;
            MyGdxGame.batch.draw(background, position.x, position.y, width, height);
            flickSound = false;
        }
    }

    public void dispose() {

    }

    public float getWidth() {
        return width;
    }

    private void flickLight (float delta) {
        flickTime += delta;
        if (flickTime >= 100) {
            time = -flickPeriod;
            flickPeriod = rand.nextInt(500 - 200) + 200;
            flickTime -=100;
        }
        MyGdxGame.batch.draw(backgroundNoLight, position.x, position.y, width, height);
    }
}
