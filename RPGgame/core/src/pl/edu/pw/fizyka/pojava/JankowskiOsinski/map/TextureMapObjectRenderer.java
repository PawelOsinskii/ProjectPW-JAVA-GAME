package pl.edu.pw.fizyka.pojava.JankowskiOsinski.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * @author robjan Class to render map and objects from Tiled
 */
public class TextureMapObjectRenderer extends OrthogonalTiledMapRenderer {

	// list of objects to get X,Y position
	List<TextureMapObject> textureMonsters = new ArrayList<>();

	public TextureMapObjectRenderer(TiledMap map) {
		super(map);
	}

	public TextureMapObjectRenderer(TiledMap map, Batch batch) {
		super(map, batch);
	}

	public TextureMapObjectRenderer(TiledMap map, float unitScale) {
		super(map, unitScale);
	}

	public TextureMapObjectRenderer(TiledMap map, float unitScale, Batch batch) {
		super(map, unitScale, batch);

	}

	@Override
	public void renderObject(MapObject object) {
		if (object instanceof TextureMapObject) {
			TextureMapObject textureObject = (TextureMapObject) object;
			batch.begin();
			batch.draw(textureObject.getTextureRegion(), textureObject.getX(), textureObject.getY());
			batch.end();
		}
	}

	public void renderMonster(MapObject object) {
		if (object instanceof TextureMapObject) {
			TextureMapObject textureMonster = (TextureMapObject) object;
			textureMonsters.add(textureMonster);
			batch.begin();
			batch.draw(textureMonster.getTextureRegion(), textureMonster.getX(), textureMonster.getY());
			batch.end();
			// move the monster
			textureMonster.setX(randomMove(-1, 1).x + textureMonster.getX());
			textureMonster.setY(randomMove(-1, 1).y + textureMonster.getY());
		}
	}

	public Vector2 randomMove(float min, float max) {
		Random random = new Random();
		float randX = min + random.nextFloat() * (max - min);
		float randY = min + random.nextFloat() * (max - min);
		return new Vector2(randX, randY);
	}

	public List<TextureMapObject> getTextureMonster() {
		return textureMonsters;
	}

	public void setTextureMonster(List<TextureMapObject> textureMonster) {
		this.textureMonsters = textureMonster;
	}
}
