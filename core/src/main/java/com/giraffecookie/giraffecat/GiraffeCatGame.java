package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class GiraffeCatGame extends BasicGame {

    public static final String TAG = GiraffeCatGame.class.getName();
	public static final String GAME_IDENTIFIER = "com.giraffecookie.giraffecat";
	public static final int MODEL_WIDTH = 285;
    public static final int MODEL_HEIGHT = 162;

	Level level;
	GiraffeCat gfc;

	@Override
    public void initialise() {
    	level = new Level();
    	gfc = new GiraffeCat(level.platforms);
    	level.addObject(gfc);
    	level.addObject(new Platform(64, 162-96, 64,16));
    	//level.addObject(new CameraController());
    	//level.addObject(new CameraWindow(gfc));
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
		level.render(g);
    }

    @Override
    public void resize(int width, int height) {
	    //For Other platforms like Android and IOs we need to detect how much larger
        //the screen is than the model width and height and adjust here
        super.resize(MODEL_WIDTH, MODEL_HEIGHT);
    }
}
