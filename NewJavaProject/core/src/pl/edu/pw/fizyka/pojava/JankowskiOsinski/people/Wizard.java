package pl.edu.pw.fizyka.pojava.JankowskiOsinski.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.MapScreen;

public class Wizard extends Person {

	private static final String toFilePath = ""; // nazwa pliku do maga
	public int spellCost = 10;

	public Wizard(OrthographicCamera camera) {
		super(toFilePath,new Vector2(MapScreen.startPositionX, MapScreen.startPositionY));
	}

	
	public void spell() {
		if (getMana() > spellCost) {
			setMana(getMana() - spellCost);
			
		}
	}

}
