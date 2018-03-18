package com.mygdx.game.people;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.map.MapScreen;

public class Bot extends Person {

	public static final String toFilePath = "/home/robjan/eclipse-workspace/NewJavaProject/core/assets/bot.png";
	
	public Bot() {
		super(toFilePath,new Vector2(500,550));
		super.position.set(500,550);
	}
	
	
	
	
	@Override
	public void update(float delta) {
		//super.spriteBatch.setProjectionMatrix();
		super.update(delta);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
