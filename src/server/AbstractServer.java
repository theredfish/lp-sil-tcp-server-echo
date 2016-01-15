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
	protected int cpuNumber = 4;
	protected int inactivityDelay = 2000;
	protected int poolSize = 10;
	protected int port = 9900;
	protected String protocol = "TCP";
	protected Properties config;
	protected ServerSocket socket;


	/**
	 * AbstractServer constructor.
	 * Initialize the server (port, pool size and inactivity delay).
	 *
	 * @param config which represent the specified configuration.
	 */
	public AbstractServer(Properties config) {
		this.config = config;

		this.cpuNumber = config.getProperty("CPU_NUMBER") != null? Integer.parseInt(config.getProperty("CPU_NUMBER")):this.cpuNumber;
		this.inactivityDelay = config.getProperty("INACTIVITY_DELAY") != null?Integer.parseInt(config.getProperty("INACTIVITY_DELAY")):this.inactivityDelay;
		this.poolSize = config.getProperty("POOL_SIZE") != null? Integer.parseInt(config.getProperty("POOL_SIZE")):this.poolSize;
		this.port = config.getProperty("PORT") != null? Integer.parseInt(config.getProperty("PORT")):this.port;
		this.protocol = config.getProperty("PROTOCOL") != null? config.getProperty("PROTOCOL"):this.protocol;

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
