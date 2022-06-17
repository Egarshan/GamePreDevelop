package com.mygdx.game.Corridor;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Item;
import com.mygdx.game.ResourcesClass;

public class Key extends Item {
    public Key() {
        super(ResourcesClass.getResources().get(19)[1], new Vector2(0, 0), 0, 0, false);
    }
}
