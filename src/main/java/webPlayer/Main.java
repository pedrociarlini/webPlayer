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
import webPlayer.servlet.StopMusicaServlet;
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

	@PostConstruct
	public void startup() {

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8082);
		tomcat.getConnector();
		Player.startJavaFX();

		Context ctx = tomcat.addWebapp("", Main.class.getClassLoader().getResource(".").getFile());
		System.out.println("context path: " + Main.class.getClassLoader().getResource(".").getFile());
		System.out.println("context path: " + ctx.getCatalinaBase().getAbsolutePath());

		// escolher diretorio
		Tomcat.addServlet(ctx, "diretorioServlet", diretorioServlet);
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

		try {
			tomcat.start();
		} catch (LifecycleException e) {
			throw new RuntimeException(e);
		}
		tomcat.getServer().await();

		// TODO colocar no systray do sistema (SE HOUVER)
	}
}
