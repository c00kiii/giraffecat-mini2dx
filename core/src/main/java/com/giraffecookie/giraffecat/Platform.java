package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.Color;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

public class Platform implements Solid, Renderable {

    CollisionBox cb;

    public Platform(float x, float y, float width, float height) {
        cb = new CollisionBox(x, y, width,height);
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        cb.draw(g);
    }

}
