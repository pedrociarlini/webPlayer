package webPlayer.servlet;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webPlayer.audio.Player;
import webPlayer.business.MainBusiness;

@Named
public class TocarMusicaServlet extends HttpServlet {

	@Inject
	private Player player;

	@Inject
	private MainBusiness mainBuss;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().setAttribute("mainBuss", mainBuss);

		String comando = req.getParameter("comando");

		if (comando != null && !comando.isEmpty()) {
			if (comando.equals("tocar")) {
				String musicPath = req.getParameter("caminhoCompleto");
				if (musicPath != null & new File(musicPath).exists()) {
					player.playMusic(musicPath);
					mainBuss.setMusicaTocando(musicPath);
				}
			} else if (comando.equals("Pause")) {
				player.pause();
			} else if (comando.equals("Play")) {
				player.play();
			} else if (comando.equals("Baixar volume")) {
				player.setVolume(player.getVolume() - 0.05D);
			} else if (comando.equals("Aumentar volume")) {
				player.setVolume(player.getVolume() + 0.05D);
			}
		}
		req.getRequestDispatcher("tocar.jsp").forward(req, resp);

	}
}