package com.mygdx.game.map;

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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.people.Knight;

public class MapScreen implements Screen, InputProcessor {

	public static final String TAG = MapScreen.class.getName();
	private static final String mapName = "eclipse-workspace/NewJavaProject/core/assets/map.tmx";
	private static final float ZOOM = 0.45f;
	private final float startPositionX = 430;
	private final float startPositionY = 1684;
	TiledMap tiledMap;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer tiledMapRenderer;

	private int[] layerBottom = { 0 };
	private int[] layerTop = { 2 };

	Knight knight;

	Animation<TextureRegion> goblinAnimation;
	Texture walkSheet;
	SpriteBatch spriteBatch;
	float stateTime;

	@Override
	public void show() {

		walkSheet = new Texture("/home/robjan/eclipse-workspace/NewJavaProject/core/assets/goblin.png");
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 11, walkSheet.getHeight() / 5);
		TextureRegion[] walkFrames = new TextureRegion[11 * 5];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 11; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		goblinAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
		spriteBatch = new SpriteBatch();
		stateTime = 0f;

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		tiledMap = new TmxMapLoader(new ExternalFileHandleResolver()).load(mapName);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);
		knight = new Knight(camera);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		camera.zoom = ZOOM;
		camera.position.set(startPositionX, startPositionY, 0);
		camera.update();

		// knight.isCollideWithSecondLayer(tiledMapRenderer);
	}

	@Override
	public void render(float delta) {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Ciekawa opcja na zakonczenie gry
		// camera.zoom += 0.1;
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(layerBottom);
		knight.update(delta);
		
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = goblinAnimation.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 50, 50);
		spriteBatch.end();

		knight.isCollideWithSecondLayer(tiledMapRenderer);
		tiledMapRenderer.render(layerTop);
		// System.out.println(knight.isCollideWithSecondLayer(tiledMapRenderer));
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

	@Override
	public void dispose() {
		tiledMap.dispose();
		tiledMapRenderer.dispose();
		knight.dispose();

		spriteBatch.dispose();
		walkSheet.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.NUM_1)
			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		if (keycode == Input.Keys.NUM_2)
			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		Gdx.app.log(TAG, camera.position.toString());
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
