package webPlayer.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import webPlayer.audio.MusicaFinaizouListener;
import webPlayer.audio.Player;
import webPlayer.model.Musica;

@Named
@Singleton
public class PlaylistBusiness implements Runnable, LineListener, MusicaFinaizouListener {

	private List<Musica> playlist = new Vector<Musica>();

	private int numMusicaAtual = 0;

	@Inject
	private Player player;

	@PostConstruct
	private void setup() {
		player.setOnMusicFinish(this);
	}

	public void addMusicaAoFim(Musica novaMusica) {
		playlist.add(novaMusica);
	}

	public void addMusicaNoInicio(Musica novaMusica) {
		playlist.add(0, novaMusica);
	}

	public void addMusicasAoFim(Collection<Musica> musicas) {
		playlist.addAll(musicas);
	}

	public void removeMusica(Musica musica) {
		playlist.remove(musica);
	}

	public List<Musica> listMusicas() {
		return new ArrayList<Musica>(playlist);
	}

	/**
	 * Executado quando a música corrente acaba
	 */
	@Override
	public void run() {
		tocarMusica(numMusicaAtual + 1);
	}

	public Musica tocarMusica(int numeroMusica) {
		if (numeroMusica >= playlist.size() || numeroMusica < 0) {
			throw new RuntimeException("Playlist não possui a música de número " + numeroMusica);
		}
		numMusicaAtual = numeroMusica;
		Musica result = playlist.get(numeroMusica);
		player.playMusic(result.getCaminhoCompleto());
		return result;
	}

	public Musica tocarAnterior() {
		return tocarMusica(numMusicaAtual - 1);
	}

	public Musica tocarProxima() {
		return tocarMusica(numMusicaAtual + 1);
	}

	@Override
	public void update(LineEvent event) {
		System.out.println(event + "");
	}

	@Override
	public void musicaFinalizou() {
		tocarProxima();
	}
}
