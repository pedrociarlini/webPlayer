package webPlayer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiretorioServlet extends HttpServlet {

	// "/Users/pedrociarlini/Downloads/Ana Vilela - Trem-Bala [Clipe
	// Oficial].mp3"

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameterMap().containsKey("novoDiretorio")) {
			String diretorio = req.getParameter("diretorio");
			
		} else {
			req.getRequestDispatcher("diretorio.jsp").forward(req, resp);
		}
	}
}