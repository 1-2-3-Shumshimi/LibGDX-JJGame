package com.mygdx.jjgame;

import com.badlogic.gdx.math.Vector2;

public class BadBallGreen extends Ball{

	Vector2 playerPosition;
	int trackerCount;
	
	protected BadBallGreen(Vector2 position) {
		super(position, 3, 10, false, "Green circle small.png");
		type = 2;
		points = 50;
		trackerCount = 0;
		playerPosition = new Vector2();
	}

	@Override
	public void update() {
		
		
		if (trackerCount % 10 == 0){
			Vector2 delta = new Vector2(position.x-playerPosition.x, position.y-playerPosition.y);
			float d = delta.len();
			velocity = delta.scl(-magnitude/d);
		}
		
		position.x += velocity.x;
		position.y += velocity.y;
		trackerCount++;
		
	}

	@Override
	public void getPlayerInfo(Player player) {
		// Green ball wants the player's position so it can follow it around.
		playerPosition = player.position;
	}

}
