package com.mygdx.jjgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends Ball{

	public boolean isGhost;

	public Player(Vector2 position, String textureLoc){
		super(position, 0, 0, true, textureLoc);
		
		// Reset the velocity from the super class - now an unchanging velocity in any direction
		velocity.x = 4;
		velocity.y = 4;
		
		isGhost = false;
		type = 0;
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
			isGhost = true;
		} else {
			texture = new Texture("Red circle.png");
			isGhost = false;
		}

		// teleporting
		if (Gdx.input.isTouched()){
			position.x = Gdx.input.getX(); 
			position.y = -Gdx.input.getY() + Gdx.graphics.getHeight(); 
			// this get() code is based on y-down, must adjust the y value	
		}
		checkWallPlayer();
	}
	
	private void checkWallPlayer(){
		if (position.x <= sprite.getWidth()/2){
			position.x = sprite.getWidth()/2;
		}
		else if (position.x + sprite.getWidth()/2 >= Gdx.graphics.getWidth()){ 
			position.x = Gdx.graphics.getWidth() - sprite.getWidth()/2;
		}
		
		if (position.y <= sprite.getHeight()/2){
			position.y = sprite.getHeight()/2;
		}
		else if (position.y + sprite.getHeight()/2 >= Gdx.graphics.getHeight()){ 
			position.y = Gdx.graphics.getHeight() - sprite.getHeight()/2;
		}
	}

	@Override
	public void getPlayerInfo(Player player) {
		// Do nothing...for now
		// Perhaps if we have multiplayer mode this might come in handy
		
	}


}
