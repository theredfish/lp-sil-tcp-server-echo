package server;

import java.net.ServerSocket;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * AbstractServer class
 * See EchoProtocol RFC https://tools.ietf.org/html/rfc862
 */
public abstract class AbstractServer {

	protected Properties config;
	protected int cpuNumber;
	protected int inactivityDelay;
	protected int poolSize;
	protected int port;
	protected int responseDelay;
	protected ServerSocket socket;


	/**
	 * AbstractServer constructor.
	 * Initialize the server (port, pool size and response delay).
	 */
	public AbstractServer(Properties config) {
		this.config = config;

		this.cpuNumber = Integer.parseInt(config.getProperty("CPU_NUMBER"));
		this.inactivityDelay = Integer.parseInt(config.getProperty("INACTIVITY_DELAY"));
		this.poolSize = Integer.parseInt(config.getProperty("POOL_SIZE"));
		this.port = Integer.parseInt(config.getProperty("PORT"));
		this.responseDelay = Integer.parseInt(config.getProperty("RESPONSE_DELAY"));

		System.out.println("Configuration loaded... \n"
			+ cpuNumber + " core \n"
			+ "Max inactivity delay : "	+ inactivityDelay + "\n"
			+ "Max threads : " + poolSize +  "\n"
			+ "Port : " + port + "\n"
			+ "Max response delay : " + responseDelay);
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
