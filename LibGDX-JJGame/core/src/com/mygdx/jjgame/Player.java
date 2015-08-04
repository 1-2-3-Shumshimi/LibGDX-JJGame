package com.mygdx.jjgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Player {
	
	Vector2 position;
	Vector2 velocity;
	
	// Helping accelerometer
	long startTime;
	long elapsedTime;
	
	Texture texture;
	Sprite sprite;
	
	public Player(Vector2 position, String textureLoc){
		this.position = position;
		this.texture = new Texture(textureLoc);
		this.sprite = new Sprite(new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()));
		sprite.setOriginCenter(); // is not for draw() method though, only for rotations and stuff like that
		
		this.velocity = new Vector2(3, 3);
		startTime = TimeUtils.millis(); // want seconds
		elapsedTime = startTime;
	}
	
	/**
	 * Updates the player's position and/or velocity. Called on in the game's render method.
	 */
	public void update(){
		
		// Checks if the player is on a device that uses the accelerometer
		boolean accelAvailable = Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer);
		
		if (accelAvailable){
			float accelX = Gdx.input.getAccelerometerX(); // axis left-right
		    float accelY = Gdx.input.getAccelerometerY(); // axis top-down
		    //float accelZ = Gdx.input.getAccelerometerZ(); // axis front-back
		    if (accelX == 0 || accelY == 0){
		    	startTime = TimeUtils.millis(); // acceleration has stopped, reset count
		    	elapsedTime = startTime; 
		    } else {
		    	elapsedTime = TimeUtils.timeSinceMillis(startTime);
		    	position.x -= accelX * velocity.x * elapsedTime/1000;
		    	position.y -= accelY * velocity.y * elapsedTime/1000;
		    }
		} else {
			// basic orthogonal movement with key presses
			if (Gdx.input.isKeyPressed(Keys.W)){
				position.y += velocity.y;
			} if (Gdx.input.isKeyPressed(Keys.A)){
				position.x -= velocity.x;
			} if (Gdx.input.isKeyPressed(Keys.S)){
				position.y -= velocity.y;
			} if (Gdx.input.isKeyPressed(Keys.D)){
				position.x += velocity.x;
			}
		}
		
		checkBoundPlayer();
	    
	}
	
	/**
	 * Checks if the player will reach the edge of the screen and halts its movement.
	 * NOTE: IF WE MAKE THE CAMERA FOLLOW THE BALL AROUND, MIGHT NEED TO CHANGE THIS METHOD
	 */
	private void checkBoundPlayer(){
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
}
