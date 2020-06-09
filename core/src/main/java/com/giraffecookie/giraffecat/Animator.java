package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.Texture;

import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.SpriteSheet;

public class Animator {

    public static Animation loadSprite(String file, int frameWidth, int frameHeight) {
        Animation anim = new Animation();
        Texture texture = new Texture(file);
        SpriteSheet spriteSheet = new SpriteSheet(texture, frameWidth, frameHeight);
        anim.addFrame(spriteSheet.getSprite(0), 1);
        return anim;
    }

    public static Animation loadSprite(String file, int frameWidth, int frameHeight, float frameDuration, int numberOfFrames) {
        Animation anim = new Animation();
        Texture texture = new Texture(file);
        SpriteSheet spriteSheet = new SpriteSheet(texture, frameWidth, frameHeight);
        for (int i = 0; i < numberOfFrames; i++) {
            anim.addFrame(spriteSheet.getSprite(i), frameDuration);
        }
        return anim;
    }
}
