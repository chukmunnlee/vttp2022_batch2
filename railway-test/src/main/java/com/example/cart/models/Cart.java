package com.example.cart.models;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Cart {

    private List<Item> contents = new LinkedList<>();
    private final String created = (new Date()).toString();

    public String getCreatedDateTimestamp() {
        return created;
    }

    public List<Item> getContents() {
        return Collections.unmodifiableList(contents);
    }
    public void setContents(List<Item> contents) {
        this.contents = contents;
    }
    public void addItem(Item item) {
        contents.add(item);
    }
    public void removeItem(int i) {
        contents.remove(i);
    }
    public void removeItem(final Item item) {
        contents.removeIf(v -> v.getName().equals(item.getName()) 
            && (v.getQuantity() == item.getQuantity()));
    }
}
