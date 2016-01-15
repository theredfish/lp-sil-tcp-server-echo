/**
 * @author Alexis Chappron - Julian Didier
 */

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import client.Client;
import java.util.Properties;

public class HighLevelServer extends AbstractServer {

	/**
	 * Executor service.
	 *
	 * Usefull to have thread pool strategies.
	 */
	private ExecutorService executorService;

	/**
	 * High level server constructor which initializes the executor service.
	 *
	 * FixedThreadPool is usefull to avoid overhead by specifying pool size.
	 * CachedThreadPool is usefull to focus reuse.
	 *
	 * @param config allows to choose the strategy.
	 */
	public HighLevelServer(Properties config) {
		super(config);

		// If the poolsize is much higher than the number of core.
		if(this.poolSize > 2*(this.cpuNumber)){
			executorService = Executors.newFixedThreadPool(poolSize);
			System.out.println("FixedPThreadPool choosen ...");
		}else{
			executorService = Executors.newCachedThreadPool();
			System.out.println("CachedThreadPool choosen ...");
		}

	}

	/**
	 * Launch high level server as a (echo) service
	 */
	public void launch() {
		try {
			socket = new ServerSocket(port);
			System.out.println("TCP high level server is running on " + port + "...");

			while (true) {
				Socket tcpClient = socket.accept();
				Client client = new Client(tcpClient, inactivityDelay, this);

				// Here we run the client
				executorService.submit(client);
				System.out.println("Server is listening new client...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
