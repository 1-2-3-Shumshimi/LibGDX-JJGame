package com.mygdx.jjgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Level {

	Player player;
	Obstacle[] obstacles;
	int score;
	int lives;

	public Level(){
		player = new Player(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2), "Red circle.png");
	}
	
	/**
	 * A render method separate from the main render method of the Game.
	 * This allows levels to be drawn in their own way.
	 */
	public void renderHelp(){
		
	}
}

