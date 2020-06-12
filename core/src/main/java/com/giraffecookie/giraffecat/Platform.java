package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.Color;

import org.mini2Dx.core.graphics.Graphics;

public class Platform {
    float top;
    float bottom;
    float left;
    float right;


    public Platform(float left, float top, float width, float height) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
    }

    public void render(Graphics g) {

        float width = right - left;
        float height = top - bottom;

        g.setColor(Color.BLUE);
        g.drawRect(left, bottom, width, height);

    }

}
