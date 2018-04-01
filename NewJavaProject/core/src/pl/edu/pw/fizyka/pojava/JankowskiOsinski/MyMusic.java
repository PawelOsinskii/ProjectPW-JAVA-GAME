package pl.edu.pw.fizyka.pojava.JankowskiOsinski;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

public class MyMusic {

	// "caravan.ogg.ogg" - pustynia
	// "music.mp3" - las

	FileHandle fileMusic;
	Music music;

	public MyMusic(String name) {
		fileMusic = Gdx.files.internal(name);
		music = Gdx.audio.newMusic(fileMusic);
	}

	public void startPlay() {
		music.play();
	}

	public void stopPlay() {
		music.stop();
	}

}
