package com.mygdx.game.map;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Position;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.people.Bot;
import com.mygdx.game.people.Knight;
import com.mygdx.game.people.Person;

public class MapScreen implements Screen {

	public static final String TAG = MapScreen.class.getName();
	private static final String mapName = "eclipse-workspace/NewJavaProject/core/assets/map.tmx";
	private static final float ZOOM = 0.45f;
	public static final int startPositionX = 400;
	public static final int startPositionY = 1640;
	public static final float ZOOM_RATE = 0.8f;
	TiledMap tiledMap;
	OrthographicCamera camera;
	private OrthogonalTiledMapRenderer tiledMapRenderer;

	private int[] layerBottom = { 0 };
	private int[] layerTop = { 2 };

	private Knight knight;

	//Bot bot;
	boolean isZooming = false;

	// to change map
	HashMap<String, Vector2> mapsNameAndCoords;

	@Override
	public void show() {
		init(startPositionX, startPositionY);
		// knight.isCollideWithSecondLayer(tiledMapRenderer);
		mapsNameAndCoords = new HashMap<>();
		mapsNameAndCoords.put(mapName, new Vector2(startPositionX, startPositionY));
		mapsNameAndCoords.put(mapName, new Vector2(690, 1100));
	}

	// initialize variable
	private void init(float posX, float posY) {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		tiledMap = new TmxMapLoader(new ExternalFileHandleResolver()).load(mapName);
		setTiledMapRenderer(new OrthogonalTiledMapRenderer(tiledMap));
		knight = new Knight(camera);
		// bot = new Bot();
		camera.zoom = ZOOM;
		camera.position.set(posX, posY, 0);
		camera.update();
	}

	@Override
	public void render(float delta) {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		getTiledMapRenderer().setView(camera);
		getTiledMapRenderer().render(layerBottom);
		knight.update(delta, this);
		// bot.update(delta);

		// knight.isCollideWithSecondLayer(this);

		getTiledMapRenderer().render(layerTop);

		// Zoom out effect and reseting map
		if (isEndOfGame(knight, getTiledMapRenderer())) {
			long endTime = TimeUtils.nanoTime();
			isZooming = false;
			// Do poprawy kamera
			// camera.zoom += ZOOM_RATE;
			while (!isZooming) {
				if (TimeUtils.timeSinceNanos(endTime) > 1000000000) {
					init(startPositionX, startPositionY);
					isZooming = true;
					endTime = TimeUtils.nanoTime();
				}
			}
		}
	}

	// function to save map !!
	public void saveMap() {
	}

	@Override
	public void resize(int width, int height) {
		camera.update();
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

	public boolean isEndOfGame(Person person, OrthogonalTiledMapRenderer mapRenderer) {
		// get first layer and calculate center of the map
		TiledMapTileLayer layer = (TiledMapTileLayer) mapRenderer.getMap().getLayers().get(0);
		float width = layer.getTileWidth() * layer.getWidth();
		float height = layer.getTileHeight() * layer.getHeight();
		// is in "circle"
		if (person.getPosition().dst(width / 2, height / 2) < 10) {
			return true;
		}

		// pozycja posągu (690,1100)
		// cos nie dziala, ma byc w okregu o promieniu R = 20
		if (((Math.pow(person.getPosition().x - 690, 2)) + (Math.pow(person.getPosition().y - 1100, 2))) < 50) {
			camera.zoom += 0.3;
			return true;
		}
		return false;
	}

	@Override
	public void dispose() {
		tiledMap.dispose();
		tiledMapRenderer.dispose();
		knight.dispose();
		//bot.dispose();
	}

	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return tiledMapRenderer;
	}

	public void setTiledMapRenderer(OrthogonalTiledMapRenderer tiledMapRenderer) {
		this.tiledMapRenderer = tiledMapRenderer;
	}
	public Knight getKnight() {
		return knight;
	}

	public void setKnight(Knight knight) {
		this.knight = knight;
	}
}
