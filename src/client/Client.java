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

	private int delay;
	private Socket serverSocket;
	private AbstractServer server;
	private Token tokens;


	/**
	 * Client constructor.
	 * Initialize client with a serverSocket and default delay.
	 *
	 * @param serverSocket which represents the client serverSocket
	 */
	public Client(Socket serverSocket) {
		this.serverSocket = serverSocket;
		this.delay = 0;
	}

	public Client(Socket serverSocket, int delay, AbstractServer server) {
		this(serverSocket);
		this.delay = delay;
		this.server = server;
	}

	/**
	 * Client constructor.
	 * Initialize client with a serverSocket and a specified sleep time.
	 *
	 * @param serverSocket which represents the open tcp connection with a client.
	 * @param delay which represents client's sleep time.
	 **/
	public Client(Socket serverSocket, int delay, AbstractServer server, Token tokens) {
		this(serverSocket);
		this.delay = delay;
		this.server = server;
		this.tokens = tokens;
	}

	/**
	 * Client thread.
	 * When client is running we process to Read/Write (input/output standard).
	 */
	public void run() {
		if (server instanceof LowLevelServer) {
			runLowLevel();
		} else {
			runHighLevel();
		}
	}

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

	protected void runHighLevel() {
		try {
			echo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected boolean quit(String str) {
		return str.equalsIgnoreCase("quit");
	}

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
