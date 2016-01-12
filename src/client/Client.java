package client;

import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.util.Scanner;
import java.io.OutputStream;

/**
 * Client class
 */
public class Client implements Runnable {

	private int delay;
	Socket serverSocket;

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
	public Client(Socket serverSocket, int delay) {
		this(serverSocket);
		this.delay = delay;
	}

	public boolean quit(String str) {
		return str.equalsIgnoreCase("quit");
	}

	/**
	 * Client is runnable.
	 * When client is running we process to Read/Write (input/output standard).
	 * TODO : move responsability to the server which should offer echo service.
	 * The client should only write on standard input.
	 */
	public void run() {
		try {
			InputStream input = serverSocket.getInputStream();
			OutputStream output = serverSocket.getOutputStream();
			Scanner scanner = new Scanner(input);
			byte[] line = scanner.nextLine().getBytes();

			while(!quit(new String(line))) {
				output.write(line);
				line = scanner.nextLine().getBytes();
			}

			Thread.sleep(2);

			output.write(line);
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
