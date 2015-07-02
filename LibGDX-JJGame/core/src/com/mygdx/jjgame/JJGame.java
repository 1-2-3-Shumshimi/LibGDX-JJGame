package com.mygdx.jjgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class JJGame extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	Player player;
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		System.out.println(w); // these are equal to the screen's resolution
		System.out.println(h);
		
		camera = new OrthographicCamera(w, h);
		camera.setToOrtho(true, w, h); // this line is to set the y-axis to point down
		
		batch = new SpriteBatch();
		
		player = new Player(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2), "Red circle.png");
					
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		player.update();
		batch.draw(player.texture, player.position.x - player.sprite.getOriginX(), player.position.y - player.sprite.getOriginY()); 
		// this draw() code is based on y-up, also adjusting for the sprite's origin
		
		
		batch.end();
	}
}
