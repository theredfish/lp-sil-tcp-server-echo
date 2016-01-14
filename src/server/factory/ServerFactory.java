package server.factory;

import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import server.AbstractServer;
import server.LowLevelServer;
import server.HighLevelServer;

public class ServerFactory {

	private static Properties config = new Properties();

	public static AbstractServer getServer() {
		loadConfiguration();

		return isLowLevelServer() ? getLowLevelServer(config) : getHighLevelServer(config);
	}

	protected static void loadConfiguration() {
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

	protected static boolean isLowLevelServer() {
		return config.getProperty("IMPLEMENTATION").equalsIgnoreCase("low");
	}

	protected static boolean isHighLevelServer() {
		return config.getProperty("IMPLEMENTATION").equalsIgnoreCase("high");
	}

	/**
	 * Get high level server instance.
	 *
	 * @return high level server
	 */
	protected static HighLevelServer getHighLevelServer(Properties config){
		return new HighLevelServer(config);
	}

	/**
	 * Get low level server instance.
	 *
	 * @return low level server
	 */
	protected static LowLevelServer getLowLevelServer(Properties config){
		return new LowLevelServer(config);
	}
}
