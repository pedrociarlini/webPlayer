package webPlayer.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webPlayer.audio.Player;
import webPlayer.business.MainBusiness;
import webPlayer.business.PlaylistBusiness;
import webPlayer.model.Musica;

@Named
public class TocarMusicaServlet extends HttpServlet {

	@Inject
	private Player player;

	@Inject
	private MainBusiness mainBuss;

	@Inject
	private PlaylistBusiness playlistBuss;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().setAttribute("mainBuss", mainBuss);
		req.getServletContext().setAttribute("playlistBuss", playlistBuss);

		String comando = req.getParameter("comando");

		if (comando != null && !comando.isEmpty()) {
			if (comando.equals("tocar")) {
				int numeroMusica = Integer.parseInt(req.getParameter("numeroMusica"));
				try {
					if (numeroMusica >= 0) {
						Musica musicaTocando = playlistBuss.tocarMusica(numeroMusica);
						mainBuss.setMusicaTocando(musicaTocando);
					}
				} catch (NumberFormatException ex) {
					// TODO Enviar mensagem de erro
				}

			} else if (comando.equals("Pause")) {
				player.pause();
			} else if (comando.equals("Play")) {
				player.play();

			} else if (comando.equals("Música anterior")) {
				mainBuss.setMusicaTocando(playlistBuss.tocarAnterior());
			} else if (comando.equals("Próxima música")) {
				mainBuss.setMusicaTocando(playlistBuss.tocarProxima());
			} else if (comando.equals("Baixar volume")) {
				player.setVolume(player.getVolume() - 0.05D);
			} else if (comando.equals("Aumentar volume")) {
				player.setVolume(player.getVolume() + 0.05D);
			}
		}
		req.getRequestDispatcher("tocar.jsp").forward(req, resp);

	}
}