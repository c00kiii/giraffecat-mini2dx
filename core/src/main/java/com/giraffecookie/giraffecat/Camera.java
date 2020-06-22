package com.giraffecookie.giraffecat;

import org.mini2Dx.core.engine.geom.CollisionCircle;

public class Camera {

    static CollisionCircle cc = new CollisionCircle(0, 0, 1);

    public static void addX(float x){
        cc.forceTo(cc.getRenderX()+x, cc.getRenderY());
    }

    public static void addY(float y){
        cc.forceTo(cc.getRenderX(), cc.getRenderY()+y);
    }

    public static void add(float x, float y){
        cc.forceTo(cc.getRenderX()+x, cc.getRenderY()+y);
    }
}
