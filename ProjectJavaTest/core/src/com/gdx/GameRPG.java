package com.gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameRPG extends ApplicationAdapter implements InputProcessor {
	private static final String TAG = GameRPG.class.getName();
	TiledMap map;
	OrthogonalTiledMapRenderer render;
	OrthographicCamera camera;
	ShapeRenderer renderer;
	SpriteBatch sb;
	Texture img;
	Texture texture;
	Sprite sprite;
	MapProperties mapProp;

	@Override
	public void create() {
		String mapS = "";
		mapProp = new MapProperties();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		renderer = new ShapeRenderer();
		FileHandle mapFile = Gdx.files.external("projekt.tmx");
		mapFile.writeString(mapS, false);
		map = new TmxMapLoader(new ExternalFileHandleResolver())
				.load("eclipse-workspace/ProjectJava/core/assets/projekt.tmx");
		render = new OrthogonalTiledMapRenderer(map, 2f);
		mapProp = map.getProperties();

		int mapWidth = mapProp.get("width", Integer.class);
		int mapHeight = mapProp.get("height", Integer.class);
		int tilePixelWidht = mapProp.get("tilewidth", Integer.class);
		int tilePixelHeight = mapProp.get("tileheight", Integer.class);
		int mapPixelWidht = mapWidth * tilePixelWidht;
		int mapPixelHeight = mapHeight * tilePixelHeight;
		Gdx.app.log(TAG, mapPixelHeight + " " + mapPixelWidht + " " + tilePixelHeight + " " + tilePixelWidht);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.position.set(352, 3872, 0); // position of the starting point
		// camera.translate(3400, 3500);
		camera.update();
		sb = new SpriteBatch();
		FileHandle cyklop = Gdx.files.internal("/home/robjan/eclipse-workspace/ProjectJava/core/assets/cyklop.png");
		texture = new Texture(cyklop);

		sprite = new Sprite(texture);
		sprite.setCenter(w / 2, h / 2);
		Gdx.input.setInputProcessor(this);
		FileHandle music = Gdx.files.internal("/home/robjan/eclipse-workspace/ProjectJava/core/assets/classic.wav");
		Music menuMusic = Gdx.audio.newMusic(music);
		menuMusic.setLooping(true);
		menuMusic.play();
	}

	@Override
	public void render() {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		render.setView(camera);
		renderer.setProjectionMatrix(camera.combined);
		render.render();

		sb.begin();
		sprite.draw(sb);

		sb.end();

		// renderer.begin(ShapeType.Filled);
		// renderer.circle(camera.viewportWidth/2,camera.viewportHeight/2, 50);
		// renderer.end();
	}

	@Override
	public void dispose() {
		render.dispose();
		map.dispose();
		sb.dispose();

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT) {
			camera.translate(-32, 0);
		}
		if (keycode == Input.Keys.RIGHT) {
			camera.translate(32, 0);
		}
		if (keycode == Input.Keys.UP) {
			camera.translate(0, 32);
		}
		if (keycode == Input.Keys.DOWN) {
			camera.translate(0, -32);
		}
		if (keycode == Input.Keys.NUM_1) {
			map.getLayers().get(0).setVisible(false);
		}
		if (keycode == Input.Keys.NUM_2) {
			map.getLayers().get(1).setVisible(false);
		}
		if (keycode == Input.Keys.NUM_3) {
			map.getLayers().get(0).setVisible(true);
		}
		if (keycode == Input.Keys.NUM_4) {
			map.getLayers().get(1).setVisible(true);
		}
		Gdx.app.log(TAG, camera.position.toString());
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
