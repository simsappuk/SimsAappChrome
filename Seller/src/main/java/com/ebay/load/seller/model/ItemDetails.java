package com.ebay.load.seller.model;

import javax.persistence.Embeddable;

@Embeddable
public class ItemDetails{
    private String itemId;
    private Double price;
    private Integer quantity;

    public ItemDetails(String itemId, Double price, Integer quantity){
        this.itemId=itemId;
        this.price=price;
        this.setQuantity(quantity);
    }

    public ItemDetails(){
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

