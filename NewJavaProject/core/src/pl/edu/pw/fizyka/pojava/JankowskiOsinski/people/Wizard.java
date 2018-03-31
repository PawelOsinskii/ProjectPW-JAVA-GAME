package pl.edu.pw.fizyka.pojava.JankowskiOsinski.people;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.Constants;

public class Wizard extends Person {

	private static final String toFilePath = ""; // nazwa pliku do maga
	public int spellCost = 10;

	public Wizard(OrthographicCamera camera) {
		super(toFilePath,new Vector2(Constants.startPositionX, Constants.startPositionY));
	}

	
	public void spell() {
		if (getMana() > spellCost) {
			setMana(getMana() - spellCost);
			
		}
	}

}
