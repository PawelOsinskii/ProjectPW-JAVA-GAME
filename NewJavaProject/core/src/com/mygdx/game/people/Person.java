package com.mygdx.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.map.MapScreen;

public abstract class Person implements Stats {

	private int hp; // Health points
	private int mana; // Mana points
	private double speed; // Speed of running
	private int shielding; // Defense skill
	private int magicLevel; // Magic skill
	private int attackLevel; // Attack skill
	private int gold; // Amount of money

	// To generate image of person
	protected SpriteBatch spriteBatch;
	protected Texture texture;
	protected Sprite sprite;
	// Coordinates of Person
	protected Vector2 position;

	public Person(String pathToFile,Vector2 position) {
		this.hp = Stats.HP_START;
		this.mana = Stats.MANA_START;
		this.speed = Stats.SPEED_START;
		this.shielding = Stats.SHIELDING_START; // Starting defense level
		this.magicLevel = Stats.MAGIC_LEVEL_START; // Minimum magic level
		this.attackLevel = Stats.ATTACK_LEVEL_START; // Minimum attack level
		this.setGold(Stats.GOLD_START);
		//position = new Vector2(MapScreen.startPositionX, MapScreen.startPositionY);
		this.position = position;
		spriteBatch = new SpriteBatch();
		texture = new Texture(pathToFile);
		sprite = new Sprite(texture);
	}

	public void update(float delta) {
		spriteBatch.begin();
		sprite.draw(spriteBatch);
		spriteBatch.end();
	}

	public void dispose() {
		spriteBatch.dispose();
	}

	public void isCollideWithSecondLayer(MapScreen mapScreen) {

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

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

}
