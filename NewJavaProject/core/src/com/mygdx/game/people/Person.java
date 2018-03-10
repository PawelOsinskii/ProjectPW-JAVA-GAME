package com.mygdx.game.people;

import com.badlogic.gdx.math.Vector2;

public abstract class Person implements Stats {

	private int hp; // Health points
	private int mana; // Mana points
	private double speed; // Speed of running
	private int shielding; // Defense skill
	private int magicLevel; // Magic skill
	private int attackLevel; // Attack skill

	// Coordinates of Person
	private Vector2 position;

	public Person() {
		this.hp = Stats.HP_START;
		this.mana = Stats.MANA_START;
		this.speed = Stats.SPEED_START;
		this.shielding = Stats.SHIELDING_START; // Starting defense level
		this.magicLevel = Stats.MAGIC_LEVEL_START; // Minimum magic level
		this.attackLevel = Stats.ATTACK_LEVEL_START; // Minimum attack level

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
