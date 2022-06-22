package com.mygdx.game.Corridor;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Item;
import com.mygdx.game.ResourcesClass;

public class DoorOpen extends Item {

    public DoorOpen() {
        super(ResourcesClass.getResources().get(4)[0], new Vector2(2330, 153), 661, 701, false);
    }
}
