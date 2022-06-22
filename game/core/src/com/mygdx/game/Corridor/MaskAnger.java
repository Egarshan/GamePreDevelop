package com.mygdx.game.Corridor;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Item;
import com.mygdx.game.ResourcesClass;

public class MaskAnger extends Item {
    public MaskAnger() {
        super(ResourcesClass.getResources().get(4)[5], new Vector2(1900, 307), 85, 129, true);
    }
}
