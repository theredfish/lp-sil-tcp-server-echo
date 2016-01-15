/**
 * @author Alexis Chappron - Julian Didier
 */

package server;

import java.net.ServerSocket;
import java.util.Properties;

/**
 * AbstractServer class
 */
public abstract class AbstractServer {

	protected Properties config;
	protected int cpuNumber;
	protected int inactivityDelay;
	protected int poolSize;
	protected int port;
	protected String protocol;
	protected ServerSocket socket;


	/**
	 * AbstractServer constructor.
	 * Initialize the server (port, pool size and response delay).
	 *
	 * @param config which represent the specified configuration.
	 */
	public AbstractServer(Properties config) {
		this.config = config;

		this.cpuNumber = Integer.parseInt(config.getProperty("CPU_NUMBER"));
		this.inactivityDelay = Integer.parseInt(config.getProperty("INACTIVITY_DELAY"));
		this.poolSize = Integer.parseInt(config.getProperty("POOL_SIZE"));
		this.port = Integer.parseInt(config.getProperty("PORT"));
		this.protocol = config.getProperty("PROTOCOL");

		System.out.println("Configuration loaded... \n"
			+ cpuNumber + " core \n"
			+ "Max inactivity delay : "	+ inactivityDelay + "\n"
			+ "Max threads : " + poolSize +  "\n"
			+ "Port : " + port + "\n"
			+ "Protocol : " + protocol);
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
	 * Set inactivity delay.
	 *
	 * @param delay which represents the max inactivity delay (ms)
	 */
	public void setInactivityDelay(int delay){
		this.inactivityDelay = delay;
	}

	/**
	 * Launch the server.
	 */
	public abstract void launch();
}
