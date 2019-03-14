package webPlayer.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webPlayer.business.MainBusiness;
import webPlayer.business.PlaylistBusiness;

@Named
@Singleton
public class DiretorioServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DIRETORIO_JSP = "diretorio.jsp";

	@Inject
	private MainBusiness mainBuss;

	@Inject
	private PlaylistBusiness playlistBuss;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().setAttribute("mainBuss", mainBuss);

		if (req.getParameterMap().containsKey("novoDiretorio")) {
			String diretorio = req.getParameter("diretorio");
			try {
				mainBuss.alterarDiretorio(diretorio);

				playlistBuss.addMusicasAoFim(mainBuss.listarTodasAsMusicas());

				// Deu certo
				resp.sendRedirect("listar");
			} catch (RuntimeException ex) {
				disparaErro(ex.getMessage(), req, resp);
			}
		} else {
			req.getRequestDispatcher(DIRETORIO_JSP).forward(req, resp);
		}
	}

	private void disparaErro(String erroMsg, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect(DIRETORIO_JSP + "?erro=" + erroMsg);
		// req.getRequestDispatcher(DIRETORIO_JSP + "?erro=" + sr).forward(req,
		// resp);
	}
}