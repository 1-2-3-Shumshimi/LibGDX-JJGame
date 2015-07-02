package com.mygdx.jjgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Ball {

	Vector2 position;
	Vector2 velocity;
	Texture texture;
	Sprite sprite;
	
	protected Ball(Vector2 position, String textureLoc){
		this.position = position;
		this.texture = new Texture(textureLoc);
		this.sprite = new Sprite(new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight()));
		sprite.setOriginCenter(); // is not for draw() method though, only for rotations and stuff like that
		
		// set initial velocity vector - have the magnitude be 4, but a random direction
		velocity.x = (int)(Math.random()*5);
		velocity.y = (float) Math.sqrt(16 - (velocity.x * velocity.x));
	}
	
	public abstract void update();
	
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
		
		// get the minimum translational difference (mtd)
		Vector2 delta = (position.sub(ball.position));
	    float d = delta.len();
	    // minimum translation distance to push balls apart after intersecting
	    float multiplyFactor = (float) (0.5*(((sprite.getHeight()/2 + ball.sprite.getHeight()/2)-d)/d));
	    Vector2 mtd = new Vector2(delta.x * multiplyFactor, delta.y * multiplyFactor);
	    
	    // update positions
	    position = position.add(mtd);
	    ball.position = position.sub(mtd);
	    
	    // impact speed
	    Vector2 v = (this.velocity.sub(ball.velocity));
	    float vn = v.dot(mtd.nor()); 

	    // sphere intersecting but moving away from each other already
	    if (vn > 0.0f) return;

	    // collision impulse
	    float i = -vn; // simplified due to assuming perfect collision and equal masses
	    Vector2 impulse = new Vector2(mtd.x*i, mtd.y*i);

	    // change in momentum
	    this.velocity = this.velocity.add(impulse);
	    ball.velocity = ball.velocity.sub(impulse);

	}
}
