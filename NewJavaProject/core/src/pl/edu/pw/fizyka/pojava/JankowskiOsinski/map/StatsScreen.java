package pl.edu.pw.fizyka.pojava.JankowskiOsinski.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.people.Person;

public class StatsScreen implements Screen {

	public static final String TAG = StatsScreen.class.getName();

	private static final float FONT_SCALE = 2;
	private static final float xOffset = 250;
	private static final float yOffset = 30;

	String statsString;
	MapScreen mapScreen;
	BitmapFont font;
	SpriteBatch textDisplay;

	public StatsScreen(MapScreen mapScreen) {
		this.mapScreen = mapScreen;
	}

	@Override
	public void show() {
		font = new BitmapFont();
		textDisplay = new SpriteBatch();
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.getData().setScale(FONT_SCALE);

		statsString = "Gold : " + mapScreen.getKnight().getGold();
		statsString += "\nHP : " + mapScreen.getKnight().getHp() + "\nMana : " + mapScreen.getKnight().getMana();
		statsString += "\nAttack level : " + mapScreen.getKnight().getAttackLevel() + "\nMagic level : "
				+ mapScreen.getKnight().getMagicLevel();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		textDisplay.begin();
		font.draw(textDisplay, statsString, Gdx.graphics.getWidth() / 4 - xOffset, Gdx.graphics.getHeight() - yOffset,
				5, Align.left, false);
		textDisplay.end();
	}

	@Override
	public void resize(int width, int height) {

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
		textDisplay.dispose();
	}

}
