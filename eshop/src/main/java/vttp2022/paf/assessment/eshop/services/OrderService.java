package vttp2022.paf.assessment.eshop.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Transactional(rollbackFor={ OrderException.class }) 
	public void createOrder(Order order, Customer customer) throws OrderException {

		String orderId = UUID.randomUUID().toString().substring(0, 8);
		order.setOrderId(orderId);
		order.setAddress(customer.getAddress());
		order.setEmail(customer.getEmail());

		try {
			orderRepo.insertOrder(order);
		} catch (Exception ex) {
			throw new OrderException(ex.getMessage(), ex);
		}
	}

}
