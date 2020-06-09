package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.Texture;

import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.SpriteSheet;

public class GameSprite {
    private Animation anim = new Animation();
    private Texture texture;
    private SpriteSheet spriteSheet;
    private int frameWidth, frameHeight;

    public GameSprite(String file, int frameWidth, int frameHeight) {
        texture = new Texture(file);
        spriteSheet = new SpriteSheet(texture, frameWidth, frameHeight);
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        anim.addFrame(spriteSheet.getSprite(0), 1);
    }

    public GameSprite(String file, int frameWidth, int frameHeight, float frameDuration, int numberOfFrames) {
        texture = new Texture(file);
        spriteSheet = new SpriteSheet(texture, frameWidth, frameHeight);
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;

        for (int i = 0; i < numberOfFrames; i++) {
            anim.addFrame(spriteSheet.getSprite(i), frameDuration);
        }
    }

    public Animation getAnimation() {
        return anim;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }


}
