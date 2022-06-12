package com.mygdx.game.Corridor;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Item;
import com.mygdx.game.ResourcesClass;

public class Hammer extends Item {

    public Hammer() {
        super(ResourcesClass.getResources().get(4)[6], new Vector2(1704, 600), 62, 62*2.121f, true);
    }
}
