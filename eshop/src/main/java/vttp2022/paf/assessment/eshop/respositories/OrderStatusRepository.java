package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.OrderStatus;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class OrderStatusRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertOrderStatus(OrderStatus orderStatus) {
		//order_status(order_id, delivery_id, status)
		jdbcTemplate.update(SQL_INSERT_ORDER_STATUS, orderStatus.getOrderId()
				, orderStatus.getDeliveryId(), orderStatus.getStatus());
	}
}
