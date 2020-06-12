package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.TextureRegion;

public class Platform implements Renderable {

    float x;
    float y;
    float width;
    float height;


    public Platform(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {

        g.setColor(Color.BLUE);
        g.drawRect(x, y, width, height);
    }

}
