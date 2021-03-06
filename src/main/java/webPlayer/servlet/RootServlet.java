package webPlayer.servlet;

import java.io.IOException;

import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
public class RootServlet extends HttpServlet {

	// "/Users/pedrociarlini/Downloads/Ana Vilela - Trem-Bala [Clipe
	// Oficial].mp3"

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Writer w = resp.getWriter();
		req.getRequestDispatcher("listar").forward(req, resp);
		// w.write("WebPlayer 0.1 BETA.\n");
		// w.flush();
		// w.close();
	}
}