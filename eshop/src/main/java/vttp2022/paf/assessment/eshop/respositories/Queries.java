package vttp2022.paf.assessment.eshop.respositories;

public class Queries {

	public static final String SQL_FIND_CUSTOMER_BY_NAME = """
			select * 
				from customer 
				where name = ?
		""";

	public static final String SQL_INSERT_ORDER = """
			insert into 
				purchase_order(order_id, order_date, name, address, email)
				values (?, ?, ?, ?, ?)
		""";

	public static final String SQL_INSERT_LINEITEM = """
			insert into
				line_item(item, quantity, order_id)
				values (?, ?, ?)
		""";

	public static final String SQL_INSERT_ORDER_STATUS = """
			insert into
				order_status(order_id, delivery_id, status)
				values (?, ?, ?)
		""";

	public static final String SQL_GET_ORDER_STATUS_BY_NAME = """
			select * 
				from order_status_count 
				where name = ?
		""";
}
