package pl.edu.pw.fizyka.pojava.JankowskiOsinski.people;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Vector2;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.TextureMapObjectRenderer;

public class Bot {

	TextureMapObjectRenderer tiledMapRenderer;
	MapLayer monsterLayer;
	MapObjects monsterObjects;
	MapObject monster;

	public Bot(TextureMapObjectRenderer tiledMapRenderer, String name) {
		this.tiledMapRenderer = tiledMapRenderer;
		try {
			monsterLayer = tiledMapRenderer.getMap().getLayers().get("monster");
			monsterObjects = monsterLayer.getObjects();
			monster = monsterObjects.get(name);
		} catch (Exception ex) {
		}
	}

	public void update(float delta) {
		tiledMapRenderer.renderObject(monster);

	}

	// getting position of the monster
	public Vector2 position() {
		float x = (float) monster.getProperties().get("xPos");
		float y = (float) monster.getProperties().get("yPos");
		System.out.println(x + " , " + y);
		return new Vector2();
	}

}
