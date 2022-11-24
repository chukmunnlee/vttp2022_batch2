package vttp.paf.day24.models;

public class LineItem {
    private Integer itemId;
    private String description;
    private Integer quantity;

    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
}
