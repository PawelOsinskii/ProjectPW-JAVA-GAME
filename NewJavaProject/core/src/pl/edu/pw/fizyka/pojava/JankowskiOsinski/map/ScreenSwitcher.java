package pl.edu.pw.fizyka.pojava.JankowskiOsinski.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input.Keys;

public class ScreenSwitcher extends InputAdapter {

	Game game;
	MapScreen screenMap;
	Screen statsScreen;
	int currentScreen;
	Vector2 posCamera;
	Vector2 posPlayer;
	
	

	public ScreenSwitcher(Game game, MapScreen screenMap, Screen statsScreen) {
		this.game = game;
		this.screenMap = screenMap;
		this.statsScreen = statsScreen;
		currentScreen = 1;
	}

	// Use I to change screens !
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.I) {
			if (currentScreen == 1) {
				currentScreen = 2;
				posCamera = new Vector2(screenMap.camera.position.x, screenMap.camera.position.y);
				posPlayer = new Vector2(screenMap.getKnight().getPosition().x, screenMap.getKnight().getPosition().y);
				game.setScreen(statsScreen);
			} else {
				currentScreen = 1;
				game.setScreen(screenMap);
				screenMap.camera.position.set(new Vector3(posCamera.x, posCamera.y, 0));
				screenMap.getKnight().getPosition().set(posPlayer);

			}
		}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// tu sprawdzam czy nacisnalem dragon, ale wymaga poprawy,bo sa inne wspolrzedne !!
		if(screenX == screenMap.bots.position("dragon").x) {
			System.out.println("yeah");
		}
		System.out.println(screenMap.bots.position("d"));
		return false;
	}
}
