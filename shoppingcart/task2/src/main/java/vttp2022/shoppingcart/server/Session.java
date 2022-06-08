package vttp2022.shoppingcart.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class Session {

	public static final String LIST = "list";
	public static final String CARTS = "carts";
	public static final String ADD = "add";
	public static final String DELETE = "delete";
	public static final String LOAD = "load";
	public static final String SAVE = "save";
	public static final String EXIT = "exit";

	private Repository repository;
	private Cart currCart;
	private Socket sock;
	private InputStream is;
	private ObjectInputStream ois;
	private OutputStream os;
	private ObjectOutputStream oos;

	public Session(Repository repo, Socket sock) {
		this.repository = repo;
		this.sock = sock;
	}

	public void start() throws IOException {

		boolean stop = false;

		initializeStreams(sock);

		currCart = new Cart("anon");

		while (!stop) {
			String input = read();

			System.out.printf(">> %s\n", input);

			String[] terms = input.split(" ");
			StringBuilder builder = new StringBuilder();
			String msg = "";

			switch (terms[0]) {

				case CARTS:
					builder.append("List of shopping carts\n");
					printList(repository.getShoppingCarts(), builder);
					msg = builder.toString();
					break;

				case LIST:
					msg = "Items in %s's shopping cart\n".formatted(currCart.getUsername());
					builder.append(msg);
					printList(currCart.getContents(), builder);
					msg = builder.toString();
					break;

				case ADD:
					int before = currCart.getContents().size();
					for (int i = 1; i < terms.length; i++)
						currCart.add(terms[i]);
					int addedCount = currCart.getContents().size() - before;
					msg = "Added %d item(s) to %s's cart\n".formatted(addedCount, currCart.getUsername());
					break;

				case DELETE:
					int idx = Integer.parseInt(terms[1]);
					String item = currCart.delete(idx - 1);
					msg = "Removed %s from %s's cart\n".formatted(item, currCart.getUsername());
					break;

				case LOAD:
					currCart = repository.load(terms[1]);
					msg =	"Loaded %s shopping cart. There are %s item(s)\n"
								.formatted(terms[1], currCart.getContents().size());
					break;

				case SAVE:
					repository.save(currCart);
					msg = "%s's cart has been saved\n".formatted(currCart.getUsername());
					break;

				case EXIT:
					stop = true;
					continue;

				default:
					msg = "Unknow input: %s\n".formatted(terms[0]);
			}

			write(msg);
		}

		close();
	}

	private String read() throws IOException {
		return ois.readUTF();
	}
	private void write(String out) throws IOException {
		oos.writeUTF(out);
		oos.flush();
	}

	private void initializeStreams(Socket sock) 
			throws IOException {
		// The order of creating the stream is important
		// we are opening input stream first then output stream
		// on the client we should open output stram first followed by input stream
		is = sock.getInputStream();
		ois = new ObjectInputStream(is);
		os = sock.getOutputStream();
		oos = new ObjectOutputStream(os);
	}

	private void close() throws IOException {
		is.close();
		os.close();
	}

	private void printList(List<String> list, StringBuilder builder) {
		if (list.size() <= 0) {
			builder.append("There are no contents\n");
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			String msg = "%d. %s\n".formatted((i + 1), list.get(i));
			builder.append(msg);
		}
	}
}
