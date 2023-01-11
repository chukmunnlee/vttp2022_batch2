package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatusCount;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;
import static vttp2022.paf.assessment.eshop.Utils.*;

import java.util.List;

@Repository
public class OrderRepository {
	// TODO: Task 3

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertOrder(Order order) {
		//purchase_order(order_id, order_date, name, address, email)
		jdbcTemplate.update(SQL_INSERT_ORDER, order.getOrderId(), order.getOrderDate()
				, order.getName(), order.getAddress(), order.getEmail());

		//line_item(item, quantity, order_id)
		List<Object[]> lineItems = order.getLineItems().stream()
				.map(v -> new Object[]{ v.getItem(), v.getQuantity(), order.getOrderId() })
				.toList();
		jdbcTemplate.batchUpdate(SQL_INSERT_LINEITEM, lineItems);
	}

	public OrderStatusCount getOrdersCount(String name) {

		SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_ORDER_STATUS_BY_NAME, name);

		return toOrderStatusCount(rs);
	}
}
