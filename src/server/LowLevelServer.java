/**
 * @author Alexis Chappron - Julian Didier
 */

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import client.Client;
import java.util.Properties;

public class LowLevelServer extends AbstractServer {

    /**
     * Low level server constructor
     *
     * Default strategy is "fixed".
     * TODO use config to determine strategies.
     *
     * @param config allows to choose the strategy.
     */
    public LowLevelServer(Properties config) {
		super(config);
    }

	/**
	 * Launch low level server as a (echo) service
	 */
    public void launch() {
		Token tokens = new Token(poolSize);

		try {
			socket = new ServerSocket(port);

			System.out.println("TCP low level server is running on localhost "
								+ port + "...");

			// Server in listen mode
			while (true) {
				Socket tcpClient = socket.accept();
				Client client = new Client(tcpClient, inactivityDelay, this, tokens);

				// Here we run new "client" thread
				Thread t = new Thread(client);
				t.start();
			}
		} catch (IOException e) {
			System.out.println("I/O Error" + e);
		}
	}
}
