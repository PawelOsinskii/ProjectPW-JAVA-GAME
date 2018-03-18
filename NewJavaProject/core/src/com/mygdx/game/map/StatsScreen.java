package com.mygdx.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.people.Person;

public class StatsScreen implements Screen {

	public static final String TAG = StatsScreen.class.getName();

	private static final float FONT_SCALE = 2;
	private static final float xOffset = 250;
	private static final float yOffset = 30;

	
	String statsString;

	BitmapFont font;
	SpriteBatch textDisplay;

	@Override
	public void show() {
		font = new BitmapFont();
		textDisplay = new SpriteBatch();
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.getData().setScale(FONT_SCALE);
		
		// here i have to get to know person from the main screen
		statsString = "Gold : " + Person.GOLD_START;
		statsString += "\nHP : " + Person.HP_START + "\nMana : " + Person.MANA_START;
		statsString += "\nAttack level : " + Person.ATTACK_LEVEL_START + "\nMagic level : " + Person.MAGIC_LEVEL_START;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		textDisplay.begin();
		font.draw(textDisplay, statsString, Gdx.graphics.getWidth() / 4 - xOffset, Gdx.graphics.getHeight() - yOffset,
				5, Align.left, false);
		textDisplay.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		textDisplay.dispose();
	}

}
