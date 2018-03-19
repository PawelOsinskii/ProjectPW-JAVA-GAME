package pl.edu.pw.fizyka.pojava.JankowskiOsinski.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.MapScreen;

public class Knight extends Person {
	// nazwa pliku z rycerzem
	public static final String toFilePath = "person2.png";

	// To animate person in different position
	Animation<TextureRegion> walkAnimation;
	Texture walkSheet;
	float stateTime;
	TextureRegion[][] textureRegion;
	SpriteBatch walkBatch;
	public OrthographicCamera camera;

	// Podzial na cztery kierunki chodzenia
	TextureRegion[] upWalk;
	TextureRegion[] downWalk;
	TextureRegion[] leftWalk;
	TextureRegion[] rightWalk;
	Animation<TextureRegion> walkAnimationUp;
	Animation<TextureRegion> walkAnimationDown;
	Animation<TextureRegion> walkAnimationRight;
	Animation<TextureRegion> walkAnimationLeft;
	TextureRegion currentFrame;

	public Knight(OrthographicCamera camera) {

		super(toFilePath, new Vector2(MapScreen.startPositionX, MapScreen.startPositionY));
		this.camera = camera;
		// animation
		walkSheet = new Texture(Gdx.files.internal(toFilePath));
		walkSheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		textureRegion = TextureRegion.split(walkSheet, walkSheet.getWidth() / 4, walkSheet.getHeight() / 4);

		upWalk = new TextureRegion[4];
		downWalk = new TextureRegion[4];
		leftWalk = new TextureRegion[4];
		rightWalk = new TextureRegion[4];

		for (int i = 0; i < 4; i++) {
			upWalk[i] = textureRegion[3][i];
			rightWalk[i] = textureRegion[2][i];
			leftWalk[i] = textureRegion[1][i];
			downWalk[i] = textureRegion[0][i];
		}

		walkAnimationUp = new Animation<TextureRegion>(0.1f, upWalk);
		walkAnimationDown = new Animation<TextureRegion>(0.1f, downWalk);
		walkAnimationLeft = new Animation<TextureRegion>(0.1f, leftWalk);
		walkAnimationRight = new Animation<TextureRegion>(0.1f, rightWalk);

		walkBatch = new SpriteBatch();

	}

	public void update(float delta, MapScreen mapScreen) {
		walkBatch.setProjectionMatrix(camera.combined);
		camera.update();
		walkBatch.begin();
		stateTime += delta;

		if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)
				&& !Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			walkBatch.draw(downWalk[0], position.x, position.y);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (!(camera.position.x < 270)) {
				camera.translate(-3, 0);
				position.x -= 3;
				currentFrame = walkAnimationLeft.getKeyFrame(stateTime, true);
				walkBatch.draw(currentFrame, position.x, position.y);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (!(camera.position.x > 1771)) {
				camera.translate(3, 0);
				position.x += 3;
				currentFrame = walkAnimationRight.getKeyFrame(stateTime, true);
				walkBatch.draw(currentFrame, position.x, position.y);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			if (!(camera.position.y < 220)) {
				camera.translate(0, -3);
				position.y -= 3;
				currentFrame = walkAnimationDown.getKeyFrame(stateTime, true);
				walkBatch.draw(currentFrame, position.x, position.y);
			}
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			if (!(camera.position.y > 1799)) {
				camera.translate(0, 3);
				position.y += 3;
				currentFrame = walkAnimationUp.getKeyFrame(stateTime, true);
				walkBatch.draw(currentFrame, position.x, position.y);
			}
		walkBatch.end();

	}

	@Override
	public void dispose() {
		walkBatch.dispose();
		walkSheet.dispose();
		super.dispose();
	}

}
