package webPlayer.model;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

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

	public Pasta() {
	}

	public Pasta(String nome, String caminhoCompleto, Pasta pastaPai) {
		this();
		this.nome = nome;
		this.caminhoCompleto = caminhoCompleto;
		this.pastaPai = pastaPai;
	}

	public boolean isRaiz() {
		return (getPastaPai() == null);
	}

	public Pasta getPastaPai() {
		return pastaPai;
	}

	public void setPastaPai(Pasta pastaPai) {
		this.pastaPai = pastaPai;
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
		return Pasta.class.getName() + " [ musicas: " + musicas.size() + ";\npastas: " + pastas.size() + "\t" + pastas
				+ "\n]";
	}

	@Override
	public int compareTo(Pasta o) {
		return this.hashCode() - o.hashCode();
	}
}