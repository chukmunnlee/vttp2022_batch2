package vttp2022.shoppingcart.server;

public class Main {
    public static void main( String[] args ) {
		 // Should check if parameter is present
		 String repo = args[0];
		 int port = Integer.parseInt(args[1]);
		 Server server = new Server(repo, port);
		 server.start();
    }
}
