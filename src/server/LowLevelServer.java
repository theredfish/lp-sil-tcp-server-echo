package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import client.Client;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LowLevelServer extends AbstractServer {

    /**
     * Low level server constructor
     */
    public LowLevelServer() {
        super();
    }

	/**
	 * Launch low level server as a (echo) service
	 */
    public void launch() {
		try {
			socket = new ServerSocket(port);

			System.out.println("TCP low level server is running on localhost "
								+ port + "...");

			// Server in listen mode
			while (true) {
				Socket tcpClient = socket.accept();
				Client client = new Client(tcpClient, responseDelay);

				// Here we run new "client" thread
				new Thread(client).start();
			}
		} catch (IOException e) {
			System.out.println("I/O Error" + e);
		}
	}
}
