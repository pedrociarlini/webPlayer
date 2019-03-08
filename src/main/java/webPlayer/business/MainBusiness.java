package webPlayer.business;

import java.io.File;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.inject.Named;
import javax.inject.Singleton;

import webPlayer.helper.PastaFilenameFilter;
import webPlayer.model.Pasta;

@Named
@Singleton
public class MainBusiness {
	private String diretorio = "";

	private Pasta pastaRaiz = new Pasta();

	public void alterarDiretorio(String novoDiretorio) {
		File rootDir = new File(novoDiretorio);
		if (!rootDir.exists()) {
			throw new RuntimeException("Diretorio não existe");
		}

		// TODO salvar no diretorio do usuário
		this.diretorio = novoDiretorio;

		atualizarMusicas();
	}

	public void atualizarMusicas() {
		File rootDir = new File(getDiretorio());
		pastaRaiz = new Pasta(rootDir.getName(), rootDir.getAbsolutePath(), null);

		// Empilhando pastas

		Queue<Pasta> pastasAPercorrer = new PriorityQueue<Pasta>();
		pastasAPercorrer.add(pastaRaiz);

		while (!pastasAPercorrer.isEmpty()) {
			Pasta pastaCorrente = pastasAPercorrer.poll();
			final File[] dirs = new File(pastaCorrente.getCaminhoCompleto()).listFiles(new PastaFilenameFilter());
			if (dirs.length > 0) {
				for (File dir : dirs) {
					Pasta novaPAsta = new Pasta(dir.getName(), dir.getAbsolutePath(), pastaCorrente);
					pastaCorrente.getPastas().add(novaPAsta);
					pastasAPercorrer.add(novaPAsta);
				}
			}
		}

		// FilenameFilter filter = new MusicaFilenameFilter();
		// final File[] musicas = new
		// File(pastaCorrente.getCaminhoCompleto()).listFiles(filter);
		// for (File musica : musicas) {
		// pastaCorrente.getMusicas().add(new Musica(musica.getName(),
		// musica.getName(), musica.getAbsolutePath()));
		// }
	}

	public String getDiretorio() {
		return this.diretorio;
	}

}
