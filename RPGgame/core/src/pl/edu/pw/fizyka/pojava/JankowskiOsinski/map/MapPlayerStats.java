package pl.edu.pw.fizyka.pojava.JankowskiOsinski.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.people.Knight;

public class MapPlayerStats {

	public Stage stage;
	Label statsLabel;
	AssetManager assetManager;
	String statsHP;
	String statsGOLD;
	TextButton returnButton;

	public MapPlayerStats() {
		stage = new Stage();
		assetManager = new AssetManager();
		assetManager.load("uiskin.json", Skin.class);
		assetManager.finishLoading();
		statsLabel = new Label("", assetManager.get("uiskin.json", Skin.class));
		statsLabel.setPosition(20, Gdx.graphics.getHeight() - 50);
		returnButton = new TextButton("Exit", assetManager.get("uiskin.json", Skin.class));
		returnButton.setPosition(1100, Gdx.graphics.getHeight() - 50);
		returnButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Dialog dialog = new Dialog("Warning", assetManager.get("uiskin.json", Skin.class)) {
					@Override
					protected void result(Object object) {
						if (object.equals(true)) {
							// zapis statystyk do bazy danych !
							System.out.println("Exiting");
						}
					}
				};
				dialog.text("Do you want to exit ?").pad(50);
				dialog.button("Yes", true).pad(30);
				dialog.button("No", false).pad(30);
				dialog.key(Keys.ENTER, true);
				dialog.show(stage);
			}
		});
		stage.addActor(statsLabel);
		stage.addActor(returnButton);
		Gdx.input.setInputProcessor(stage);
	}

	public void show(Knight knight) {
		statsHP = "HP : " + knight.getHp();
		statsGOLD = "    GOLD : " + knight.getGold();
		statsLabel.setText(statsHP + statsGOLD);
	}

	public void render() {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

}
