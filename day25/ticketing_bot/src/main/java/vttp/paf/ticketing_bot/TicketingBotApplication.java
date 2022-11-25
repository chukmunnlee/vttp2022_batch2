package vttp.paf.ticketing_bot;


import org.apache.catalina.connector.Response;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TicketingBotApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TicketingBotApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String name = args[2];
		String numTickets = args[3];

		// Construct the form
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("name", name);
		form.add("num_tickets", numTickets);

		// Construct the request
		RequestEntity<MultiValueMap<String, String>> req = RequestEntity
			.post("http://localhost:8080/purchase")
			.accept(MediaType.TEXT_HTML)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.body(form) ;

		// Create the RestTemplate
		RestTemplate template = new RestTemplate();

		// Make the request
		ResponseEntity<String> resp = template.exchange(req, String.class);

		String payload = resp.getBody();

		System.out.printf(">>> purchase ticket response:\n%s\n", payload);

		System.exit(0);
	}

}
