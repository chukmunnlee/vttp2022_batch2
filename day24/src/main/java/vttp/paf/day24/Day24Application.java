package vttp.paf.day24;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.paf.day24.models.LineItem;
import vttp.paf.day24.models.PurchaseOrder;
import vttp.paf.day24.services.OrderException;
import vttp.paf.day24.services.OrderService;

@SpringBootApplication
public class Day24Application implements CommandLineRunner {

	public static String[] DESC = { "apple", "orange", "pear", "grapes" };
	public static Integer[] QTY = { 10, 5, 3, 5 };

	@Autowired OrderService ordSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day24Application.class, args);
	}

	@Override
	public void run(String... args) {

		/* 
		PurchaseOrder po = new PurchaseOrder();
		po.setName("fred");
		po.setOrderDate(new Date());

		for (int i = 0; i < DESC.length; i++)
			po.addLineItem(new LineItem(DESC[i], QTY[i]));

		// Create the purchase order
		try {
			ordSvc.createNewOrder(po);
		} catch (OrderException ex) {
			System.out.println(">>>>> " + ex.getMessage());
		}
		*/
	}

}
