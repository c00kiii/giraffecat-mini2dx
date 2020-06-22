package com.giraffecookie.giraffecat;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

public class CameraWindow implements Updatable, Renderable{

    static final float WIDTH = 64;
    static final float HEIGHT = 48;

    CollisionBox cb;
    GiraffeCat gfc;

    public CameraWindow(GiraffeCat gfc){
        this.gfc = gfc;
        cb = new CollisionBox(
                GiraffeCatGame.MODEL_WIDTH/2 - WIDTH/2,
                GiraffeCatGame.MODEL_HEIGHT/2 - HEIGHT/2,
                WIDTH,
                HEIGHT
        );
    }

    public void update(float delta){
        if (!cb.contains(gfc.cBox)){
            //cb.forceTo(cb.getRenderX() + gfc.physics.velocity.x*1.01f, cb.getRenderY() + gfc.physics.velocity.y*1.01f);
            float addX = 0;
            float addY = 0;
            if (gfc.cBox.getRenderX() < cb.getRenderX()){
                addX = gfc.cBox.getRenderX() - cb.getRenderX();
            }
            if (gfc.cBox.getRenderX() + gfc.cBox.getRenderWidth() > cb.getRenderX() + cb.getRenderWidth()){
                addX = gfc.cBox.getRenderX() + gfc.cBox.getRenderWidth() - cb.getRenderX() - cb.getRenderWidth();
            }
            if (gfc.cBox.getRenderY() < cb.getRenderY()){
                addY = gfc.cBox.getRenderY() - cb.getRenderY();
            }
            if (gfc.cBox.getRenderY() + gfc.cBox.getRenderHeight() > cb.getRenderY() + cb.getRenderHeight()){
                addY = gfc.cBox.getRenderY() + gfc.cBox.getRenderHeight() - cb.getRenderY() - cb.getRenderHeight();
            }
            cb.forceTo(cb.getRenderX() + addX, cb.getRenderY() + addY);
            Camera.add(addX, addY);
        }
    }

    public void render(Graphics g){
        //g.drawString(""+cb.contains(gfc.cBox), 0, 0);
        g.drawRect(cb.getRenderX()-Camera.cc.getRenderX(),
                cb.getRenderY()-Camera.cc.getRenderY(),
                cb.getRenderWidth(),
                cb.getRenderHeight());
    }
}
