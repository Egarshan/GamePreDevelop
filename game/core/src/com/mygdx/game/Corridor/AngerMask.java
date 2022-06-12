package com.mygdx.game.Corridor;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Mask;
import com.mygdx.game.ResourcesClass;

public class AngerMask extends Mask {

    public AngerMask() {
        super(ResourcesClass.getResources().get(4)[5], new Vector2(1900, 307), 62, 62*1.851f, true);
    }
}
