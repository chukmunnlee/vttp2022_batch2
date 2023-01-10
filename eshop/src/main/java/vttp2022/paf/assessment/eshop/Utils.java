package vttp2022.paf.assessment.eshop;

import java.io.StringReader;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;

public class Utils {

	public static JsonObject toJson(String str) {
		JsonReader reader = Json.createReader(new StringReader(str));
		return reader.readObject();
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

	public static Customer toCustomer(SqlRowSet rs) {
		Customer customer = new Customer();
		customer.setName(rs.getString("name"));
		customer.setAddress(rs.getString("address"));
		customer.setEmail(rs.getString("email"));
		return customer;
	}

	public static JsonObject createError(String message) {
		return Json.createObjectBuilder()
			.add("error", message)
			.build();
	}
}
