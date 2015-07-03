package com.mygdx.jjgame;

import com.badlogic.gdx.math.Vector2;

public class GoodBallBlue extends Ball{

	protected GoodBallBlue(Vector2 position) {
		super(position, 6, 15, true, "Blue circle small.png");
		type = 1;
		points = 100;
		
	}

	@Override
	public void update() {
		position.x += velocity.x;
		position.y += velocity.y;
		
	}

	@Override
	public void getPlayerInfo(Player player) {
		// Do nothing because blue ball is a boring ball that just bounces like a ball...ball
	}
	
	

}
