package com.mygdx.jjgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

	private static final long serialVersionUID = 1L;
	Vector2 position;
	Texture texture;
	Sprite sprite;

	public Player(Vector2 position, String textureLoc){
		this.position = position;
		this.texture = new Texture(textureLoc);
		this.sprite = new Sprite(new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()));
		sprite.setOriginCenter(); // is not for draw() method though, only for rotations and stuff like that
	}

	public void update(){
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
