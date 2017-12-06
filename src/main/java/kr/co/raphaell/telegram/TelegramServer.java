package kr.co.raphaell.telegram;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class TelegramServer {

	
	public static void main(String[] args) throws Exception {
		TelegramBotService telegramBotService = null;
		
		telegramBotService = TelegramBotService.getInstance();
		telegramBotService.setBotToken(args[0]);
		TelegramGroup.init();
		
		Server server = new Server(Integer.parseInt(args[1]));

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		
		context.addServlet(new ServletHolder(new TelegramHandler("list")), "/list");
		context.addServlet(new ServletHolder(new TelegramHandler("send")), "/send");
		
		server.start();
		server.join();
	}
}
