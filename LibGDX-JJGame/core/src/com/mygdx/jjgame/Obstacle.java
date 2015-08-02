package com.mygdx.jjgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Obstacle {

	Vector2 position;
	Texture texture;
	Sprite sprite;
	
	protected Obstacle(Vector2 position, String textureLoc){
		this.position = position;
		try {
			this.texture = new Texture(textureLoc);
			this.sprite = new Sprite(new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()));
			sprite.setOriginCenter(); // is not for draw() method though, only for rotations and stuff like that
		} catch (Exception e){
			// Do nothing - will have to be set by the children classes
		}
	}
	
	// Usually called in the render method of the game class - updates position and other features specific to the obstacle
	public abstract void update();
	
	// Assumes that the obstacle has collided with the Player. Changes the Player's position, velocity, etc. depending on the 
	// characteristics of the obstacle in question.
	public abstract void resolveCollision(Player player);
	
	/**
	 * Checks whether the player and the obstacle are close enough to be considered as collided.
	 * @param player
	 * @return
	 */
	public boolean hasCollided(Player player){
		float xDelta = position.x - player.position.x;
		float yDelta = position.y - player.position.y;
		
		float sumRadius = sprite.getHeight()/2 + player.sprite.getHeight()/2; // radius being half the height (or length since it's a square)
		float sqrRadius = sumRadius * sumRadius;
		
		float distSqr = (xDelta * xDelta) + (yDelta * yDelta);
		
		if (distSqr <= sqrRadius)
			return true;
		
		return false;
	}
	
}
