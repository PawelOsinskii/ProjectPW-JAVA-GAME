package com.mygdx.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class Knight extends Person {

	Texture image;
	FileHandle imageFile;

	public Knight() {
		imageFile = Gdx.files.internal("/home/robjan/eclipse-workspace/NewJavaProject/core/assets/knight.png");
		image = new Texture(imageFile);
	}

	
}
