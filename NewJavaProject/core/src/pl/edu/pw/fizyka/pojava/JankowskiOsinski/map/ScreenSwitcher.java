package pl.edu.pw.fizyka.pojava.JankowskiOsinski.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input.Keys;

public class ScreenSwitcher extends InputAdapter {

	Game game;
	MapScreen mapScreen;
	Screen statsScreen;
	int currentScreen;
	Vector2 posCamera;
	Vector2 posPlayer;

	public ScreenSwitcher(Game game, MapScreen screenMap, Screen statsScreen) {
		this.game = game;
		this.mapScreen = screenMap;
		this.statsScreen = statsScreen;
		currentScreen = 1;
	}

	// Use I to change screens
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.I) {
			if (currentScreen == 1) {
				currentScreen = 2;
				posCamera = new Vector2(mapScreen.camera.position.x, mapScreen.camera.position.y);
				posPlayer = new Vector2(mapScreen.getKnight().getPosition().x, mapScreen.getKnight().getPosition().y);
				game.setScreen(statsScreen);
			} else {
				currentScreen = 1;
				game.setScreen(mapScreen);
				mapScreen.camera.position.set(new Vector3(posCamera.x, posCamera.y, 0));
				mapScreen.getKnight().getPosition().set(posPlayer);
			}
		}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// ok działa sprawdza pozycje myszki i dragona !!
		Vector3 worldCoords = new Vector3(screenX, screenY, 0);
		mapScreen.camera.unproject(worldCoords);
		mapScreen.bots.position(0).x = MapScreen.MAP_WIDTH - MapScreen.TILE_SIZE - mapScreen.bots.position(0).x;
		for (int i = 0; i < mapScreen.bots.getMonsterList().size(); i++) {
			if (Math.abs(worldCoords.x - mapScreen.bots.position(i).x) < MapScreen.TILE_SIZE) {
				// zwiększenie attack lvl za kazdym nasicnieciem na dragona
				mapScreen.getKnight().setAttackLevel(mapScreen.getKnight().getAttackLevel() + 1);
			}
		}

		return false;
	}
}
