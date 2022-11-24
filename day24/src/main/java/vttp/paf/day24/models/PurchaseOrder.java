package vttp.paf.day24.models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PurchaseOrder {
    private String orderId;
    private String name;
    private Date orderDate;
    private List<LineItem> lineItems = new LinkedList<>();

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public List<LineItem> getLineItems() { return lineItems; }
    public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
    public void addLineItem(LineItem lineItem) { this.lineItems.add(lineItem); }

    
}
