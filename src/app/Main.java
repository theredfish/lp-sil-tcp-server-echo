package app;

import server.ServerFactory;
import server.HighLevelServer;
import server.LowLevelServer;

public class Main {
	public static void main(String[] args) {

		ServerFactory serverFactory = new ServerFactory();
		HighLevelServer s = serverFactory.getHighLevelServer();

		s.setPoolSize(2);
		s.setPort(9900);
		s.setResponseDelay(2000);
		s.launch();
	}
}
