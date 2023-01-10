package vttp2022.paf.assessment.eshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.services.OrderException;
import vttp2022.paf.assessment.eshop.services.OrderService;

import static vttp2022.paf.assessment.eshop.Utils.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private OrderService orderSvc;

	//TODO: Task 3
	public ResponseEntity<String> postOrder(@RequestBody String payload) {
		JsonObject json = toJson(payload);
		String name = json.getString("name");

		// Task 3a
		Optional<Customer> opt = custRepo.findCustomerByName(name);
		if (opt.isEmpty()) {
			JsonObject error = createError("Customer %s not found".formatted(name));
			return ResponseEntity.status(404)
					.body(error.toString());
		}

		// Task 3b
		Order order = toOrder(json);
		Customer customer = opt.get();

		try {
			orderSvc.createOrder(order, customer);
		} catch (OrderException ex) {
			JsonObject error = createError(ex.getMessage());
			return ResponseEntity.status(500)
					.body(error.toString());
		}

		return null;
	}

}
