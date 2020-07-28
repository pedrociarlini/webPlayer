package webPlayer.audio;

import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.concurrent.FutureTask;

import javax.inject.Singleton;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

@Singleton
public class Player {

	private static Player instance = null;

	private final static Object blockObj = new Object();

	// private Media hit;
	//
	// private MediaPlayer mediaPlayer;

	private MusicaFinaizouListener musicaFinalizouListener;

	private Clip clip;

	private boolean pausar = false;

	private boolean parar = false;

	private Thread thread;

	public Player() {
	}

	/**
	 * Singleton pattern
	 * 
	 * @return
	 */
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
			this.parar = false;
			this.pausar = false;

			try {
				File file = new File(path);
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
				// AudioFileFormat aff = AudioSystem.getAudioFileFormat(file);

				AudioFormat baseFormat = audioInputStream.getFormat();
				AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
						baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
				final AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);
				thread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							// Play now.
							rawplay(decodedFormat, din);
							audioInputStream.close();
						} catch (IOException | LineUnavailableException e) {
							System.err.println("Erro ao tocar a música: " + e.getMessage());
							e.printStackTrace();
						}
					}
				}, "Tocando " + file.getName());
				thread.start();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException {
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		if (line != null) {
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			System.out.println("Música iniciada.");
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
			if (this.parar) {
				System.out.println("Música parada.");
			} else {
				System.out.println("Música finalizou.");
				if (this.musicaFinalizouListener != null)
					new Thread(new Runnable() {

						@Override
						public void run() {
							Player.this.musicaFinalizouListener.musicaFinalizou();
						}
					}, "Player avisando fim.").start();
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
		this.parar = true;
		if (thread != null) {
			while (thread.getState() != State.TERMINATED) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public void pause() {
		this.pausar = true;
	}

	public void play() {
		this.pausar = false;
	}

	public double getVolume() {
		if (clip != null) {
			FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float deltaTotal = gain.getMaximum() - gain.getMinimum();
			float valorPercentual = gain.getValue() / deltaTotal;
			return valorPercentual;
		} else {
			return 1;
		}
	}

	public void setVolume(double volume) {
		if (clip != null) {
			if (volume >= 0.00 && volume <= 1.00) {
				// mediaPlayer.setVolume(volume);
				FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				float deltaTotal = gain.getMaximum() - gain.getMinimum();
				float valorAbosoluto = (float) (volume * deltaTotal);

				float novoVolume = gain.getMinimum() + valorAbosoluto;
				gain.setValue(novoVolume);
			}

		}
	}

	public void setOnMusicFinish(MusicaFinaizouListener listener) {
		this.musicaFinalizouListener = listener;
	}
}
