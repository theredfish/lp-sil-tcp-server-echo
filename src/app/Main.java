/**
 * @author Alexis Chappron - Julian Didier
 */


package app;

import server.factory.ServerFactory;
import server.AbstractServer;

/**
 * Main class
 */
public class Main {
	public static void main(String[] args) {
		AbstractServer server = ServerFactory.getServer();
		server.launch();
	}
}
