package webPlayer.business;

public class MainBusiness {
	private String diretorio = "";

	public void alterarDiretorio(String novoDiretorio) {
		// TODO salvar no diretorio do usuário
		this.diretorio = novoDiretorio;
	}
	
	public String getDiretorio() {
		return this.diretorio;
	}
}
