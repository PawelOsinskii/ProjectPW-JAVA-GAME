package pl.edu.pw.fizyka.pojava.JankowskiOsinski.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Enemy {

	private static final String path = "bot.png";
	private Texture img;
	private Sprite sprite;
	private Vector2 position;
	private SpriteBatch batch;

	public Enemy() {
		img = new Texture(Gdx.files.internal(path));
		sprite = new Sprite(img);
		batch = new SpriteBatch();
		position = new Vector2();
	}

	public void update(float delta) {
		batch.begin();
		batch.draw(img, position.x, position.y);
		batch.end();
	}

	// public void move(int count) {
	// for (int i = 0; i < count; i++) {
	// setPositionXY(position.x += 10f, position.y += 10f);
	// }
	// }

	public void setPositionXY(float x, float y) {
		position.x = x;
		position.y = y;
		sprite.setPosition(x, y);
	}

}
