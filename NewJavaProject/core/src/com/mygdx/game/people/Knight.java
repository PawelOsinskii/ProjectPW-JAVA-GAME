package com.mygdx.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class Knight extends Person {

	public static final String toFilePath = "/home/robjan/eclipse-workspace/NewJavaProject/core/assets/cyklop.png"; // nazwa pliku z rycerzem

	public Knight(OrthographicCamera camera) {
		super(toFilePath,camera);
	}


	
}
