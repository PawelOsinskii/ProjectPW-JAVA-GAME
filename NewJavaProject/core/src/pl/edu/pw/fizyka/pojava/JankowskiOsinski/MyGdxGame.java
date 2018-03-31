package pl.edu.pw.fizyka.pojava.JankowskiOsinski;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.MapScreen;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.ScreenSwitcher;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.StatsScreen;

public class MyGdxGame extends Game {

	MapScreen mapScreen;
	StatsScreen statsScreen;

	@Override
	public void create() {
		mapScreen = new MapScreen();
		statsScreen = new StatsScreen(mapScreen);
		this.setScreen(mapScreen);
		Gdx.input.setInputProcessor(new ScreenSwitcher(this, mapScreen, statsScreen));
	}
	
 
	@Override
	public void dispose() {
		super.dispose();
	}
	
}
