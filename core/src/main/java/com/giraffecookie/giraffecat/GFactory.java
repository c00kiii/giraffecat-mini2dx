package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.Texture;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.TextureRegion;

public class GFactory {

    static TextureRegion subX;
    static TextureRegion subY;
    static TextureRegion subCorner;

    public static void drawTextureTiled(Graphics g, Texture t, float x, float y, float width, float height){

        int cols = (int)width/t.getWidth();
        int rows = (int)height/t.getHeight();
        float subWidth = width%t.getWidth();
        float subHeight = height/t.getHeight();

        subX = new TextureRegion(t, 0, 0, t.getWidth(), subHeight);
        subY = new TextureRegion(t, 0, 0, subWidth, t.getHeight());
        subCorner = new TextureRegion(t, subWidth, subHeight, t.getWidth(), t.getHeight());

        for (int i = 0; i <= rows; i++){
            for (int j = 0; j <= cols; j++){
                if (i == rows){
                    if (j == cols){
                        g.drawTextureRegion(subX, j*t.getWidth(), j*t.getHeight());
                    }
                    else {
                        g.drawTextureRegion(subCorner, j*t.getWidth(), j*t.getHeight());
                    }
                }
                else {
                    if (j == cols){
                        g.drawTextureRegion(subY, j*t.getWidth(), j*t.getHeight());
                    }
                    else {
                        g.drawTexture(t, j*t.getWidth(), j*t.getHeight());
                    }
                }
            }
        }
    }
}
