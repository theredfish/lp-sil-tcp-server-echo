package server;

import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * AbstractServer class
 * See EchoProtocol RFC https://tools.ietf.org/html/rfc862
 */
public abstract class AbstractServer {

	protected int port;
	protected int poolSize;
	protected int responseDelay;
	protected ServerSocket socket;


	/**
	 * AbstractServer constructor.
	 * Initialize the server (port, pool size and response delay).
	 */
	public AbstractServer() {
		this.port = 9900;
		this.poolSize = 10;
		this.responseDelay = 0;
	}

	/**
	 * Set pool size.
	 *
	 * This method is not specific to higher level server.
	 * With low level server the pool size corresponds to the number of threads.
	 *
	 * @param poolSize which represents the pool size or the number of threads
	 */
	public void setPoolSize(int poolSize){
		this.poolSize = poolSize;
	}

	/**
	 * Set server port.
	 *
	 * @param port which represents server port
	 */
	public void setPort(int port){
		this.port = port;
	}

	/**
	 * Set response delay.
	 *
	 * @param delay which represents the server response delay
	 */
	public void setResponseDelay(int delay){
		this.responseDelay = delay;
	}

	/**
	 * Launch the server.
	 */
	public abstract void launch();

	protected void parseConfiguration() throws IOException, FileNotFoundException, ServerException {
		FileReader file = new FileReader("./../.env");
		BufferedReader buffer = new BufferedReader(file);
		String line = buffer.readLine();

		while (line != null){
			if (!(line.equals("")) && !(line.contains("#"))) {
				String[] params = line.split("=");
				System.out.println(params.length);
				if (!(params.length < 2) && !(params.length > 2)) {
					System.out.println(params[0] + " - " + params[1]);
				} else {
					throw new ServerException("An error occurred while parsing the configuration file.");
				}
			}

			line = buffer.readLine();
		}

		buffer.close();
	}

	/**
	 * Shut down the server in a proper way.
	 */
	public void shutdown() {
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				try {
					socket.close();
					System.out.println("The server is shut down!");
				} catch (IOException e) {
					System.out.println("Error - Cannot close socket" + e);
				}
			}
		});
	}
}
