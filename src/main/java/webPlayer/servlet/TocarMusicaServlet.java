package webPlayer.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webPlayer.audio.Player;

public class TocarMusicaServlet extends HttpServlet {

	private Player player = Player.getInstance();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String musicPath = "/Users/pedrociarlini/Downloads/A cuca te pega.mp3";
		player.playMusic(musicPath);
		Writer w = resp.getWriter();
		w.write("Tocando..." + musicPath);
		w.flush();
		w.close();
	}
}