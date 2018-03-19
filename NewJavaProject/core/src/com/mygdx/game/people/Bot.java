package com.mygdx.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.map.MapScreen;

public class Bot extends Person {

	public static final String toFilePath = "bot.png";

	public Bot() {
		super(toFilePath, new Vector2(500,500));
	}

	public void update(float delta) {
		super.update(delta);
		super.spriteBatch.begin();
		super.spriteBatch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
