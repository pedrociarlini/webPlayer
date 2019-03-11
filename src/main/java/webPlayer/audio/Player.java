package webPlayer.audio;

import java.io.File;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

@Named
@Singleton
public class Player extends Application {

	private static Player instance = null;

	private final static Object blockObj = new Object();

	private Media hit;

	private MediaPlayer mediaPlayer;

	public static void startJavaFX() {
		synchronized (blockObj) {
			new Thread() {
				{
					setName("Thread do JavaFX Application");
				}

				public void run() {
					Application.launch(Player.class, "");
				}
			}.start();
		}
	}

	/**
	 * Singleton pattern
	 * 
	 * @return
	 */
	@Produces
	public static Player getInstance() {
		synchronized (blockObj) {
			if (instance == null) {
				instance = new Player();
			}
		}

		return instance;
	}

	public void playMusic(String path) {
		synchronized (blockObj) {
			stopMusic();
			hit = new Media(new File(path).toURI().toString());
			mediaPlayer = new MediaPlayer(hit);
			play();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Estágio: " + primaryStage);
	}

	public void stopMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public void play() {
		mediaPlayer.play();
	}

	public double getVolume() {
		return mediaPlayer.getVolume();
	}

	public void setVolume(double volume) {
		if (volume >= 0 || volume <= 1) {
			mediaPlayer.setVolume(volume);
		}
	}
}
