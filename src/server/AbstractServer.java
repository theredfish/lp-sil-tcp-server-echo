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
	protected int cpuNumber = 4;
	protected int inactivityDelay = 2000;
	protected int poolSize = 10;
	protected int port = 9900;
	protected String protocol = "TCP";
	protected int responseDelay=0;
	protected ServerSocket socket;


	/**
	 * AbstractServer constructor.
	 * Initialize the server (port, pool size and response delay).
	 */
	public AbstractServer(Properties config) {
		this.config = config;

		this.cpuNumber = config.getProperty("CPU_NUMBER") != null? Integer.parseInt(config.getProperty("CPU_NUMBER")):this.cpuNumber;
		this.inactivityDelay = config.getProperty("INACTIVITY_DELAY") != null?Integer.parseInt(config.getProperty("INACTIVITY_DELAY")):this.inactivityDelay;
		this.poolSize = config.getProperty("POOL_SIZE") != null? Integer.parseInt(config.getProperty("POOL_SIZE")):this.poolSize;
		this.port = config.getProperty("PORT") != null? Integer.parseInt(config.getProperty("PORT")):this.port;
		this.protocol = config.getProperty("PROTOCOL") != null? config.getProperty("PROTOCOL"):this.protocol;
		this.responseDelay = config.getProperty("RESPONSE_DELAY") != null?Integer.parseInt(config.getProperty("RESPONSE_DELAY")):this.responseDelay;

		System.out.println("Configuration loaded... \n"
			+ cpuNumber + " core \n"
			+ "Max inactivity delay : "	+ inactivityDelay + "\n"
			+ "Max threads : " + poolSize +  "\n"
			+ "Port : " + port + "\n"
			+ "Protocol : " + protocol + "\n"
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
