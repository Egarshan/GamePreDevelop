package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class InventoryItem {
    private int ID;
    private Vector2 position;
    private String name;

    public InventoryItem(int ID, Vector2 position, String name){
        this.ID = ID;
        this.position = position;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
}
