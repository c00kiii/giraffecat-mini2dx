package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class GiraffeCatGame extends BasicGame {

    public static final String TAG = GiraffeCatGame.class.getName();
	public static final String GAME_IDENTIFIER = "com.giraffecookie.giraffecat";
	public static final int MODEL_WIDTH = 240;
    public static final int MODEL_HEIGHT = 135;

	private Texture texture;

	Level level;

	@Override
    public void initialise() {
    	texture = new Texture(ImgPath.MINI2DX);
    	level = new Level();
    }
    
    @Override
    public void update(float delta) {
        level.update(delta);
    }
    
    @Override
    public void interpolate(float alpha) {
    }
    
    @Override
    public void render(Graphics g) {
		g.drawTexture(texture, 0f, 0f);
		level.render(g);
    }

    @Override
    public void resize(int width, int height) {
	    //For Other platforms like Android and IOs we need to detect how much larger
        //the screen is than the model width and height and adjust here
        super.resize(MODEL_WIDTH, MODEL_HEIGHT);
    }
}
