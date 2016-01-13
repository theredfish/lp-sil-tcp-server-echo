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
	protected String protocol;
	protected int responseDelay;
	protected ServerSocket socket;


	/**
	 * AbstractServer constructor.
	 * Initialize the server (port, pool size and response delay).
	 */
	public AbstractServer() {
		this.config = new Properties();

		parseConfiguration();

		this.cpuNumber = Integer.parseInt(config.getProperty("CPU_NUMBER"));
		this.inactivityDelay = Integer.parseInt(config.getProperty("INACTIVITY_DELAY"));
		this.poolSize = Integer.parseInt(config.getProperty("POOL_SIZE"));
		this.port = Integer.parseInt(config.getProperty("PORT"));
		this.protocol = config.getProperty("PROTOCOL");
		this.responseDelay = Integer.parseInt(config.getProperty("RESPONSE_DELAY"));

		System.out.println(cpuNumber + " " + inactivityDelay + " " +
		poolSize +  " " + port + " " + protocol + " " + responseDelay);
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

	protected void parseConfiguration() {
		try {
			FileInputStream configFile = new FileInputStream("./../.env");
			config.load(configFile);
			configFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("Configuration file '.env' not found "+e);
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("I/O error "+e);
			System.exit(-1);
		}
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
