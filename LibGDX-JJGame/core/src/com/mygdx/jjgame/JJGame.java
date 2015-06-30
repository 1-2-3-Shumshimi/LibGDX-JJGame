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
	Texture texture;
	Sprite sprite;
	Vector2 position;
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		System.out.println(w); // these are equal to the screen's resolution
		System.out.println(h);
		
		camera = new OrthographicCamera(w, h);
		camera.setToOrtho(true, w, h); // this line is to set the y-axis to point down
		
		batch = new SpriteBatch();
		
		texture = new Texture("Red circle.png");
		
//		System.out.println(texture.getWidth()); // these are equal to the image size (50x50 pixels)
//		System.out.println(texture.getHeight());
		
		TextureRegion region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
		
		// so to create an image that you can manipulate... Texture -> TextureRegion -> Sprite
		sprite = new Sprite(region);
		sprite.setOriginCenter(); // is not for draw() method though, only for rotations and stuff like that
		
		position = new Vector2(50, 50);
		
					
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		// basic orthogonal movement
		if (Gdx.input.isKeyPressed(Keys.W)){
			position.y += 5;
		} if (Gdx.input.isKeyPressed(Keys.A)){
			position.x -= 5;
		} if (Gdx.input.isKeyPressed(Keys.S)){
			position.y -= 5;
		} if (Gdx.input.isKeyPressed(Keys.D)){
			position.x += 5;
		}
		
		// "ghost mode" or just changing textures
		if (Gdx.input.isTouched()){
			texture = new Texture("Red circle transparent.png");
		} else {
			texture = new Texture("Red circle.png");
		}
		
		// teleporting
		if (Gdx.input.isKeyPressed(Keys.SPACE)){
			position.x = Gdx.input.getX(); 
			position.y = -Gdx.input.getY() + Gdx.graphics.getHeight(); 
			// this get() code is based on y-down, must adjust the y value	
		}
		
		batch.draw(texture, position.x - sprite.getOriginX(), position.y - sprite.getOriginY()); 
		// this draw() code is based on y-up, also adjusting for the sprite's origin
		
		batch.end();
	}
}
