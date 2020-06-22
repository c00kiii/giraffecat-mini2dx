package com.giraffecookie.giraffecat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class CameraController implements Updatable {

    static final float MOVESPEED = 5;

//    CameraWindow cw;
//
//    public CameraController(CameraWindow cw){
//        this.cw = cw;
//    }

    public void update(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            Camera.addY(-1*MOVESPEED);
            //cw.cb.forceTo(cw.cb.getRenderX(), cw.cb.getRenderY()-1*delta*MOVESPEED);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            Camera.addX(-1*MOVESPEED);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            Camera.addY(MOVESPEED);
            //Camera.cc.forceTo(Camera.cc.getRenderX(), Camera.cc.getRenderY()+1*delta*50);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            Camera.addX(MOVESPEED);
        }
    }
}
