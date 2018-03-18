package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.map.MapScreen;
import com.mygdx.game.map.ScreenSwitcher;
import com.mygdx.game.map.StatsScreen;

public class MyGdxGame extends Game {

	MapScreen mapScreen;
	StatsScreen statsScreen;

	@Override
	public void create() {
		mapScreen = new MapScreen();
		statsScreen = new StatsScreen();
		this.setScreen(mapScreen);
		Gdx.input.setInputProcessor(new ScreenSwitcher(this, mapScreen, statsScreen));
	}
	
 
	@Override
	public void dispose() {
		super.dispose();
	}
	
}
