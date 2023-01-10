package vttp2022.paf.assessment.eshop.services;

public class OrderException extends Exception {
	public OrderException() { 
		super();
	}
	public OrderException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
