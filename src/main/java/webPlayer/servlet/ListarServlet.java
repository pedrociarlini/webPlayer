package webPlayer.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webPlayer.business.MainBusiness;

@Named
public class ListarServlet extends HttpServlet {

	@Inject
	private MainBusiness mainBuss;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().setAttribute("mainBuss", mainBuss);
		req.getRequestDispatcher("listar.jsp").forward(req, resp);
	}
}