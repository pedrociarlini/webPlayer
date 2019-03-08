package webPlayer.business;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MainBusinessTest {

	private MainBusiness mainBuss = new MainBusiness();

	@Test
	public void testAlterarDiretorio() {
		String diretorio = "/Users/pedrociarlini/Downloads";
		try {
			mainBuss.alterarDiretorio(diretorio);
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Não era para dar exceção");
		}
		assertTrue("Deveria ter dados", mainBuss.getDiretorio() != null);
	}
}
