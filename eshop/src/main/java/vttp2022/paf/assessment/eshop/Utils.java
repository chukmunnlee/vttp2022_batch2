package vttp2022.paf.assessment.eshop;

import java.io.StringReader;
import java.util.List;

import com.mysql.cj.xdevapi.JsonArray;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.models.OrderStatusCount;

public class Utils {

	public static JsonObject toJson(String str) {
		JsonReader reader = Json.createReader(new StringReader(str));
		return reader.readObject();
	}

	public static JsonObject toJson(Order order, String createdBy) {
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder(
				order.getLineItems().stream()
					.map(v -> Json.createObjectBuilder()
							.add("item", v.getItem())
							.add("quantity", v.getQuantity())
							.build())
					.toList());

		JsonObjectBuilder objBuilder = Json.createObjectBuilder()
			.add("orderId", order.getOrderId())
			.add("name", order.getName())
			.add("email", order.getEmail())
			.add("address", order.getAddress())
			.add("createdBy", createdBy)
			.add("lineItems", arrBuilder.build());
		
		return objBuilder.build();
	}

	public static JsonObject toJson(OrderStatus orderStatus) {
		JsonObjectBuilder objBuilder = Json.createObjectBuilder()
				.add("orderId", orderStatus.getOrderId())
				.add("status", orderStatus.getStatus());

		if ("dispatched".equals(orderStatus.getStatus()))
				objBuilder.add("deliveryId", orderStatus.getDeliveryId());

		return objBuilder.build();
	}

	public static JsonObject toJson(OrderStatusCount orderStatusCount) {
		return Json.createObjectBuilder()
			.add("name", orderStatusCount.getName())
			.add("dispatched", orderStatusCount.getDispatched())
			.add("pending", orderStatusCount.getPending())
			.add("name", orderStatusCount.getName())
			.build();
	}


	public static Order toOrder(JsonObject json) {
		List<LineItem> lineItems = json.getJsonArray("lineItems")
			.stream()
			.map(v -> v.asJsonObject())
			.map(Utils::toLineItem)
			.toList();
		Order order = new Order();
		order.setName(json.getString("name"));
		order.setLineItems(lineItems);
		return order;
	}

	public static LineItem toLineItem(JsonObject json) {
		LineItem lineItem = new LineItem();
		lineItem.setItem(json.getString("item"));
		lineItem.setQuantity(json.getInt("quantity"));
		return lineItem;
	}

	public static OrderStatus toOrderStatus(JsonObject json) {
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrderId(json.getString("orderId"));
		orderStatus.setDeliveryId(json.getString("deliveryId"));
		orderStatus.setStatus("dispatched");
		return orderStatus;
	}

	public static Customer toCustomer(SqlRowSet rs) {
		Customer customer = new Customer();
		customer.setName(rs.getString("name"));
		customer.setAddress(rs.getString("address"));
		customer.setEmail(rs.getString("email"));
		return customer;
	}

	public static OrderStatusCount toOrderStatusCount(SqlRowSet rs) {
		OrderStatusCount orderStatusCount = new OrderStatusCount();
		while (rs.next()) {
			String status = rs.getString("status");
			orderStatusCount.setName(rs.getString("name"));
			if ("pending".equals(status))
				orderStatusCount.setPending(rs.getInt("status_count"));
			else if ("dispatched".equals(status))
				orderStatusCount.setDispatched(rs.getInt("status_count"));
		}
		return orderStatusCount;
	}

	public static JsonObject createError(String message) {
		return Json.createObjectBuilder()
			.add("error", message)
			.build();
	}

}
