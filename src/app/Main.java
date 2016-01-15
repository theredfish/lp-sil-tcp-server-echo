package app;

import server.factory.ServerFactory;
import server.AbstractServer;

public class Main {
	public static void main(String[] args) {
		AbstractServer server = ServerFactory.getServer();
		server.launch();
	}
}
