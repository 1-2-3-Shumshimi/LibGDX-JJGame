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
	Ball[] balls;
	int score;
	
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
		
		balls = new Ball[12];
		
		for (int i = 0; i < balls.length - 1; i++){
			balls[i] = new GoodBallBlue(new Vector2(i*Gdx.graphics.getWidth()/balls.length, (float) Math.random()*i*Gdx.graphics.getHeight()/balls.length));
			// creates a new BadBallBlue at a position directly proportional to the index - done to make the balls spread out
		}
		balls[balls.length-1] = new BadBallGreen(new Vector2(Gdx.graphics.getWidth()/balls.length, (float) Math.random()*Gdx.graphics.getHeight()/balls.length));
		
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		player.update();
		batch.draw(player.texture, player.position.x - player.sprite.getOriginX(), player.position.y - player.sprite.getOriginY());
		// this draw() code is based on y-up, also adjusting for the sprite's origin
		
		for (int i = 0; i < balls.length; i++){
			balls[i].getPlayerInfo(player);
			balls[i].update();
		}
		checkBallCollisions();
		if (!player.isGhost)
			checkPlayerCollisions();
		checkWallCollisions();
		
		for (int i = 0; i < balls.length; i++){
			batch.draw(balls[i].texture, balls[i].position.x - balls[i].sprite.getOriginX(), balls[i].position.y - balls[i].sprite.getOriginY());
		}
		
		batch.end();
	}
	
	private void checkBallCollisions(){
		for (int i = 0; i < balls.length - 1; i++){
			for (int j = i + 1; j < balls.length; j++){
				if (balls[i].hasCollided(balls[j]))
					balls[i].resolveCollision(balls[j]);
			}
		}
	}
	
	private void checkPlayerCollisions(){
		for (int i = 0; i < balls.length; i++){
			if (balls[i].hasCollided(player)){
				Vector2 delta = new Vector2(balls[i].position.x-player.position.x, balls[i].position.y-player.position.y);
				float d = delta.len();
				balls[i].velocity = delta.scl(balls[i].magnitude/d);
				// maintain the magnitude on velocities because we assume perfect elastic collision
				player.position.sub(balls[i].velocity);
				
				if (balls[i].isGood){
					score += balls[i].points;
				} else {
					score -= balls[i].points;
				}
				
				balls[i].collisionCount++;
				if (balls[i].collisionCount >= balls[i].maxCollisionCount)
					balls[i] = makeNewRandomBall(balls[i].position);
			}
		}
	}
	
	private void checkWallCollisions(){
		for (int i = 0; i < balls.length; i++){
			if (balls[i].position.x <= balls[i].sprite.getWidth()/2){
				balls[i].position.x = balls[i].sprite.getWidth()/2;
				balls[i].velocity.x = -balls[i].velocity.x;
			}
			else if (balls[i].position.x + balls[i].sprite.getWidth()/2 >= Gdx.graphics.getWidth()){ 
				balls[i].position.x = Gdx.graphics.getWidth() - balls[i].sprite.getWidth()/2;
				balls[i].velocity.x = -balls[i].velocity.x;
			}
			
			if (balls[i].position.y <= balls[i].sprite.getHeight()/2){
				balls[i].position.y = balls[i].sprite.getHeight()/2;
				balls[i].velocity.y = -balls[i].velocity.y;
			}
			else if (balls[i].position.y + balls[i].sprite.getHeight()/2 >= Gdx.graphics.getHeight()){ 
				balls[i].position.y = Gdx.graphics.getHeight() - balls[i].sprite.getHeight()/2;
				balls[i].velocity.y = -balls[i].velocity.y;
			}
		}
	}
	
	private Ball makeNewRandomBall(Vector2 position){
		int numGood = 0;
		int numBad = 0;
		for (int i = 0; i < balls.length; i++){
			if (balls[i].type == 1){
				numGood++;
			} else if (balls[i].type == 2){
				numBad++;
			}
		}
		double random = Math.random()*100;
		if (random < numBad*100 / (numGood + balls.length)){
			return new GoodBallBlue(position);
		} else {
			return new BadBallGreen(position);
		}
		
	}
}
