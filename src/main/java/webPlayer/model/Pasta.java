package webPlayer.model;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

public class Pasta implements Serializable, Comparable<Pasta> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1510686924374300512L;

	private String nome;

	private String caminhoCompleto;

	private Pasta pastaPai;

	private List<Musica> musicas = new Vector<Musica>();

	private List<Pasta> pastas = new Vector<Pasta>();

	private int profundidade = 0;

	// TOOD implementar validação de quantidade de músicas de subpastas, para
	// remover pasta
	// private int qtdeMusicasSubPastas = 0;

	public Pasta() {
	}

	public Pasta(String nome, String caminhoCompleto, Pasta pastaPai) {
		this();
		this.nome = nome;
		this.caminhoCompleto = caminhoCompleto;
		setPastaPai(pastaPai);

	}

	public boolean isRaiz() {
		return (getPastaPai() == null);
	}

	public Pasta getPastaPai() {
		return pastaPai;
	}

	public void setPastaPai(Pasta pastaPai) {
		this.pastaPai = pastaPai;
		if (pastaPai != null) {
			this.profundidade = pastaPai.getProfundidade() + 1;
		}
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public String getCaminhoCompleto() {
		return caminhoCompleto;
	}

	public void setCaminhoCompleto(String caminhoCompleto) {
		this.caminhoCompleto = caminhoCompleto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pasta> getPastas() {
		return pastas;
	}

	public void setPastas(List<Pasta> pastas) {
		this.pastas = pastas;
	}

	public int getProfundidade() {
		return profundidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caminhoCompleto == null) ? 0 : caminhoCompleto.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasta other = (Pasta) obj;
		if (caminhoCompleto == null) {
			if (other.caminhoCompleto != null)
				return false;
		} else if (!caminhoCompleto.equals(other.caminhoCompleto))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String identacao = StringUtils.repeat("\t", profundidade);
		return identacao + Pasta.class.getCanonicalName() + " [ musicas: " + musicas.size() + ";\n\t" + identacao
				+ "pastas: " + pastas.size() + pastas + "\n]";
	}

	@Override
	public int compareTo(Pasta o) {
		return this.hashCode() - o.hashCode();
	}
}