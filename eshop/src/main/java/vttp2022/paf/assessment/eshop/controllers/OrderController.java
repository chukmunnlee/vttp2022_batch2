package vttp2022.paf.assessment.eshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.models.OrderStatusCount;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.services.OrderException;
import vttp2022.paf.assessment.eshop.services.OrderService;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

import static vttp2022.paf.assessment.eshop.Utils.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/order", produces=MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private OrderService orderSvc;

	@Autowired
	private WarehouseService warehouseSvc;

	//TODO: Task 3
	@PostMapping(path={ "/", "" }, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postOrder(@RequestBody String payload) {
		JsonObject json = toJson(payload);
		String name = json.getString("name");

		// Task 3a
		Optional<Customer> opt = custRepo.findCustomerByName(name);
		if (opt.isEmpty()) {
			JsonObject error = createError("Customer %s not found".formatted(name));
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(error.toString());
		}

		// Task 3b
		Order order = toOrder(json);
		Customer customer = opt.get();

		try {
			orderSvc.createOrder(order, customer);
		} catch (OrderException ex) {
			JsonObject error = createError(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(error.toString());
		}

		// Task 4
		OrderStatus orderStatus = warehouseSvc.dispatch(order);

		return ResponseEntity.ok(toJson(orderStatus).toString());
	}

	@GetMapping(path="/{name}/status")
	public ResponseEntity<String> getOrderStatus(@PathVariable String name) {
		OrderStatusCount orderStatusCount = orderSvc.getOrderStatusCount(name);
		JsonObject payload = toJson(orderStatusCount);
		return ResponseEntity.ok(payload.toString());
	}

}
