package client;

import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.util.Scanner;
import java.io.OutputStream;
import server.AbstractServer;
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

	public boolean quit(String str) {
		return str.equalsIgnoreCase("quit");
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

	runLowLevel() {
		tokens.take();

		try {
			echo();
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			tokens.release();
		}
	}

	runHighLevel() {
		echo();
	}

	public void echo() throws IOException, InterruptedException {
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
