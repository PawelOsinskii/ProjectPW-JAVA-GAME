package com.mygdx.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Person implements Stats {

	private int hp; // Health points
	private int mana; // Mana points
	private double speed; // Speed of running
	private int shielding; // Defense skill
	private int magicLevel; // Magic skill
	private int attackLevel; // Attack skill

	// To generate image of person
	private SpriteBatch spriteBatch;
	private Texture texture;
	private OrthographicCamera camera;
	private Sprite sprite;
	// Coordinates of Person
	private Vector2 position;
	
	// To animate person
	Texture walkSheet;
	float stateTime;
	
	// dokoncz animacje z goblina 
	

	public Person(String pathToFile, OrthographicCamera camera) {
		this.hp = Stats.HP_START;
		this.mana = Stats.MANA_START;
		this.speed = Stats.SPEED_START;
		this.shielding = Stats.SHIELDING_START; // Starting defense level
		this.magicLevel = Stats.MAGIC_LEVEL_START; // Minimum magic level
		this.attackLevel = Stats.ATTACK_LEVEL_START; // Minimum attack level
		this.camera = camera;
		position = new Vector2(camera.position.x, camera.position.y);
		spriteBatch = new SpriteBatch();
		texture = new Texture(pathToFile);
		sprite = new Sprite(texture);
		sprite.setCenter(camera.viewportWidth / 2, camera.viewportHeight / 2);
	}

	public void update(float delta) {
		spriteBatch.begin();
		sprite.draw(spriteBatch);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (!(camera.position.x < 271)) {
				camera.translate(-3, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (!(camera.position.x > 1771)) {
				camera.translate(3, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			if (!(camera.position.y < 220)) {
				camera.translate(0, -3);
			}
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			if (!(camera.position.y > 1810)) {
				camera.translate(0, 3);
			}
		spriteBatch.end();
	}

	public void dispose() {
		spriteBatch.dispose();
		texture.dispose();
	}

	public boolean isCollideWithSecondLayer(OrthogonalTiledMapRenderer tiledMapRenderer) {
		// if (tiledMapRenderer.getMap().getLayers().get(1).getOffsetY()) {
		// return true;

		return false;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getShielding() {
		return shielding;
	}

	public void setShielding(int shielding) {
		this.shielding = shielding;
	}

	public int getMagicLevel() {
		return magicLevel;
	}

	public void setMagicLevel(int magicLevel) {
		this.magicLevel = magicLevel;
	}

	public int getAttackLevel() {
		return attackLevel;
	}

	public void setAttackLevel(int attackLevel) {
		this.attackLevel = attackLevel;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

}
