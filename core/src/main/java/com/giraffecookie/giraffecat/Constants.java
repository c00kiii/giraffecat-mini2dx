package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    public static final Color BACKGROUND_COLOR = Color.SKY;

    public static final float WORLD_SIZE = GiraffeCatGame.MODEL_WIDTH;

    public static final Vector2 GFC_EYE_POSITION = new Vector2(16, 24);
    public static final float GFC_EYE_HEIGHT = 16.0f;

    public static final float GFC_MOVE_SPEED = WORLD_SIZE / 2;

    public static final float MAX_JUMP_DURATION = .10f;

    public static final float GRAVITY = 12;
}
