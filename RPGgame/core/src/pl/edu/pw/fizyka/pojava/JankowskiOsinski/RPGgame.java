package pl.edu.pw.fizyka.pojava.JankowskiOsinski;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.MapScreen;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.ScreenSwitcher;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.StatsScreen;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.ui.Menu;

public class RPGgame extends Game {

	MapScreen mapScreen;
	StatsScreen statsScreen;
	Menu menu;

	@Override
	public void create() {
		menu = new Menu(this);
		this.setScreen(menu);
	}

	public void loadGame() {
		mapScreen = new MapScreen();
		statsScreen = new StatsScreen(mapScreen);
		this.setScreen(mapScreen);
		Gdx.input.setInputProcessor(new ScreenSwitcher(this, mapScreen, statsScreen));
	}
}
