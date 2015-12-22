package services;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Echo protocol
 */
public class EchoService implements Runnable {

	private int delay;
	Socket client;

	/**
	 * EchoService constructor.
	 *
	 * @param socket which represents client socket
	 */
	public EchoService(Socket socket) {
		this.client = socket;
		this.delay = 0;
	}

	/**
	 * EchoService constructor.
	 *@param _socket correspond to the open tcp connexion with a client.
	 *@param _delay is correspond to the time before the service respond.(Usefull for debugging)
	 **/
	public EchoService(Socket _socket, int _delay) {
		this(_socket);
		this.delay = _delay;
	}


	/**
	 *Method that is used to respond to the client.
	 *A delay between the incomming echo and the outcomming one can be setted.
	 **/
	@Override
	public void run() {
		try {
			OutputStream output = client.getOutputStream();
			byte[] line = new Scanner(client.getInputStream()).nextLine().getBytes();
			Thread.sleep(delay);
			output.write(line);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
