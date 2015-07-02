package com.mygdx.jjgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends Ball{

	private static final long serialVersionUID = 1L;

	public Player(Vector2 position, String textureLoc){
		super(position, textureLoc);
		
		// Reset the velocity from the super class - now an unchanging velocity in any direction
		velocity.x = 5;
		velocity.y = 5;
	}

	public void update(){
		// basic orthogonal movement
		if (Gdx.input.isKeyPressed(Keys.W)){
			position.y += velocity.y;
		} if (Gdx.input.isKeyPressed(Keys.A)){
			position.x -= velocity.x;
		} if (Gdx.input.isKeyPressed(Keys.S)){
			position.y -= velocity.y;
		} if (Gdx.input.isKeyPressed(Keys.D)){
			position.x += velocity.x;
		}

		// "ghost mode" or just changing textures
		if (Gdx.input.isKeyPressed(Keys.SPACE)){
			texture = new Texture("Red circle transparent.png");
		} else {
			texture = new Texture("Red circle.png");
		}

		// teleporting
		if (Gdx.input.isTouched()){
			position.x = Gdx.input.getX(); 
			position.y = -Gdx.input.getY() + Gdx.graphics.getHeight(); 
			// this get() code is based on y-down, must adjust the y value	
		}
	}


}
