package com.mygdx.jjgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Ball {

	Vector2 position;
	Vector2 velocity;
	float magnitude;
	Texture texture;
	Sprite sprite;
	int type; 
	// 0 for player, 1 for good blue, 2 for bad green, and more to come!
	boolean isGood;
	// determines whether collisions result in a positive or negative score
	int points;
	int collisionCount;
	int maxCollisionCount;
	
	protected Ball(Vector2 position, float magnitude, int maxCollisionCount, boolean isGood, String textureLoc){
		this.position = position;
		this.texture = new Texture(textureLoc);
		this.sprite = new Sprite(new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()));
		sprite.setOriginCenter(); // is not for draw() method though, only for rotations and stuff like that
		
		// set initial velocity vector - have the magnitude be 4, but a random direction
		this.magnitude = magnitude;
		velocity = new Vector2();
		velocity.x = (int)(Math.random()*this.magnitude);
		velocity.y = (float) Math.sqrt(16 - (velocity.x * velocity.x));
		
		collisionCount = 0;
		this.maxCollisionCount = maxCollisionCount;
		
		this.isGood = isGood;
	}
	
	// Usually called in the render method of the game class - updates position and other features specific to the ball
	public abstract void update();
	
	// A method to allow balls to get info on the player. Used to implement any special characteristics a ball might have.
	public abstract void getPlayerInfo(Player player);
	
	/**
	 * Checks whether the two balls are close enough to be consider collided.
	 * @param ball
	 * @return
	 */
	public boolean hasCollided(Ball ball){
		
		float xDelta = position.x - ball.position.x;
		float yDelta = position.y - ball.position.y;
		
		float sumRadius = sprite.getHeight()/2 + ball.sprite.getHeight()/2; // radius being half the height (or length since it's a square)
		float sqrRadius = sumRadius * sumRadius;
		
		float distSqr = (xDelta * xDelta) + (yDelta * yDelta);
		
		if (distSqr <= sqrRadius)
			return true;
		
		return false;
	}
	
	/**
	 * Assumes that two balls have collided. Updates their velocity vectors and makes changes to their position
	 * in accordance to the minimum translation distance after collision. Assumes that the balls are the same mass
	 * and that there is perfect elastic collision.
	 * @param ball
	 */
	public void resolveCollision(Ball ball){
		
		Vector2 delta = new Vector2(position.x-ball.position.x, position.y-ball.position.y);
		float d = delta.len();
		velocity = delta.scl(magnitude/d); // maintain the magnitude on velocities because we assume perfect elastic collision
		ball.velocity = new Vector2(-velocity.x, -velocity.y);
		

	}
}
