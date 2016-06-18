package jettyService;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class OneWebAppWithFolder {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		WebAppContext context = new WebAppContext();
		context.setContextPath("/app");
		context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		context.setResourceBase("src/main/webapp");
		context.setParentLoaderPriority(true);
		context.setWelcomeFiles(new String[] { "index.html" });
		server.setHandler(context);

		server.start();
		server.join();
	}
}
