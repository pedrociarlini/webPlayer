package webPlayer.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.inject.Named;
import javax.inject.Singleton;

import org.jboss.logging.Logger;

import webPlayer.helper.MusicaFilenameFilter;
import webPlayer.helper.PastaFileFilter;
import webPlayer.model.Musica;
import webPlayer.model.Pasta;

@Named
@Singleton
public class MainBusiness {

	private String diretorio = "";

	private Pasta pastaRaiz = new Pasta();

	private static final Logger log = Logger.getLogger(MainBusiness.class);

	public void alterarDiretorio(String novoDiretorio) {
		File rootDir = new File(novoDiretorio);
		if (!rootDir.exists()) {
			throw new RuntimeException("Diretorio não existe");
		}

		// TODO salvar no diretorio do usuário
		this.diretorio = novoDiretorio;
		log.info("Novo diretório configurado: " + novoDiretorio);

		atualizarMusicas();
	}

	public void atualizarMusicas() {
		long qtdeMusicas = 0;

		File rootDir = new File(getDiretorio());
		pastaRaiz = new Pasta(rootDir.getName(), rootDir.getAbsolutePath(), null);

		// Empilhando pastas

		Queue<Pasta> pastasAPercorrer = new PriorityQueue<Pasta>();
		pastasAPercorrer.add(pastaRaiz);

		while (!pastasAPercorrer.isEmpty()) {
			Pasta pastaCorrente = pastasAPercorrer.poll();
			final File[] musicas = new File(pastaCorrente.getCaminhoCompleto()).listFiles(new MusicaFilenameFilter());

			for (File musica : musicas) {
				pastaCorrente.getMusicas()
						.add(new Musica(musica.getName(), musica.getName(), musica.getAbsolutePath()));

				qtdeMusicas++;
			}

			final File[] dirs = new File(pastaCorrente.getCaminhoCompleto()).listFiles(new PastaFileFilter());

			if (dirs.length > 0) {
				for (File dir : dirs) {
					Pasta novaPAsta = new Pasta(dir.getName(), dir.getAbsolutePath(), pastaCorrente);
					pastaCorrente.getPastas().add(novaPAsta);
					pastasAPercorrer.add(novaPAsta);
				}
			}

			log.info("Músicas atualizdas: " + qtdeMusicas + " músicas");
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

	public List<Musica> listarTodasAsMusicas() {
		List<Musica> result = new ArrayList<Musica>();
		result.add(new Musica("Teste", "Teste", "teste"));
		return result;
	}

}
