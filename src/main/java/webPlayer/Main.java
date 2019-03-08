package webPlayer;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import webPlayer.audio.Player;
import webPlayer.servlet.DiretorioServlet;
import webPlayer.servlet.ListarServlet;
import webPlayer.servlet.RootServlet;
import webPlayer.servlet.StopMusicaServlet;
import webPlayer.servlet.TocarMusicaServlet;

public class Main {
	public static void main(String[] args) throws LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(80);
		tomcat.getConnector();
		Player.startJavaFX();

		Context ctx = tomcat.addWebapp("", Main.class.getClassLoader().getResource(".").getFile());
		System.out.println("context path: " + Main.class.getClassLoader().getResource(".").getFile());
		System.out.println("context path: " + ctx.getCatalinaBase().getAbsolutePath());

		// escolher diretorio
		Tomcat.addServlet(ctx, "diretorioServlet", new DiretorioServlet());
		ctx.addServletMappingDecoded("/diretorio", "diretorioServlet");

		// Listar músicas
		Tomcat.addServlet(ctx, "listarServlet", new ListarServlet());
		ctx.addServletMappingDecoded("/listar", "listarServlet");

		// Listar músicas
		Tomcat.addServlet(ctx, "rootServlet", new RootServlet());
		ctx.addServletMappingDecoded("/", "rootServlet");

		// Tocar música
		Tomcat.addServlet(ctx, "tocarServlet", new TocarMusicaServlet());
		ctx.addServletMappingDecoded("/tocar", "tocarServlet");
		
		// Parar música
		Tomcat.addServlet(ctx, "pararServlet", new StopMusicaServlet());
		ctx.addServletMappingDecoded("/parar", "pararServlet");

		tomcat.start();
		tomcat.getServer().await();
	}
}
