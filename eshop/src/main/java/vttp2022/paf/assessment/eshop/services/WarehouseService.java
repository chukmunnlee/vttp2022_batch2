package vttp2022.paf.assessment.eshop.services;

import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.respositories.OrderStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static vttp2022.paf.assessment.eshop.Utils.*;

@Service
public class WarehouseService {

	public static final String CREATED_BY = "fred";
	public static final String URL = "http://paf.chuklee.com/dispatch";

	@Autowired
	private OrderStatusRepository orderStatusRepo;

	// You cannot change the method's signature
	// You may add one or more checked exceptions
	public OrderStatus dispatch(Order order) {

		// TODO: Task 4
		OrderStatus orderStatus;

		JsonObject json = toJson(order, CREATED_BY);

		String url = UriComponentsBuilder.fromUriString(URL)
				.pathSegment(order.getOrderId())
				.toUriString();

		RequestEntity<String> req = RequestEntity
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(json.toString());

		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
			String payload = resp.getBody();
			orderStatus = toOrderStatus(toJson(payload));
		} catch (RestClientException ex) {
			orderStatus = new OrderStatus();
			orderStatus.setOrderId(order.getOrderId());
		}

		orderStatusRepo.insertOrderStatus(orderStatus);
		
		return orderStatus;
	}
}
