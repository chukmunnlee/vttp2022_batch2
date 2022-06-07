package vttp2022.shoppingcart.core;

public class Main {
    public static void main( String[] args ) {
		 // Should check if parameter is present
		 Repository repository = new Repository(args[0]);
		 Session session = new Session(repository);
		 session.start();
    }
}
