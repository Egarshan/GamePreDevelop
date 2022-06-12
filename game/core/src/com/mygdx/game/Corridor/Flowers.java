package com.mygdx.game.Corridor;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Item;
import com.mygdx.game.Reactions;
import com.mygdx.game.ResourcesClass;

public class Flowers extends Item {

    private Polygon polygon1, polygon2;
    private Vector2 point1, point2, point3, point4, point5, point6;

    public Flowers() {
        super(ResourcesClass.getResources().get(4)[3], new Vector2(1943, 203), 300, 300*0.875f, true);

        point1 = new Vector2(1989, 207);
        point2 = new Vector2(2026, 194);
        point3 = new Vector2(2070, 214);
        point4 = new Vector2(2153, 210);
        point5 = new Vector2(2190, 189);
        point6 = new Vector2(2237, 213);

        polygon1 = new Polygon(new float[] {point1.x, point1.y, point2.x, point2.y, point3.x, point3.y});
        polygon2 = new Polygon(new float[] {point4.x, point4.y, point5.x, point5.y, point6.x, point6.y});
    }

    public Polygon getPolygon1() {
        return polygon1;
    }

    public Polygon getPolygon2() {
        return polygon2;
    }
}
