package pl.edu.pw.fizyka.pojava.JankowskiOsinski.map;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.Constants;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.people.Bot;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.people.Knight;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.people.Person;

public class MapScreen implements Screen {

	public static final String TAG = MapScreen.class.getName();
	private boolean isFirstMap = true;

	public static float MAP_HEIGHT;
	public static float MAP_WIDTH;
	public static float TILE_SIZE;

	TiledMap tiledMap;
	OrthographicCamera camera;
	TextureMapObjectRenderer tiledMapRenderer;

	private int[] layerBottom = { 0 };
	private int[] layerTop = { 3 };

	private Knight knight;
	public Bot bots;

	boolean isFirstInit = true;
	boolean isZooming = false;

	// to change map
	HashMap<String, Vector2> mapsNameAndCoords;

	int i=1;
	@Override
	public void show() {
		// aby nie resetowały się statystyki bohatera 
		if (isFirstInit) {
			init(Constants.startPositionX, Constants.startPositionY, Constants.mapName);
			isFirstInit = false;
		}
		// knight.isCollideWithSecondLayer(tiledMapRenderer);
		mapsNameAndCoords = new HashMap<>();
		mapsNameAndCoords.put(Constants.mapName, new Vector2(Constants.startPositionX, Constants.startPositionY));
		mapsNameAndCoords.put(Constants.mapName, new Vector2(690, 1100));
	}

	// initialize variable
	private void init(float posX, float posY, String map) {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		tiledMap = new TmxMapLoader().load(map);
		tiledMapRenderer = new TextureMapObjectRenderer(tiledMap);
		knight = new Knight(camera);
		bots = new Bot(tiledMapRenderer);

		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMapRenderer.getMap().getLayers().get(0);
		MAP_WIDTH = layer.getTileWidth() * layer.getWidth();
		MAP_HEIGHT = layer.getTileHeight() * layer.getHeight();
		TILE_SIZE = layer.getTileHeight();

		camera.zoom = Constants.ZOOM;
		camera.position.set(posX, posY, 0);
		camera.update();
	}

	@Override
	public void render(float delta) {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tiledMapRenderer.setView(camera);
		if (isFirstMap) {
			tiledMapRenderer.render(layerBottom);
			knight.update(delta, this);
			bots.update(delta);
			// knight.isCollideWithSecondLayer(this);
			tiledMapRenderer.render(layerTop);
		}
		// tu teleport do innej mapy !
		if (!isFirstMap) {
			// nie ma pliku terrain_atlas.png -> Pawel wyśle później
			tiledMapRenderer.render();
		}
		// Zoom out effect and reseting map
		if (isEndOfGame(knight, getTiledMapRenderer())) {
			long endTime = TimeUtils.nanoTime();
			isZooming = false;
			while (!isZooming) {
				if (TimeUtils.timeSinceNanos(endTime) > 10000000) {
					init(Constants.startPositionX, Constants.startPositionY, Constants.nextMapName);
					isFirstMap = false;
					isZooming = true;
					endTime = TimeUtils.nanoTime();
				}
			}
		}

	}

	// function to save map,not finished yet
	public void saveMap() {
	}

	@Override
	public void resize(int width, int height) {
		camera.update();
	}

	// check player position on the map
	public boolean isEndOfGame(Person person, OrthogonalTiledMapRenderer mapRenderer) {
		// get first layer and calculate center of the map
		TiledMapTileLayer layer = (TiledMapTileLayer) mapRenderer.getMap().getLayers().get(0);
		float width = layer.getTileWidth() * layer.getWidth();
		float height = layer.getTileHeight() * layer.getHeight();
		// is in "circle"
		if (person.getPosition().dst(width / 2, height / 2) < 10) {
			return true;
		}

		// pozycja posągu (650,1140) + 32
		// cos nie dziala, ma byc w okregu o promieniu R = 50
		if (((Math.pow(person.getPosition().x - 650, 2)) + (Math.pow(person.getPosition().y - 1140, 2))) < 60
				|| ((Math.pow(person.getPosition().x - 620, 2)) + (Math.pow(person.getPosition().y - 1172, 2))) < 60
				|| ((Math.pow(person.getPosition().x - 620, 2)) + (Math.pow(person.getPosition().y - 1140, 2))) < 60
				|| ((Math.pow(person.getPosition().x - 650, 2)) + (Math.pow(person.getPosition().y - 1172, 2))) < 60) {
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

	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return tiledMapRenderer;
	}

	public void setTiledMapRenderer(TextureMapObjectRenderer tiledMapRenderer) {
		this.tiledMapRenderer = tiledMapRenderer;
	}

	public Knight getKnight() {
		return knight;
	}

	public void setKnight(Knight knight) {
		this.knight = knight;
	}

}
