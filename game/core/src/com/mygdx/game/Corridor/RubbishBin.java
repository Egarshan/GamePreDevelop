package com.mygdx.game.Corridor;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Item;
import com.mygdx.game.ResourcesClass;

public class RubbishBin extends Item {

    private Polygon polygon1, polygon2;
    private Vector2 point1, point2, point3, point4, point5, point6;

    public RubbishBin() {
        super(ResourcesClass.getResources().get(4)[4], new Vector2(70, 130), 160, 160*1.782f, true);

        point1 = new Vector2(254, 232);
        point2 = new Vector2(221, 174);
        point3 = new Vector2(121, 130);
        point4 = new Vector2(84, 145);
        point5 = new Vector2(0, 33);
        point6 = new Vector2(1, 6);


        polygon1 = new Polygon(new float[] {point1.x, point1.y, point2.x, point2.y, point3.x, point3.y});
        polygon2 = new Polygon(new float[] {point4.x, point4.y, point5.x, point5.y, point6.x, point6.y});
    }

    public Polygon getPolygon() {
        return polygon1;
    }

    public Polygon getPolygon2() {
        return polygon2;
    }
}
