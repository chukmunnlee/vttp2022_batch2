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
}
