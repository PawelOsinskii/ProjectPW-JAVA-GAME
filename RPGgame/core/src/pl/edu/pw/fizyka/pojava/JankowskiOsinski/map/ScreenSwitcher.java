package pl.edu.pw.fizyka.pojava.JankowskiOsinski.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.Constants;

public class ScreenSwitcher extends InputAdapter {

	Game game;
	MapScreen mapScreen;
	StatsScreen statsScreen;
	Shop shop;
	int currentScreen = Constants.MAP_SCREEN;
	Vector2 posCamera;
	Vector2 posPlayer;

	public ScreenSwitcher(Game game, MapScreen screenMap, StatsScreen statsScreen, Shop shop) {
		this.game = game;
		this.mapScreen = screenMap;
		this.statsScreen = statsScreen;
		this.shop = shop;
	}

	// Use 'I' to change screens
	// Use 'Space' to go to the shop
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.I) {
			if (currentScreen == Constants.MAP_SCREEN) {
				currentScreen = Constants.STATS_SCREEN;
				posCamera = new Vector2(mapScreen.camera.position.x, mapScreen.camera.position.y);
				posPlayer = new Vector2(mapScreen.getKnight().getPosition().x, mapScreen.getKnight().getPosition().y);
				game.setScreen(statsScreen);
			} else if (currentScreen == Constants.STATS_SCREEN) {
				currentScreen = Constants.MAP_SCREEN;
				game.setScreen(mapScreen);
				mapScreen.camera.position.set(new Vector3(posCamera.x, posCamera.y, 0));
				mapScreen.getKnight().getPosition().set(posPlayer);
			}
		}
		if (keycode == Keys.SPACE) {
			if (currentScreen == Constants.MAP_SCREEN) {
				game.setScreen(shop);
				currentScreen = Constants.SHOP_SCREEN;
			} else if (currentScreen == Constants.SHOP_SCREEN) {
				game.setScreen(mapScreen);
				currentScreen = Constants.MAP_SCREEN;
			}
		}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (currentScreen == Constants.MAP_SCREEN) {
			float x1 = mapScreen.tiledMapRenderer.textureMonsters.get(0).getX();
			float y1 = mapScreen.tiledMapRenderer.textureMonsters.get(0).getY();
			float x2 = mapScreen.tiledMapRenderer.textureMonsters.get(1).getX();
			float y2 = mapScreen.tiledMapRenderer.textureMonsters.get(1).getY();
			// here I have to make unproject camera, to get map coords
			System.out.println(x1 + " " + y1 + "," + x2 + " " + y2 + " :mouse" + screenX + " " + screenY);
		}
		return false;
	}

}
