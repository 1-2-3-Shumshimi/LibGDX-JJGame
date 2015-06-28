package com.mygdx.jjgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class JJGame extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture texture;
	Sprite sprite;
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		System.out.println(w); // these are equal to the screen's resolution
		System.out.println(h);
		
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		
		texture = new Texture("Red circle.png");
		
		System.out.println(texture.getWidth()); // these are equal to the image size (50x50 pixels)
		System.out.println(texture.getHeight());
		
		TextureRegion region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
		
		// so to create an image that you can manipulate... Texture -> TextureRegion -> Sprite
		sprite = new Sprite(region);
		//sprite.setSize(texture.getWidth(), texture.getHeight());
		sprite.setPosition(550, 360);
		
		//booo la la boo la la hoo hoo hoo hoo
		//testing testing 12345
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		//batch.draw(texture, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		batch.end();
	}
}
