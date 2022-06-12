package com.mygdx.game.Corridor;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Item;
import com.mygdx.game.ResourcesClass;

public class Photo extends Item {

    public Photo() {
        super(ResourcesClass.getResources().get(4)[7], new Vector2(2052, 610), 50, 50*1.15f, true);
    }
}
