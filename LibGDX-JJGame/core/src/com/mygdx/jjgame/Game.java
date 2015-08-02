package com.mygdx.jjgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter {

	OrthographicCamera camera;
	SpriteBatch batch;
	Level[] levels;
	Level chosenLevel;
	
	public void create(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		camera.setToOrtho(true, w, h); // this line is to set the y-axis to point down
		
		batch = new SpriteBatch();
		
		int numLevels = 1;
		createLevels(numLevels);
		chosenLevel = levels[0];
		
	}
	
	public void render(){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		chosenLevel.renderHelp();
		
		batch.end();
			
	}
	
	public void createLevels(int numLevels){
		levels = new Level[numLevels];
		// Maybe consider a level creator class when we start having a lot of different 
		// complex levels...
		levels[0] = new Level();
	}
}
