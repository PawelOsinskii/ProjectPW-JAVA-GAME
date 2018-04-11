package pl.edu.pw.fizyka.pojava.JankowskiOsinski.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.RPGgame;

public class LogMenu implements Screen {

	private Stage stage;
	private Table table;
	private TextButton registerButton;
	private TextButton loginButton;
	private TextButton returnButton;
	private TextField loginField;
	private TextArea passwordArea;
	private Label loginLabel;
	private Label passwordLabel;
	private AssetManager assetManager;

	private RPGgame game;

	public LogMenu(RPGgame game) {
		this.game = game;
	}

	@Override
	public void show() {
		assetManager = new AssetManager();
		assetManager.load("uiskin.json", Skin.class);
		assetManager.finishLoading();

		registerButton = new TextButton("Register", assetManager.get("uiskin.json", Skin.class));
		loginButton = new TextButton("Login", assetManager.get("uiskin.json", Skin.class));
		returnButton = new TextButton("Back to menu", assetManager.get("uiskin.json", Skin.class));
		loginLabel = new Label("Login: ", assetManager.get("uiskin.json", Skin.class));
		passwordLabel = new Label("Password: ", assetManager.get("uiskin.json", Skin.class));
		loginField = new TextField("", assetManager.get("uiskin.json", Skin.class));
		passwordArea = new TextArea("", assetManager.get("uiskin.json", Skin.class));
		loginButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// problem z boolean
				if (LogIn.isLogin(loginField.getText(), passwordArea.getText())) {
					// tu trzeba pobrac statystyki bohatera z bazy danych
					game.loadGame();
				} else {
					// komunikat
					loginButton.setColor(Color.RED);
					// to nie działa
					passwordArea.setText("");
				}

			}
		});

		stage = new Stage(new ScreenViewport());
		table = new Table();

		table.setWidth(stage.getWidth());
		table.align(Align.center | Align.top);
		table.setPosition(0, Gdx.graphics.getHeight());

		table.padTop(50);
		table.add(loginLabel).padBottom(50);
		table.add(loginField).padBottom(50);
		table.row();
		table.add(passwordLabel).padBottom(50);
		table.add(passwordArea).padBottom(50);
		table.row();
		table.add(loginButton);
		table.add(registerButton);

		returnButton.setPosition(10, 20, Align.left);
		stage.addActor(table);
		stage.addActor(returnButton);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}