package webPlayer;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import webPlayer.audio.Player;
import webPlayer.servlet.DiretorioServlet;
import webPlayer.servlet.ListarServlet;
import webPlayer.servlet.RootServlet;
import webPlayer.servlet.TocarMusicaServlet;

@Named
@Singleton
public class Main {

	public static void main(String[] args) throws LifecycleException {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		container.select(Main.class).get();

	}

	@Inject
	private DiretorioServlet diretorioServlet;

	@Inject
	private ListarServlet listarServlet;

	@Inject
	private TocarMusicaServlet tocarMusicaServlet;

	@Inject
	private RootServlet rootServlet;

	@PostConstruct
	public void startup() {

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8082);
		tomcat.getConnector();
		Player.startJavaFX();

		Context ctx = tomcat.addWebapp("", Main.class.getClassLoader().getResource(".").getFile());
		System.out.println("context path: " + Main.class.getClassLoader().getResource(".").getFile());
		System.out.println("context path: " + ctx.getCatalinaBase().getAbsolutePath());

		// ROOT
		Tomcat.addServlet(ctx, "rootServlet", rootServlet);
		ctx.addServletMappingDecoded("", "rootServlet");

		// escolher diretorio
		Tomcat.addServlet(ctx, "diretorioServlet", diretorioServlet);
		ctx.addServletMappingDecoded("/diretorio", "diretorioServlet");

		// Listar músicas
		Tomcat.addServlet(ctx, "listarServlet", listarServlet);
		ctx.addServletMappingDecoded("/listar", "listarServlet");

		// Tocar música
		Tomcat.addServlet(ctx, "tocarServlet", tocarMusicaServlet);
		ctx.addServletMappingDecoded("/tocar", "tocarServlet");

		try {
			tomcat.start();
		} catch (LifecycleException e) {
			throw new RuntimeException(e);
		}
		tomcat.getServer().await();

		// TODO colocar no systray do sistema (SE HOUVER)
	}
}
