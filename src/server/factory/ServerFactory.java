/**
 * @author Alexis Chappron - Julian Didier
 */

package server.factory;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import server.AbstractServer;
import server.LowLevelServer;
import server.HighLevelServer;

public class ServerFactory {

	private static Properties config = new Properties();

	/**
	 * Determine the server to use. Depends of configuration.
	 * @return low level server or high level server.
	 */
	public static AbstractServer getServer() {
		loadConfiguration();

		return isLowLevelServer() ? getLowLevelServer(config) : getHighLevelServer(config);
	}

	/**
	 * Load current configuration defined inside ".env" file
	 */
	protected static void loadConfiguration() {
		try {
			FileInputStream configFile = new FileInputStream(".env");
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
	 * Determine if current server is "low level"
	 * @return true or false
	 */
	protected static boolean isLowLevelServer() {
		return config.getProperty("IMPLEMENTATION").equalsIgnoreCase("low");
	}

	/**
	 * Determine if current server is "high level"
	 * @return true or false
	 */
	protected static boolean isHighLevelServer() {
		return config.getProperty("IMPLEMENTATION").equalsIgnoreCase("high");
	}

	/**
	 * Get high level server instance.
	 *
	 * @param config which represent the specified configuration.
	 * @return HighLevelServer
	 */
	protected static HighLevelServer getHighLevelServer(Properties config){
		return new HighLevelServer(config);
	}

	/**
	 * Get high level server instance.
	 *
	 * @param config which represent the specified configuration.
	 * @return LowLevelServer(config);

	 */
	protected static LowLevelServer getLowLevelServer(Properties config){
		return new LowLevelServer(config);
	}
}
