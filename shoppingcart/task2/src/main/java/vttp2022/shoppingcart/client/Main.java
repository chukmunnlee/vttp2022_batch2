package vttp2022.shoppingcart.client;

import java.io.IOException;
import java.net.Socket;

public class Main {

	public static void main(String[] args) throws IOException {
		String terms[] = args[0].split("@");
		String user = terms[0];
		terms = terms[1].split(":");
		String host = terms[0];
		int port = Integer.parseInt(terms[1]);

		System.out.printf("Connecting to %s on port %d with user %s...", host, port, user);

		Socket sock = new Socket(host, port);

		System.out.printf(" connected\n");

		Session sess = new Session(user, sock);
		sess.start();
	}
}
