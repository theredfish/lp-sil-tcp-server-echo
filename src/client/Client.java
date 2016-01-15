/**
 * @author Alexis Chappron - Julian Didier
 */

package client;

import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.util.Scanner;
import java.io.OutputStream;
import server.AbstractServer;
import server.LowLevelServer;
import server.HighLevelServer;
import server.Token;

/**
 * Client class
 */
public class Client implements Runnable {

	private int inactivityDelay;
	private Socket serverSocket;
	private AbstractServer server;
	private Token tokens;

	/**
	 * Client constructor.
	 * Initialize client with a server socket and default inactivity delay.
	 *
	 * @param serverSocket which represents the client serverSocket
	 * @param inactivityDelay which represents the specified max inactivity delay
	 */
	public Client(Socket serverSocket, int inactivityDelay) {
		this.serverSocket = serverSocket;
		this.inactivityDelay = inactivityDelay;
	}

	/**
	 * Client constructor.
	 * Initialize client with a server socket and default inactivity delay.
	 *
	 * @param serverSocket which represents the client serverSocket
	 * @param inactivityDelay which represents the specified max inactivity delay
	 * @param server which represents the current server level instance
	 */
	public Client(Socket serverSocket, int inactivityDelay, AbstractServer server) {
		this(serverSocket, inactivityDelay);
		this.server = server;
	}

	/**
	 * Client constructor.
	 * Initialize client with a server socket and default inactivity delay.
	 *
	 * @param serverSocket which represents the client serverSocket
	 * @param inactivityDelay which represents the specified max inactivity delay
	 * @param server which represents the current server level instance
	 * @param tokens which represents the max number of executed thread
	 */
	public Client(Socket serverSocket, int inactivityDelay, AbstractServer server, Token tokens) {
		this(serverSocket, inactivityDelay);
		this.server = server;
		this.tokens = tokens;
	}

	/**
	 * Run the thread
	 * Low level or high level. Depends of current server.
	 */
	public void run() {
		if (server instanceof LowLevelServer) {
			runLowLevel();
		} else {
			runHighLevel();
		}
	}

	/**
	 * Run low level server.
	 */
	protected void runLowLevel() {
		tokens.take();

		try {
			echo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			tokens.release();
		}
	}

	/**
	 * Run high level server
	 */
	protected void runHighLevel() {
		try {
			echo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param str string that represents what the user has entered.
	 * @return true or false
	 */
	protected boolean quit(String str) {
		return str.equalsIgnoreCase("quit");
	}

	/**
     * Echo protocol implementation.
     * See RFC https://tools.ietf.org/html/rfc862
	 */
	protected void echo() throws IOException, InterruptedException {
		InputStream input = serverSocket.getInputStream();
		OutputStream output = serverSocket.getOutputStream();
		Scanner scanner = new Scanner(input);
		byte[] line = scanner.nextLine().getBytes();

		while(!quit(new String(line))) {
			output.write(line);
			line = scanner.nextLine().getBytes();
		}

		output.write(line);
		serverSocket.close();
	}
}
