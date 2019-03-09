package webPlayer.model;

import java.io.Serializable;

public class Musica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5974108419343154601L;

	private String nome;

	private String nomeArquivo;

	private String caminhoCompleto;

	private Pasta pastaPai;

	public Musica() {
	}

	public Musica(String nome, String nomeArquivo, String caminhoCompleto) {
		this();
		this.nome = nome;
		this.nomeArquivo = nomeArquivo;
		this.caminhoCompleto = caminhoCompleto;
	}

	public Musica(String nome, String nomeArquivo, String caminhoCompleto, Pasta pastaPai) {
		this(nome, nomeArquivo, caminhoCompleto);
		this.pastaPai = pastaPai;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getCaminhoCompleto() {
		return caminhoCompleto;
	}

	public void setCaminhoCompleto(String caminhoCompleto) {
		this.caminhoCompleto = caminhoCompleto;
	}

	public Pasta getPastaPai() {
		return pastaPai;
	}

	public void setPastaPai(Pasta pastaPai) {
		this.pastaPai = pastaPai;
	}

	@Override
	public String toString() {
		return "Musica [nome=" + nome + ", caminhoCompleto=" + caminhoCompleto + "]";
	}
	
	

}
