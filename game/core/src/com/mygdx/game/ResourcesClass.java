package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Arrays;

public class ResourcesClass {

    private static ArrayList<TextureRegion[]> resources = new ArrayList<>();

    public ResourcesClass() {
    }

    public static ArrayList<TextureRegion[]> getResources() {
        return resources;
    }

    public static void addResources(TextureRegion[]...t) {
        resources.addAll(Arrays.asList(t));
    }

    public static void disposeResources() {
        resources.clear();
    }
}
