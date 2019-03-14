package webPlayer;

import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

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

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	@PostConstruct
	public void startup() {

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8082);
		tomcat.getConnector();
		Player.startJavaFX();

		URL appDir = Main.class.getClassLoader().getResource("footer.jsp");
		File parentPath = new File(appDir.getPath().replaceAll("file:", "")).getParentFile();
		logger.info("\n********\nPasta original = " + parentPath + "\n********\n");

		// setting up the JSP folder in case of JAR (production environment)
		if (appDir.getProtocol().equals("jar")) {
			String auxPath = parentPath.getAbsolutePath();
			auxPath = auxPath.substring(0, auxPath.indexOf("!"));
			parentPath = new File(new File(auxPath).getParent() + File.separator + "libs" + File.separator + "app");
		}
		if (!parentPath.exists()) {
			logger.severe("A pasta [" + parentPath.getAbsolutePath() + "] não existe.");
			System.exit(1);
		}

		String webappPath = parentPath.getAbsolutePath();
		logger.info("********\nPasta do app = " + webappPath + "\n********");
		Context ctx = tomcat.addWebapp("", webappPath);
		logger.info("Catalina path: " + ctx.getCatalinaBase().getAbsolutePath());

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
