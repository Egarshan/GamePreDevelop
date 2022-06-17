package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Mask {

    private static String anger = "anger", sad = "sad";
    private static String currentMask;

    private boolean[] maskTaken;

    public Mask() {
        currentMask = sad;
        maskTaken = new boolean[2];

        maskTaken[0] = true;    //печаль
        maskTaken[1] = false;   //гнев
    }

    public void changeMask(int ind) {
        switch (ind) {
            case 0:
                currentMask = sad;
                break;
            case 1:
                currentMask = anger;
        }
    }

    public String getCurrentMask() {
        return currentMask;
    }

    public void takeMask(String maskName) {
        switch (maskName){
            case "anger":
                maskTaken[1] = true;
        }
    }

    public boolean[] getTakenMasks() {
        return maskTaken;
    }
}
