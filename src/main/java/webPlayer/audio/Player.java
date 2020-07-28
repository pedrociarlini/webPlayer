package webPlayer.audio;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

@Singleton
public class Player {

	private static Player instance = null;

	private final static Object blockObj = new Object();

	// private Media hit;
	//
	// private MediaPlayer mediaPlayer;

	private LineListener onEndOfMediaListener;

	private static double volume = 1;

	private Clip clip;

	private boolean pausar;

	private boolean parar;

	public Player() {
	}

	/**
	 * Singleton pattern
	 * 
	 * @return
	 */
	public static Player createInstance() {
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
			// AudioFilePlayer;
			AudioInputStream audioInputStream;
			try {
				File file = new File(path);
				audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
				AudioFileFormat aff = AudioSystem.getAudioFileFormat(file);

				AudioInputStream din = null;
				AudioFormat baseFormat = audioInputStream.getFormat();
				AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
						baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
				din = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);
				// Play now.
				rawplay(decodedFormat, din);
				play();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public void testPlay(String filename) {
		try {
			File file = new File(filename);
			AudioInputStream in = AudioSystem.getAudioInputStream(file);
		} catch (Exception e) {
			// Handle exception.
		}
	}

	private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException {
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		if (line != null) {
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1 && !parar) {
				if (pausar) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						System.err.println("Erro ao pausar: " + e.getMessage());
					}
				} else {
					nBytesRead = din.read(data, 0, data.length);
					if (nBytesRead != -1)
						nBytesWritten = line.write(data, 0, nBytesRead);
				}
			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		}
	}

	private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

	public void stopMusic() {
		if (clip != null) {
			clip.stop();
		}
	}

	public void pause() {
		clip.stop();
	}

	public void play() {
		clip.start();
	}

	public double getVolume() {
		FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float deltaTotal = gain.getMaximum() - gain.getMinimum();
		float valorPercentual = gain.getValue() / deltaTotal;
		return valorPercentual;
	}

	@SuppressWarnings("static-access")
	public void setVolume(double volume) {
		if (volume >= 0.00 && volume <= 1.00) {
			// mediaPlayer.setVolume(volume);
			FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float deltaTotal = gain.getMaximum() - gain.getMinimum();
			float valorAbosoluto = (float) (volume * deltaTotal);

			float novoVolume = gain.getMinimum() + valorAbosoluto;
			gain.setValue(novoVolume);
			this.volume = volume;
		}
	}

	public void setOnMusicFinish(LineListener listener) {
		this.onEndOfMediaListener = listener;
		if (clip != null) {
			clip.addLineListener(this.onEndOfMediaListener);
			// mediaPlayer.setOnEndOfMedia(this.onEndOfMediaListener);
		}
	}
}
