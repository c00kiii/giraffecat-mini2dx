package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.mini2Dx.core.graphics.Graphics;

public class Level {

    GiraffeCat gfc;

    public Level() {
        gfc = new GiraffeCat();
    }

    public void update(float delta) {
        gfc.update(delta);
    }

    public void render(Graphics g) {
        gfc.render(g);
    }

}
