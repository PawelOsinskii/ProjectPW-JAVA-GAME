package com.mygdx.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class Wizard extends Person {

	// Image of the player
	Texture image;
	FileHandle imageFile;
	public int spellCost = 10;

	public Wizard() {
		imageFile = Gdx.files.internal("/home/robjan/eclipse-workspace/NewJavaProject/core/assets/wizard.png");
		image = new Texture(imageFile);
	}

	public void spell() {
		if (getMana() > spellCost) {
			setMana(getMana() - spellCost);
			
		}
	}

}
