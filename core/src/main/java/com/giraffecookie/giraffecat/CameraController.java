package com.giraffecookie.giraffecat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class CameraController implements Updatable {

    static final float MOVESPEED = 50;

    public void update(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            Camera.cc.forceTo(Camera.cc.getRenderX(), Camera.cc.getRenderY()-1*delta*50);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            Camera.cc.forceTo(Camera.cc.getRenderX()-1*delta*50, Camera.cc.getRenderY());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            Camera.cc.forceTo(Camera.cc.getRenderX(), Camera.cc.getRenderY()+1*delta*50);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            Camera.cc.forceTo(Camera.cc.getRenderX()+1*delta*50, Camera.cc.getRenderY());
        }
    }
}
